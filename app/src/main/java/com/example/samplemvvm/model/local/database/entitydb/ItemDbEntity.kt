package com.example.samplemvvm.model.local.database.entitydb

/**
 * Created by HoangLM on 4/3/20.
 */

import androidx.room.*
import com.example.samplemvvm.common.constant.Constant

@Entity(tableName = Constant.Database.ITEM_TABLE)
data class ItemDbEntity(
    @PrimaryKey val itemId: Long,
    @ColumnInfo(name = "full_name") val full_name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "forks_count") val forks_count: Int,
    @ColumnInfo(name = "stargazers_count") val stargazers_count: Int,
    @ColumnInfo(name = "html_url") val html_url: String,
    @ColumnInfo(name = "open_issues_count") val open_issues_count: Int,
    @Embedded val owner: OwnerDbEntity
)