package com.example.samplemvvm.model.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.samplemvvm.model.local.database.dao.ReposDao
import com.example.samplemvvm.model.local.database.entitydb.ItemDbEntity
import com.example.samplemvvm.model.local.database.entitydb.OwnerDbEntity

/**
 * Created by HoangLM on 4/3/20.
 */
@Database(entities = [ItemDbEntity::class,OwnerDbEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reposDao(): ReposDao
}