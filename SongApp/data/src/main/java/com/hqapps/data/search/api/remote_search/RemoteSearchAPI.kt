package com.hqapps.data.search.api.remote_search

import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteSearchAPI {

    @GET("search?")
    fun search(@Query("term") term: String, @Query("entity") entity: String = "song")
            : Observable<ListResponse<SearchEntity>>

}