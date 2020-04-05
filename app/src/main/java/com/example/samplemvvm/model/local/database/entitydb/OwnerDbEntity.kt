package com.example.samplemvvm.model.local.database.entitydb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.samplemvvm.common.constant.Constant

/**
 * Created by HoangLM on 4/5/20.
 */

@Entity(tableName = Constant.Database.OWNER_TABLE)
data class OwnerDbEntity (
    @PrimaryKey val id : Long,
    val itemOwnerId: Long,
    @ColumnInfo(name = "avatar_url") val avatar: String
)