package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum


class NotionDatabasePropertyNumber(
    name: String,
    private var number: String?
) : NotionDatabaseProperty(NotionApiPropertyEnum.NUMBER, name, listOf()) {

    init {
        updateParentContents()
    }
    private fun updateParentContents(){
        setPropertyContents(listOf(number))
    }

    fun updateContents(number: String?){
        this.number = number
        updateParentContents()
    }

    fun getNumber(): String? = number
}


