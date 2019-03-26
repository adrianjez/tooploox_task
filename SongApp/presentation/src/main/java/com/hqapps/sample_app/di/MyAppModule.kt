package com.hqapps.sample_app.di

import android.app.Application
import android.content.Context
import com.hqapps.data.search.SearchServiceModule
import com.hqapps.sample_app.presentation.MainApplication
import dagger.Binds
import dagger.Module
import dagger.android.support.AndroidSupportInjectionModule

@Module(includes = [
    AndroidSupportInjectionModule::class,
    MyActivityModule::class,
    SearchServiceModule::class])
abstract class MyAppModule {

    @Binds
    internal abstract fun application(app: MainApplication): Application

    @Binds
    internal abstract fun applicationContext(app: MainApplication): Context


}
