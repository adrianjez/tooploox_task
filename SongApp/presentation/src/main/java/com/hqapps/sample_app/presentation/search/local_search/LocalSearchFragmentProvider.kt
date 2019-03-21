package com.hqapps.sample_app.presentation.search.local_search

import android.content.Context
import android.view.LayoutInflater
import com.hqapps.sample_app.presentation.search.adapter.SearchAdapter
import dagger.Module
import dagger.Provides

@Module
class LocalSearchFragmentProvider {
    @Provides
    internal fun providesSearchAdapter(inflater: LayoutInflater) :
            SearchAdapter = SearchAdapter(inflater)

    @Provides
    internal fun providesLayoutInflater(fragment: LocalSearchFragment) :
            LayoutInflater = fragment.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}