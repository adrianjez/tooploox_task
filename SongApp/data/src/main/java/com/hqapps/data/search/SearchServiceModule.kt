package com.hqapps.data.search

import com.hqapps.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class SearchServiceModule {

    @Provides
    @Singleton
    internal fun provideSearchAPI(retrofit: Retrofit): SearchAPI {
        return retrofit.create<SearchAPI>(SearchAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(searchRepository: SearchRepositoryImpl): SearchRepository {
        return searchRepository
    }

}