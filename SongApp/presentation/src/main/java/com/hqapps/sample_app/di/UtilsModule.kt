package com.hqapps.sample_app.di

import android.content.Context
import android.content.res.AssetManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    internal fun providesAssetManager(context: Context): AssetManager =
        context.assets

}