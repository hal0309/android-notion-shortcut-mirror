package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum


class NotionDatabasePropertyRichText(
    name: String,
    contents: List<String?>
) : NotionDatabaseProperty(NotionApiPropertyEnum.RICH_TEXT, name, contents) {
    fun hasContents(): Boolean{
        return contents.isNotEmpty() && !contents[0].isNullOrEmpty()
    }
    fun getRichText(): String?{
        return when(hasContents()){
            false -> null
            true -> contents[0]
        }
    }
}


