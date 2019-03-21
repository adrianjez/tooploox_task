package com.hqapps.sample_app.presentation.search.remote_search

import androidx.fragment.app.Fragment
import dagger.Binds
import dagger.Module

@Module(includes = [RemoteSearchFragmentProvider::class])
abstract class RemoteSearchFragmentModule {

    @Binds
    internal abstract fun activityLoginFragment(login: RemoteSearchFragment): Fragment
}
