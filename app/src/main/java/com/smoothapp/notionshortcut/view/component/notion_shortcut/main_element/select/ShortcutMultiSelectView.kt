package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select

import android.content.Context
import android.util.AttributeSet
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyMultiSelect
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertySelect


class ShortcutMultiSelectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, property: NotionDatabasePropertyMultiSelect,
    selectedList: List<NotionPostTemplate.Select>? = null, listener: Listener? = null
) : BaseShortcutSelectView(context, attrs, defStyleAttr, property, selectedList, listener) {

    init {
        init()
    }

    private fun init() {

    }

    override fun getContents(): NotionDatabasePropertyMultiSelect {
        return NotionDatabasePropertyMultiSelect(
            property.getName(),
            selectedList.map { it.name },
            selectedList.map { it.color.getName() } /* optional: color */
        )
    }

}