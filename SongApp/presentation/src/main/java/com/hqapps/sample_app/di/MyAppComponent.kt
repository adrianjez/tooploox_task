package com.hqapps.sample_app.di

import com.hqapps.data.search.SearchServiceModule
import com.hqapps.sample_app.di.viewmodel.ViewModelModule
import com.hqapps.sample_app.presentation.search.MainApplication
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [MyAppModule::class, UtilsModule::class, ViewModelModule::class, NetworkModule::class, SearchServiceModule::class])
interface MyAppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()
}
