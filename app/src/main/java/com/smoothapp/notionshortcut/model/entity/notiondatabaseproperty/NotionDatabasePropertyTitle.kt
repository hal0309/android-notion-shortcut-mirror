package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyTitle(
    name: String,
    private var title: String?
) : NotionDatabaseProperty(NotionApiPropertyEnum.TITLE, name, listOf(title)) {

    init {
        updateParentContents()
    }
    private fun updateParentContents(){
        setPropertyContents(listOf(title))
    }

    fun updateContents(title: String?){
        this.title = title
        updateParentContents()
    }

    fun getTitle(): String? = title
}


