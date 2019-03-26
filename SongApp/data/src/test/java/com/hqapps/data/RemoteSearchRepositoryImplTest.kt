package com.hqapps.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hqapps.data.search.api.remote_search.RemoteSearchAPI
import com.hqapps.data.search.repository.RemoteSearchRepositoryImpl
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
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteSearchRepositoryImplTest {

    private lateinit var remoteSearchRepositoryImpl: RemoteSearchRepositoryImpl

    private lateinit var testRemoteData : ListResponse<SearchEntity>

    @Mock
    private lateinit var remoteSearchAPI: RemoteSearchAPI

    @Before
    fun setUp(){
        remoteSearchRepositoryImpl = RemoteSearchRepositoryImpl(remoteSearchAPI)
        val listType = object : TypeToken<ListResponse<SearchEntity>>() {}.type
        testRemoteData = Gson().fromJson(loadJsonFileFromResources("testRemoteData.json"), listType)
    }

    @Test
    fun testRemoteSearch() {
        given(remoteSearchAPI.search(Matchers.anyString()))
                .willReturn(Observable.just(testRemoteData))

        val observer = remoteSearchRepositoryImpl.searchRemote("").test()

        verify(remoteSearchAPI).search("")

        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue { l -> l.results.size == 6 } // comes from testRemoteData.json
    }
}