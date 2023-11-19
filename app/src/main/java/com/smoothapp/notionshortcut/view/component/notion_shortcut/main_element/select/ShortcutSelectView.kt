package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select

import android.content.Context
import android.util.AttributeSet
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty
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

    override fun getContents(): NotionDatabasePropertySelect {
        var selectName: String? = null
        var selectColor: NotionColorEnum? = null
        if(selectedList.isNotEmpty()){
            selectName = selectedList[0].name
            selectColor = selectedList[0].color
        }
        return NotionDatabasePropertySelect(
            property.getName(),
            selectName,
            selectColor
        )
    }

}