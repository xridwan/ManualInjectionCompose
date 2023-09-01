package com.eve.manualinjection

import android.app.Application
import com.eve.manualinjection.di.AppModule
import com.eve.manualinjection.di.AppModuleImpl

class MainApp : Application() {

    companion object {
        lateinit var appModule: AppModule
    }

    override fun onCreate() {
        super.onCreate()
        appModule = AppModuleImpl(this)
    }
}