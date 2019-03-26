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

    companion object {
        const val RADIO_BUTTON_BUNDLE_KEY = "RADIO_BUTTON_BUNDLE_KEY"
        const val FRAGMENT_BUNDLE_KEY = "FRAGMENT_BUNDLE_KEY"
    }

    private var content: Fragment? = null

    override val containerId: Int
        get() = R.id.fragment_container


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null) makeStateRestoration(savedInstanceState)
        else makeScreenInitialization()

        makeScreenPostInitialization()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(content != null) {
            supportFragmentManager.putFragment(outState, FRAGMENT_BUNDLE_KEY, content!!)
        }
        outState.putInt(RADIO_BUTTON_BUNDLE_KEY, segmentedGroup.checkedRadioButtonId)
    }

    /**
     * Listeners
     */
    private val checkChangeListener = RadioGroup.OnCheckedChangeListener { _, checkedId ->
        when(checkedId){
            R.id.local -> content = LocalSearchFragment()
            R.id.remote -> content = RemoteSearchFragment()
        }
        show(content as Fragment)
    }

    /**
     * Private methods
     */
    private fun makeStateRestoration(savedInstanceState: Bundle){
        content = supportFragmentManager.getFragment(savedInstanceState, FRAGMENT_BUNDLE_KEY)
        segmentedGroup.check(savedInstanceState.getInt(RADIO_BUTTON_BUNDLE_KEY))
    }

    private fun makeScreenInitialization(){
        content = LocalSearchFragment() // default page
        show(content as Fragment)
    }

    private fun makeScreenPostInitialization(){
        segmentedGroup.setOnCheckedChangeListener(checkChangeListener)
    }
}
