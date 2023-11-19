package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutTitleBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyTitle

class ShortcutTitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val property: NotionDatabasePropertyTitle, // todo: propertyにデフォルト値を設定していないからlayoutから呼び出したら終わる?
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutTitleBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_title, this)
        binding = ViewShortcutTitleBinding.bind(this)
        binding.apply {
            content.hint = property.getName()
            content.setText(property.getTitle())
        }
    }

    override fun getContents(): NotionDatabasePropertyTitle {
        return NotionDatabasePropertyTitle(
            property.getName(),
            binding.content.text.toString()
        )
    }

}