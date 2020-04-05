package com.example.samplemvvm.model.local

import android.content.Context
import com.example.samplemvvm.model.local.sharedpreferences.IValueStore
import com.example.samplemvvm.model.local.sharedpreferences.ValueStore

/**
 * Created by HoangLM on 4/3/20.
 */
object AppData {
    private lateinit var valueStore: IValueStore

    fun initValueStore(context: Context) {
        valueStore = ValueStore(context)
    }

    fun valueStore(): IValueStore {
        return valueStore
    }
}