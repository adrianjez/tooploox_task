package com.hqapps.data.search.api.local_search

import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import io.reactivex.Observable

interface LocalSearchAPI {

    fun search(term: String) : Observable<ListResponse<SearchEntity>>
}