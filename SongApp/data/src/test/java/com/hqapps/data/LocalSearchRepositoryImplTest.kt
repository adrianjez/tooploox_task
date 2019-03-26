package com.hqapps.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hqapps.data.search.api.local_search.LocalSearchAPI
import com.hqapps.data.search.repository.LocalSearchRepositoryImpl
import com.hqapps.data.utils.ReadJsonFileUtils.loadJsonFileFromResources
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.Matchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalSearchRepositoryImplTest {

    private lateinit var localSearchRepositoryImpl: LocalSearchRepositoryImpl

    private lateinit var testLocalData : ListResponse<SearchEntity>

    @Mock
    private lateinit var localSearchAPI: LocalSearchAPI

    @Before
    fun setUp(){
        localSearchRepositoryImpl = LocalSearchRepositoryImpl(localSearchAPI)
        val listType = object : TypeToken<ListResponse<SearchEntity>>() {}.type
        testLocalData = Gson().fromJson(loadJsonFileFromResources("testLocalData.json"), listType)
    }

    @Test
    fun testLocalSearch() {
        given(localSearchAPI.search(Matchers.anyString()))
                .willReturn(Observable.just(testLocalData))

        val observer = localSearchRepositoryImpl.searchLocal("").test()

        Mockito.verify(localSearchAPI).search("")

        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue { l -> l.results.size == 2 } // comes from testRemoteData.json*/
    }
}