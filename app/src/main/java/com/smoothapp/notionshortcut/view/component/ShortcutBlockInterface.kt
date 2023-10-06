package com.smoothapp.notionshortcut.view.component

import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty

interface ShortcutBlockInterface {
    fun getContents(): NotionDatabaseProperty
}