package com.example.samplemvvm.common.di.module

import android.content.Context
import com.example.samplemvvm.BuildConfig
import com.example.samplemvvm.common.constant.Constant
import com.example.samplemvvm.common.di.annotation.ApplicationContext
import com.example.samplemvvm.model.api.ReposApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by HoangLM on 4/3/20.
 */

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): Gson {
        return GsonBuilder().setDateFormat(Constant.DEFAULT_FORMAT).serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient.Builder {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(30, TimeUnit.SECONDS)
        httpClient.readTimeout(30, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        return httpClient
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    @Singleton
    @Named("no-auth")
    fun provideRetrofitNoAuth(gson: Gson, httpClient: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Provides
    fun provideReposApi(retrofit: Retrofit): ReposApi {
        return retrofit.create(ReposApi::class.java)
    }
}