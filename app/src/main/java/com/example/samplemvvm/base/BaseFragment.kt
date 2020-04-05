package com.example.samplemvvm.base

import androidx.fragment.app.Fragment
import com.example.samplemvvm.common.di.component.AppComponent

/**
 * Created by HoangLM on 4/5/20.
 */
abstract class BaseFragment  : Fragment() {
    val appComponent: AppComponent
        get() {
            return (activity as BaseActivity).appComponent
        }
}