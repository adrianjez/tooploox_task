package com.hqapps.data.search

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hqapps.data.utils.readAssetsFile
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.domain.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SearchRepositoryImpl : SearchRepository {


    private val searchAPI: SearchAPI
    private val assetManager: AssetManager

    @Inject
    constructor(searchAPI: SearchAPI, assetManager: AssetManager) {
        this.searchAPI = searchAPI
        this.assetManager = assetManager
    }

    override fun searchRemote(query: String): Observable<ListResponse<SearchEntity>> {
        return searchAPI.search(query)
    }

    override fun searchLocal(query: String): Observable<ListResponse<SearchEntity>> {
        val listType = object : TypeToken<ListResponse<SearchEntity>>() {}.type
        val stringJson = assetManager.readAssetsFile("songs-list.json")
        return Observable
                .create(ObservableOnSubscribe<ListResponse<SearchEntity>> { itt ->
                    val localResponse: ListResponse<SearchEntity> = Gson().fromJson(stringJson, listType)
                    if(query.isNotEmpty()) {
                        localResponse.results.filter { it.trackName.isNotEmpty() && it.trackName.contains(query, true) }
                        var filteredResults: List<SearchEntity>? = localResponse.results.filter { it.trackName != null && it.trackName.contains(query, true) }
                        localResponse.results.clear()
                        localResponse.results.addAll(filteredResults ?: arrayListOf())
                        localResponse.resultCount = filteredResults?.size ?: 0
                    }
                    itt.onNext(localResponse)
                    itt.onComplete()
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

}