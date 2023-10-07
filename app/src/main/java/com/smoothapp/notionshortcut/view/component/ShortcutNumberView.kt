package com.smoothapp.notionshortcut.view.component

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutNumberBinding
import com.smoothapp.notionshortcut.databinding.ViewShortcutRichTextBinding
import com.smoothapp.notionshortcut.databinding.ViewShortcutTitleBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty

class ShortcutNumberView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = ""
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutNumberBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_number, this)
        binding = ViewShortcutNumberBinding.bind(this)
        binding.apply {
            name.text = this@ShortcutNumberView.name
        }
    }

    override fun getContents(): NotionDatabaseProperty{
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.NUMBER,
            name,
            listOf( binding.content.text.toString())
        )
    }

}