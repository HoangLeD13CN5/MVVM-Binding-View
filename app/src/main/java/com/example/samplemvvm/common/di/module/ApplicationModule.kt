package com.example.samplemvvm.common.di.module

import android.app.Application
import android.content.Context
import com.example.samplemvvm.common.di.annotation.ApplicationContext
import dagger.Module
import dagger.Provides

/**
 * Created by HoangLM on 4/3/20.
 */
@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return application
    }
}
