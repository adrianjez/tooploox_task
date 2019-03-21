package com.hqapps.sample_app.presentation.search.remote_search

import android.content.Context
import android.view.LayoutInflater
import com.hqapps.sample_app.presentation.search.adapter.SearchAdapter
import dagger.Module
import dagger.Provides

@Module
class RemoteSearchFragmentProvider {

    @Provides
    internal fun providesSearchAdapter(inflater: LayoutInflater) :
            SearchAdapter = SearchAdapter(inflater)

    @Provides
    internal fun providesLayoutInflater(fragment: RemoteSearchFragment) :
            LayoutInflater = fragment.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}