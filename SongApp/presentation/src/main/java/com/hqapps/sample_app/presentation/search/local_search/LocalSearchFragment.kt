package com.hqapps.sample_app.presentation.search.local_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.sample_app.R
import com.hqapps.sample_app.presentation.search.base.BaseSearchFragment
import javax.inject.Inject

class LocalSearchFragment : BaseSearchFragment() {

    @Inject
    lateinit var viewModel: LocalSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.responseLiveData.observe(this, Observer(::processResponse))
        viewModel.errorLiveData.observe(this, Observer(::processError))
        viewModel.showHideProgression.observe(this, Observer(::processShowHideProgression))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_search, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchDataFor("")
    }

    override fun searchDataFor(query: String) {
        viewModel.searchDataFor(query)
    }

    /**
     * Private members
     */
    private fun processResponse(resp: ListResponse<SearchEntity>) {
        adapter.replaceData(resp.results)
    }
}


