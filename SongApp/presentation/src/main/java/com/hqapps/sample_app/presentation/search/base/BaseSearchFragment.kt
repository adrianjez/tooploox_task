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

    abstract fun searchDataFor(query: String)

    protected fun processError(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun processShowHideProgression(showOrHide: Boolean){
        progress_view.visibility = if (showOrHide) View.VISIBLE else View.GONE
    }
    /**
     * Private members
     */
    private val textWatcher =  object : TextWatcher {
        private var timer = Timer()
        private val DELAY: Long = 1000 // in ms
        override fun afterTextChanged(s: Editable?) {
            //Not used
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            timer.cancel()
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if(s != null){
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        searchDataFor(s.toString())
                    }
                }, DELAY)
            }
        }

    }
}