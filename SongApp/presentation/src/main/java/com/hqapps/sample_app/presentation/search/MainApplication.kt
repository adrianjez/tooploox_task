package com.hqapps.sample_app.presentation.search

import com.hqapps.sample_app.di.DaggerMyAppComponent

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerMyAppComponent.builder().create(this)
    }

}
