package com.hqapps.sample_app.di

import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttp(): Call.Factory {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(callFactory: Call.Factory) : Retrofit {
        return Retrofit.Builder()
                .callFactory(callFactory)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://itunes.apple.com")
                .build()
    }
}