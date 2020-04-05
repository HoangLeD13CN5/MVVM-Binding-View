package com.example.samplemvvm.model.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.samplemvvm.common.constant.Constant
import com.example.samplemvvm.model.local.database.entitydb.ItemDbEntity
import io.reactivex.Single

/**
 * Created by HoangLM on 4/3/20.
 */
@Dao
interface ReposDao {

    @Query("SELECT * FROM ${Constant.Database.ITEM_TABLE}")
    fun getAll(): Single<List<ItemDbEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: ItemDbEntity): Single<Long>

    @Query("DELETE FROM ${Constant.Database.ITEM_TABLE} WHERE full_name = :name")
    fun delete(name: String): Single<Int>

    @Query("DELETE FROM ${Constant.Database.ITEM_TABLE}")
    fun deleteAll() : Single<Int>

}