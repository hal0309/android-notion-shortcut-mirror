package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutCheckboxBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyCheckbox

class ShortcutCheckboxView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val property: NotionDatabasePropertyCheckbox
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutCheckboxBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_checkbox, this)
        binding = ViewShortcutCheckboxBinding.bind(this)
        binding.apply {
            name.text = property.getName()
            content.isChecked = property.getCheckbox().toBoolean() //todo: Stringへの変換は挟まない方が良い..?
        }
    }

    override fun getContents(): NotionDatabasePropertyCheckbox {
        return NotionDatabasePropertyCheckbox(
            property.getName(),
            binding.content.isChecked
        )
    }

}