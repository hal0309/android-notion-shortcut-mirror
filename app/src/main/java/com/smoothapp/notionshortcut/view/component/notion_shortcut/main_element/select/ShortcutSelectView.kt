package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutSelectBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.adapter.NotionSelectListAdapter
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutBlockInterface

class ShortcutSelectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, name: String = "",
    selectedList: List<NotionPostTemplate.Select>? = null, listener: Listener? = null
) : BaseShortcutSelectView(context, attrs, defStyleAttr, name, selectedList, listener) {

    init {
        init()
    }

    private fun init() {

    }

    override fun getContents(): NotionDatabaseProperty {
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.SELECT,
            name,
            selectedList.map { it.name }
        )
    }

}