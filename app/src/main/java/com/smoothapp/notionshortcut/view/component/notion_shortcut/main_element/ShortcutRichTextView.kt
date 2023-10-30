package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutRichTextBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty

class ShortcutRichTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = ""
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutRichTextBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_rich_text, this)
        binding = ViewShortcutRichTextBinding.bind(this)
        binding.apply {
            content.hint = name
        }
    }

    override fun getContents(): NotionDatabaseProperty {
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.RICH_TEXT,
            name,
            listOf( binding.content.text.toString())
        )
    }

}