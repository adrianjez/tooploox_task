package com.hqapps.data.search.repository

import com.hqapps.data.search.api.local_search.LocalSearchAPI
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.domain.repository.LocalSearchRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalSearchRepositoryImpl : LocalSearchRepository {

    private val localSearchAPI: LocalSearchAPI

    @Inject
    constructor(localSearchAPI: LocalSearchAPI) {
        this.localSearchAPI = localSearchAPI
    }

    override fun searchLocal(query: String): Observable<ListResponse<SearchEntity>> {
        return localSearchAPI.search(query)
    }
}