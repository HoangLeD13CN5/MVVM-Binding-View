package com.example.samplemvvm.model.local.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import com.example.samplemvvm.common.constant.Constant

/**
 * Created by HoangLM on 4/3/20.
 */
class ValueStore(context: Context) : IValueStore {

    private val mSharedPreferences: SharedPreferences = context.getSharedPreferences(Constant.SharedPreferences.APP_NAME, Context.MODE_PRIVATE)

    override fun store(key: String, value: Boolean) {
        val editor = mSharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun store(key: String, value: Int) {
        val editor = mSharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    override fun store(key: String, value: String?) {
        val editor = mSharedPreferences.edit()
        if (value == null) {
            editor.putString(key, "")
        } else {
            editor.putString(key, value)
        }
        editor.apply()
    }

    override fun store(key: String, value: Long) {
        val editor = mSharedPreferences.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    override fun clear(key: String) {
        val editor = mSharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mSharedPreferences.getBoolean(key, defaultValue)
    }

    override fun getInteger(key: String, defaultValue: Int): Int {
        return mSharedPreferences.getInt(key, defaultValue)
    }

    override fun getString(key: String, defaultValue: String): String {
        return mSharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return mSharedPreferences.getLong(key, defaultValue)
    }

    override fun has(key: String): Boolean {
        return mSharedPreferences.contains(key)
    }
}
