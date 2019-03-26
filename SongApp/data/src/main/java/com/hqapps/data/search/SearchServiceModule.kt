package com.hqapps.data.search

import com.hqapps.data.search.api.local_search.LocalSearchAPI
import com.hqapps.data.search.api.local_search.LocalSearchImpl
import com.hqapps.data.search.api.remote_search.RemoteSearchAPI
import com.hqapps.data.search.repository.LocalSearchRepositoryImpl
import com.hqapps.data.search.repository.RemoteSearchRepositoryImpl
import com.hqapps.domain.repository.LocalSearchRepository
import com.hqapps.domain.repository.RemoteSearchRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SearchServiceModule {

    /**
     * Methods providing API
     */
    @Provides
    @Singleton
    internal fun provideRemoteSearchAPI(retrofit: Retrofit): RemoteSearchAPI {
        return retrofit.create<RemoteSearchAPI>(RemoteSearchAPI::class.java)
    }

    @Provides
    @Singleton
    internal fun provideLocalSearchAPI() : LocalSearchAPI {
        return LocalSearchImpl()
    }

    /**
     * Methods providing repository
     */
    @Provides
    @Singleton
    fun provideRemoteSearchRepository(searchRepository: RemoteSearchRepositoryImpl): RemoteSearchRepository {
        return searchRepository
    }

    @Provides
    @Singleton
    fun provideLocalSearchRepository(searchRepository: LocalSearchRepositoryImpl): LocalSearchRepository {
        return searchRepository
    }

}