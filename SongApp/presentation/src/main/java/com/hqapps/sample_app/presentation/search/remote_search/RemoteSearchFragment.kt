package com.hqapps.sample_app.presentation.search.remote_search

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.hqapps.domain.model.ListResponse
import com.hqapps.domain.model.SearchEntity
import com.hqapps.sample_app.presentation.search.base.BaseSearchFragment
import javax.inject.Inject


class RemoteSearchFragment : BaseSearchFragment() {


    @Inject
    lateinit var viewModel: RemoteSearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.response.observe(this, Observer(::processResponse))
        viewModel.errorLiveData.observe(this, Observer(::processError))
        viewModel.showHideProgression.observe(this, Observer(::processShowHideProgression))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null) {
            viewModel.searchDataFor("jack+johnson")
        }
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
