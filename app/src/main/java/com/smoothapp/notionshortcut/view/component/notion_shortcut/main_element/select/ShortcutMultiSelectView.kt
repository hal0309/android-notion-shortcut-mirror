package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select

import android.content.Context
import android.util.AttributeSet
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyMultiSelect


class ShortcutMultiSelectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, property: NotionDatabasePropertyMultiSelect,
    selectedList: List<NotionPostTemplate.Select>? = null, listener: Listener? = null
) : BaseShortcutSelectView(context, attrs, defStyleAttr, property, selectedList, listener) {

    init {
        init()
    }

    private fun init() {

    }

    override fun getSelected(): List<NotionPostTemplate.Select> {
        property as NotionDatabasePropertyMultiSelect
        val nameList = property.getMultiSelectName()
        val colorList = property.getMultiSelectColor()

        val selectedList = mutableListOf<NotionPostTemplate.Select>()
        for(i in nameList.indices){
            selectedList.add(
                NotionPostTemplate.Select(
                nameList[i], colorList[i]?: NotionColorEnum.DEFAULT
            ))
        }
        return selectedList
    }

    override fun setSelected(selectedList: List<NotionPostTemplate.Select>) {
        property as NotionDatabasePropertyMultiSelect
        val nameList = mutableListOf<String>()
        val colorList = mutableListOf<NotionColorEnum>()

        for(selected in selectedList){
            nameList.add(selected.name)
            colorList.add(selected.color)
        }
        property.updateContents(nameList, colorList)
        applySelected()
    }

    override fun getContents(): NotionDatabasePropertyMultiSelect {
        return property as NotionDatabasePropertyMultiSelect
    }

}