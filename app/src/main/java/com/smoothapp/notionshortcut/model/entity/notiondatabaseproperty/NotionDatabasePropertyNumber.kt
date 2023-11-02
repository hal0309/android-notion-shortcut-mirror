package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum


class NotionDatabasePropertyNumber(
    name: String,
    number: String?
) : NotionDatabaseProperty(NotionApiPropertyEnum.NUMBER, name, listOf(number)) {
    fun hasContents(): Boolean{
        return contents.isNotEmpty() && !contents[0].isNullOrEmpty()
    }
    fun getNumber(): String?{
        return when(hasContents()){
            false -> null
            true -> contents[0]
        }
    }
}


