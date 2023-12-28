package com.factorial.testtask

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.request.CachePolicy
import com.factorial.testtask.di.dataDi
import com.factorial.testtask.di.dispatcherDi
import com.factorial.testtask.di.domainDi
import com.factorial.testtask.di.mapperDi
import com.factorial.testtask.di.serviceDi
import com.factorial.testtask.di.uiDi
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

// class for di initialization and other stuff
class App : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        initDi()
    }

    private fun initDi() {
        startKoin {
            androidContext(this@App)
            modules(
                uiDi,
                domainDi,
                dataDi,
                mapperDi,
                dispatcherDi,
                serviceDi
            )
        }
    }

    override fun newImageLoader(): ImageLoader = ImageLoader.Builder(this)
        .crossfade(true)
        .error(null)
        .placeholder(null)
        .diskCachePolicy(CachePolicy.ENABLED)
        .memoryCachePolicy(CachePolicy.ENABLED)
        .networkCachePolicy(CachePolicy.ENABLED)
        .allowHardware(false)
        .build()
}
