package com.smoothapp.notionshortcut.view.component

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutCheckboxBinding
import com.smoothapp.notionshortcut.databinding.ViewShortcutTitleBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty

class ShortcutCheckboxView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = ""
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutCheckboxBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_checkbox, this)
        binding = ViewShortcutCheckboxBinding.bind(this)
        binding.apply {
            this.name.text = this@ShortcutCheckboxView.name
        }
    }

    override fun getContents(): NotionDatabaseProperty{
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.CHECKBOX,
            name,
            listOf( binding.content.isChecked.toString())
        )
    }

}