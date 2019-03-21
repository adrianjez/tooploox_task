package com.hqapps.sample_app.presentation.search.local_search

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module

@Module(includes = [LocalSearchFragmentProvider::class])
abstract class LocalSearchFragmentModule {

    @Binds
    internal abstract fun activityLoginFragment(login: LocalSearchFragment): Fragment

}
