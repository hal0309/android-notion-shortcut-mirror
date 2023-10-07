package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutTitleBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty

class ShortcutTitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = ""
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutTitleBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_title, this)
        binding = ViewShortcutTitleBinding.bind(this)
        binding.apply {
            content.hint = name
        }
    }

    override fun getContents(): NotionDatabaseProperty{
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.TITLE,
            name,
            listOf( binding.content.text.toString())
        )
    }

}