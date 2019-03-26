package com.hqapps.data.search.api.local_search

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hqapps.data.utils.ReadJsonFileUtils
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import io.reactivex.Observable

class LocalSearchImpl : LocalSearchAPI {


    override fun search(query: String): Observable<ListResponse<SearchEntity>> {
        val listType = object : TypeToken<ListResponse<SearchEntity>>() {}.type
        val stringJson = ReadJsonFileUtils.loadJsonFileFromResources("songs-list.json")
        return Observable
                .create { itt ->
                    val localResponse: ListResponse<SearchEntity> = Gson().fromJson(stringJson, listType)
                    if (query.isNotEmpty()) {
                        localResponse.results.filter { it.trackName.isNotEmpty() && it.trackName.contains(query, true) }
                        var filteredResults: List<SearchEntity>? = localResponse.results.filter {it.trackName.contains(query, true) }
                        localResponse.results.clear()
                        localResponse.results.addAll(filteredResults ?: arrayListOf())
                        localResponse.resultCount = filteredResults?.size ?: 0
                    }
                    itt.onNext(localResponse)
                    itt.onComplete()
                }
    }

}