package com.smoothapp.notionshortcut.model.constant

enum class NotionApiPropertyEnum {
    TITLE,
    RICH_TEXT,
    NUMBER,
    CHECKBOX,
    SELECT,
    MULTI_SELECT,
    STATUS,
    RELATION,
    DATE
}

enum class NotionApiPropertyStatusEnum(private val propertyName: String) {
    TO_DO("To-do"),
    IN_PROGRESS("In progress"),
    COMPLETE("Complete");

    fun getName() = propertyName
}