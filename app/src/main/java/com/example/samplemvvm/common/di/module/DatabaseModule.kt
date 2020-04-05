package com.example.samplemvvm.common.di.module

import android.content.Context
import androidx.room.Room
import com.example.samplemvvm.common.constant.Constant
import com.example.samplemvvm.model.local.database.AppDatabase
import com.example.samplemvvm.model.local.database.dao.ReposDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by HoangLM on 4/3/20.
 */
@Module
class DatabaseModule {

    private val roomDB: AppDatabase

    constructor(context: Context){
        roomDB = Room.databaseBuilder(context, AppDatabase::class.java, Constant.Database.DATABASE_NAME).build()
    }

    @Provides
    @Singleton
    fun provideReposDao(): ReposDao {
        return roomDB.reposDao()
    }
}