package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutCheckboxBinding
import com.smoothapp.notionshortcut.databinding.ViewShortcutDateBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty

class ShortcutDateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = "",
    val listener: Listener? = null
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutDateBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_date, this)
        binding = ViewShortcutDateBinding.bind(this)
        binding.apply {
            this.name.text = this@ShortcutDateView.name
            dateContainer.setOnClickListener{
                listener?.onClick(this@ShortcutDateView)
            }
        }
    }

    override fun getContents(): NotionDatabaseProperty{
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.CHECKBOX,
            name,
            listOf( "")
        )
    }

    interface Listener {
        fun onClick(shortcutDateView: ShortcutDateView)
    }

}