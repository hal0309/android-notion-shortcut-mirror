package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutSelectBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate

class ShortcutSelectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = "",
    val selected: NotionPostTemplate.Select? = null
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutSelectBinding
    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_select, this)
        binding = ViewShortcutSelectBinding.bind(this)
        binding.apply {
            this.name.text = this@ShortcutSelectView.name


        }
    }

    override fun getContents(): NotionDatabaseProperty{
//        val selected = binding.content.selectedItem as NotionPostTemplate.Select
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.SELECT,
            name,
            listOf("selected.name")
        )
    }

}