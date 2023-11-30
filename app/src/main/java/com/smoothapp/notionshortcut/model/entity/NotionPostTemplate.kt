package com.smoothapp.notionshortcut.model.entity

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty

class NotionPostTemplate(
    val templateType: TemplateType,
    val propertyList: List<NotionDatabaseProperty>
){
    enum class TemplateType{
        PAGE,
        DATABASE
    }

    data class Property(
        val type: NotionApiPropertyEnum,
        val name: String,
        val preset: Preset? = null
    )

    data class Select(
        val name: String,
        val color: NotionColorEnum,
        val id: String? = null
    )

    data class Preset(
        val contentList: List<String?>
    )
}