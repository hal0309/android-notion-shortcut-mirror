package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyCheckbox(
    name: String,
    checkbox: Boolean
) : NotionDatabaseProperty(NotionApiPropertyEnum.CHECKBOX, name, listOf(checkbox.toString())) {
    fun hasContents(): Boolean{
        return contents.isNotEmpty() && !contents[0].isNullOrEmpty()
    }
    fun getCheckbox(): String?{
        return when(hasContents()){
            false -> null
            true -> contents[0]
        }
    }
}


