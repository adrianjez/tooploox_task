package com.hqapps.sample_app.presentation.search.base

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hqapps.sample_app.R
import com.hqapps.sample_app.presentation.base.BaseFragment
import com.hqapps.sample_app.presentation.search.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*
import javax.inject.Inject

abstract class BaseSearchFragment : BaseFragment() {

    companion object {
        private const val RECYCLER_VIEW_STATE_BUNDLE_KEY = "recycler_view_state_bundle_key"
    }

    @Inject
    protected lateinit var adapter: SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_search, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view.adapter = adapter
        input_container.addTextChangedListener(textWatcher)
    }

    override fun getState(): Bundle? {
        val outState = Bundle()
        textWatcher.saveState(outState)
        outState.putParcelable(RECYCLER_VIEW_STATE_BUNDLE_KEY, recycler_view.layoutManager?.onSaveInstanceState())
        adapter.saveState(outState)
        return outState
    }

    override fun makeRestoreState(state: Bundle?) {
        if(state != null){
            textWatcher.restoreState(state)
            adapter.restoreState(state)
            (recycler_view.layoutManager as? LinearLayoutManager)?.onRestoreInstanceState(
                    state.getParcelable(RECYCLER_VIEW_STATE_BUNDLE_KEY)
            )
        }
    }

    /**
     * Abstract methods
     */
    abstract fun searchDataFor(query: String)

    /**
     * Protected methods
     */
    protected fun processError(message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()


    protected fun processShowHideProgression(showOrHide: Boolean){
        progress_view.visibility = if (showOrHide) View.VISIBLE else View.GONE
    }

    /**
     * Private members
     */
    private val textWatcher =  object : TextWatcher {
        private val QUERY_STATE_BUNDLE_KEY = "QUERY_STATE_BUNDLE_KEY"
        private var currentSearchValue = ""
        private var timer = Timer()
        private val DELAY: Long = 1000 // in ms
        override fun afterTextChanged(s: Editable?) {
            //Not used
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            timer.cancel()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s != null && !currentSearchValue.contentEquals(s.toString())){
                currentSearchValue = s.toString()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        searchDataFor(s.toString())
                    }
                }, DELAY)
            }
        }

        fun saveState(outState: Bundle){
            outState.putString(QUERY_STATE_BUNDLE_KEY, currentSearchValue)
        }

        fun restoreState(outState: Bundle?){
            currentSearchValue = outState?.getString(QUERY_STATE_BUNDLE_KEY) ?: ""
        }
    }
}