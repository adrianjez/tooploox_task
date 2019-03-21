package com.hqapps.sample_app.di

import com.hqapps.sample_app.presentation.search.MainActivity
import com.hqapps.sample_app.presentation.search.MainActivityModule

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MyActivityModule {

    /**
     * Injector
     *
     * @return
     */
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun accountActivityInjector(): MainActivity

}
