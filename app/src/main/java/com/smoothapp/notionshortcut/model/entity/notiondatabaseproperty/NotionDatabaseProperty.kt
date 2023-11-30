package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

open class NotionDatabaseProperty(
    private val type: NotionApiPropertyEnum,
    private val name: String,
    private var contents: List<String?>
){
    protected fun setPropertyContents(contents: List<String?>){
        this.contents = contents
    }

    fun getType() = type
    fun getName() = name

    fun getPropertyContents() = contents
}
