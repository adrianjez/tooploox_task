package com.hqapps.sample_app.presentation.search

import com.hqapps.sample_app.presentation.search.local_search.LocalSearchFragment
import com.hqapps.sample_app.presentation.search.local_search.LocalSearchFragmentModule
import com.hqapps.sample_app.presentation.search.remote_search.RemoteSearchFragment
import com.hqapps.sample_app.presentation.search.remote_search.RemoteSearchFragmentModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityProvider {

    @ContributesAndroidInjector(modules = [RemoteSearchFragmentModule::class])
    internal abstract fun LoginFragmentInjector(): RemoteSearchFragment

    @ContributesAndroidInjector(modules = [LocalSearchFragmentModule::class])
    internal abstract fun homeFragmentInjector(): LocalSearchFragment
}