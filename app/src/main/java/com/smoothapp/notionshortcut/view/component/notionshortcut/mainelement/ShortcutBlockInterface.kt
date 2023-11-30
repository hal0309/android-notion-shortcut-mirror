package com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement

import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty

interface ShortcutBlockInterface {
    fun getContents(): NotionDatabaseProperty
}