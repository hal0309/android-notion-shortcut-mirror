package com.smoothapp.notionshortcut.model.entity

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionPostTemplate(
    val templateType: TemplateType,
    val propertyList: List<Property>
){
    enum class TemplateType{
        PAGE,
        DATABASE
    }

    data class Property(
        val type: NotionApiPropertyEnum,
        val name: String
    )
}