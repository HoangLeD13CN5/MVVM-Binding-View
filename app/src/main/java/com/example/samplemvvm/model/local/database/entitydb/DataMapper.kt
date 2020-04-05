package com.example.samplemvvm.model.local.database.entitydb

import com.example.samplemvvm.entity.Item
import com.example.samplemvvm.entity.License
import com.example.samplemvvm.entity.Owner

/**
 * Created by HoangLM on 4/5/20.
 */
object DataMapper {
    fun convertItemDbToEntity(itemDb: ItemDbEntity): Item {
        val owner = Owner(
            "",
            itemDb.owner.itemOwnerId,
            "",
            itemDb.owner.avatar,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            false
        )
        val license = License("","","","","")
        return Item(itemDb.itemId,"","",itemDb.full_name,owner,false,itemDb.html_url,itemDb.description,false,
            "","","","","","",
            "","","","","","",
            "","","","",
            "","","","","",
            "","","","","","","","",
            "","","","","","","",
            "","","","","","","","","",
            0,itemDb.stargazers_count,0,"",false,false,false,
            false,false,itemDb.forks_count,0,false,itemDb.open_issues_count,
            license,0,0,0,"",0.0)
    }
}