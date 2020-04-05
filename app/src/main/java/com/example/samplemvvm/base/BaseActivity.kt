package com.example.samplemvvm.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.samplemvvm.MVVMApplication
import com.example.samplemvvm.common.di.component.AppComponent

/**
 * Created by HoangLM on 4/5/20.
 */
abstract class BaseActivity : AppCompatActivity() {

    val appComponent: AppComponent
        get() {
            return (application as MVVMApplication).appComponent
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()
}