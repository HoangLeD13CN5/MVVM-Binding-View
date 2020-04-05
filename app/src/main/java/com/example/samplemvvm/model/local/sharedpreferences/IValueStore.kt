package com.example.samplemvvm.model.local.sharedpreferences

/**
 * Created by HoangLM on 4/3/20.
 */
interface IValueStore {

    fun store(key: String, value: Boolean)

    fun store(key: String, value: Int)

    fun store(key: String, value: String?)

    fun store(key: String, value: Long)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun getInteger(key: String, defaultValue: Int): Int

    fun getString(key: String, defaultValue: String): String

    fun getLong(key: String, defaultValue: Long): Long

    fun clear(key: String)

    fun has(key: String): Boolean
}
