package com.smoothapp.notionshortcut.model.entity

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

data class NotionDatabaseProperty(
    val type: NotionApiPropertyEnum,
    val name: String,
    val contents: List<String>,
    val optional: List<String>? = null
)
