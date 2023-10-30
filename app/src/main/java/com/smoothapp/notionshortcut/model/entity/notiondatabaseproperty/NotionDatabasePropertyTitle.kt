package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyTitle(
    name: String,
    contents: List<String?>
) : NotionDatabaseProperty(NotionApiPropertyEnum.TITLE, name, contents) {
    fun hasContents(): Boolean{
        return contents.isNotEmpty() && !contents[0].isNullOrEmpty()
    }
    fun getTitle(): String?{
        return when(hasContents()){
            false -> null
            true -> contents[0]
        }
    }
}


