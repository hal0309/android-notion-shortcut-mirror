package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum


class NotionDatabasePropertyRichText(
    name: String,
    private var richText: String?
) : NotionDatabaseProperty(NotionApiPropertyEnum.RICH_TEXT, name, listOf()) {

    init {
        updateParentContents()
    }
    private fun updateParentContents(){
        setPropertyContents(listOf(richText))
    }

    fun updateContents(richText: String?){
        this.richText = richText
        updateParentContents()
    }

    fun hasContents(): Boolean{
        return contents.isNotEmpty() && !contents[0].isNullOrEmpty()
    }

    fun getRichText(): String? = richText
}


