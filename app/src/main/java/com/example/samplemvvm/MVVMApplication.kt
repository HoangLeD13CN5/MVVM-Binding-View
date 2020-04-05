package com.example.samplemvvm

import android.app.Application
import com.example.samplemvvm.common.di.component.AppComponent
import com.example.samplemvvm.common.di.component.DaggerAppComponent
import com.example.samplemvvm.common.di.module.ApplicationModule
import com.example.samplemvvm.common.di.module.DatabaseModule
import com.example.samplemvvm.model.local.AppData

/**
 * Created by HoangLM on 4/3/20.
 */

class MVVMApplication : Application() {

    lateinit var appComponent: AppComponent

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .applicationModule(ApplicationModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initAppUtils()
    }

    private fun initAppUtils() {
        AppData.initValueStore(this)
    }
}