package com.softwareit.sduhub.application

import android.app.Application
import com.softwareit.sduhub.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initTimber() {
//        Timber.i("Timber logging is ready");
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }
}