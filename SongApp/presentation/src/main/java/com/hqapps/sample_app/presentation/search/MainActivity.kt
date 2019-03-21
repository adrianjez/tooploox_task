package com.hqapps.sample_app.presentation.search

import android.os.Bundle
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.hqapps.sample_app.R
import com.hqapps.sample_app.presentation.base.BaseActivity
import com.hqapps.sample_app.presentation.search.local_search.LocalSearchFragment
import com.hqapps.sample_app.presentation.search.remote_search.RemoteSearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var content: Fragment? = null

    override val containerId: Int
        get() = R.id.fragment_container

    private val checkChangeListener = RadioGroup.OnCheckedChangeListener { _, checkedId ->
        when(checkedId){
            R.id.local -> content = LocalSearchFragment()
            R.id.remote -> content = RemoteSearchFragment()
        }
        show(content as Fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        segmentedGroup.setOnCheckedChangeListener(checkChangeListener)
        if(savedInstanceState != null){
            content = supportFragmentManager.getFragment(savedInstanceState, "FRAGMENT")
        } else {
            content = LocalSearchFragment() // default page
            show(content as Fragment)
        }
    }
}
