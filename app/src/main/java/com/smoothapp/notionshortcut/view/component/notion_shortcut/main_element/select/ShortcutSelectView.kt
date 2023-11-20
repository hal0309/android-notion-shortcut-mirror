package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select

import android.content.Context
import android.util.AttributeSet
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertySelect


class ShortcutSelectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, property: NotionDatabasePropertySelect,
    selectedList: List<NotionPostTemplate.Select>? = null, listener: Listener? = null
) : BaseShortcutSelectView(context, attrs, defStyleAttr, property, selectedList, listener) {


    init {
        init()
    }

    private fun init() {
    }

    override fun getSelected(): List<NotionPostTemplate.Select> {
        property as NotionDatabasePropertySelect
        return listOf(
            NotionPostTemplate.Select(
                property.getSelectName()?: return listOf(),
                property.getSelectColor()?: NotionColorEnum.DEFAULT
            )
        )
    }

    override fun setSelected(selectedList: List<NotionPostTemplate.Select>) {
        property as NotionDatabasePropertySelect
        when(selectedList.isEmpty()){
            true -> property.updateContents(null, null)
            else -> {
                val selected = selectedList[0]
                property.updateContents(selected.name, selected.color)
            }
        }
        applySelected()
    }

    override fun getContents(): NotionDatabasePropertySelect {
        return property as NotionDatabasePropertySelect
    }

}