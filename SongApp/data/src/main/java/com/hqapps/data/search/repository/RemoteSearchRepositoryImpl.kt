package com.hqapps.data.search.repository

import com.hqapps.data.search.api.remote_search.RemoteSearchAPI
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.domain.repository.RemoteSearchRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RemoteSearchRepositoryImpl : RemoteSearchRepository {

    private val remoteSearchAPI: RemoteSearchAPI

    @Inject
    constructor(remoteSearchAPI: RemoteSearchAPI) {
        this.remoteSearchAPI = remoteSearchAPI
    }

    override fun searchRemote(query: String): Observable<ListResponse<SearchEntity>> {
        return remoteSearchAPI.search(query)
    }
}