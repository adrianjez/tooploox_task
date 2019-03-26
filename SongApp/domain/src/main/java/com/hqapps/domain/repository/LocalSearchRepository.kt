package com.hqapps.domain.repository

import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import io.reactivex.Observable

interface LocalSearchRepository {
    fun searchLocal(query: String) : Observable<ListResponse<SearchEntity>>
}