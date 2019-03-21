package com.hqapps.data

import android.content.res.AssetManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hqapps.data.search.SearchAPI
import com.hqapps.data.search.SearchRepositoryImpl
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
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.stream.Collectors

@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryImplTest {

    private lateinit var searchRepositoryImpl: SearchRepositoryImpl

    private lateinit var testRemoteData : ListResponse<SearchEntity>
    private lateinit var testLocalData : ListResponse<SearchEntity>

    @Mock
    private lateinit var assetManager: AssetManager

    @Mock
    private lateinit var searchAPI: SearchAPI

    @Before
    fun setUp(){
        searchRepositoryImpl = SearchRepositoryImpl(searchAPI, assetManager)
        val listType = object : TypeToken<ListResponse<SearchEntity>>() {}.type
        testRemoteData = Gson().fromJson(loadJsonFileFromResources("testRemoteData.json"), listType)
        testLocalData = Gson().fromJson(loadJsonFileFromResources("testLocalData.json"), listType)


    }

    @Test
    fun testRemoteSearch() {
        given(searchAPI.search(Matchers.anyString()))
                .willReturn(Observable.just(testRemoteData))

        val observer = searchRepositoryImpl.searchRemote("").test()

        verify(searchAPI).search("")

        observer.awaitTerminalEvent()
        observer.assertComplete()
                .assertNoErrors()
                .assertValue { l -> l.results.size == 6 } // comes from testRemoteData.json
    }


    private fun loadJsonFileFromResources(path: String): String{
        val inn = BufferedReader(InputStreamReader(
                javaClass.classLoader.getResourceAsStream(path), "UTF-8"
        ))
        return inn.lines()
                .parallel()
                .collect(Collectors.joining())
    }
}