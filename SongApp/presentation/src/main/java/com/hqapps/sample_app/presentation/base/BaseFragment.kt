package com.hqapps.sample_app.presentation.base

import android.os.Bundle
import dagger.android.support.DaggerFragment

open abstract class BaseFragment : DaggerFragment(){

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState != null && savedInstanceState.containsKey(javaClass.name)){
            makeRestoreState(savedInstanceState.getBundle(javaClass.name))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(javaClass.name, getState())
    }

    abstract fun getState(): Bundle?
    abstract fun makeRestoreState(outState: Bundle?)
}