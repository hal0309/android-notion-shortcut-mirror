package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyCheckbox(
    name: String,
    private var checkbox: Boolean
) : NotionDatabaseProperty(NotionApiPropertyEnum.CHECKBOX, name, listOf()) {

    init {
        updateParentContents()
    }

    private fun updateParentContents(){
        setPropertyContents(listOf(checkbox.toString()))
    }

    fun updateContents(checkbox: Boolean){
        this.checkbox = checkbox
        updateParentContents()
    }

    fun hasContents(): Boolean{
        return contents.isNotEmpty() && !contents[0].isNullOrEmpty()
    }

    fun getCheckbox(): Boolean = checkbox

}


