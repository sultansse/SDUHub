package com.softwareit.sduhub.application

import android.app.Application
import com.softwareit.sduhub.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SduHubApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@SduHubApp)
            modules(appModule)
        }
    }
}