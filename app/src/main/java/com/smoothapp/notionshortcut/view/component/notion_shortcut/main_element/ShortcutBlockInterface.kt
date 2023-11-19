package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty

interface ShortcutBlockInterface {
    fun getContents(): NotionDatabaseProperty
}