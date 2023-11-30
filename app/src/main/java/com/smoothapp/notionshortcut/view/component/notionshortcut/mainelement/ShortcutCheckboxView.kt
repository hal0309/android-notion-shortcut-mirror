package com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutCheckboxBinding
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
            content.isChecked = property.getCheckbox()
            content.setOnCheckedChangeListener { _, isChecked ->
                property.updateContents(isChecked)
            }
        }
    }

    override fun getContents(): NotionDatabasePropertyCheckbox {
        return property
    }

}