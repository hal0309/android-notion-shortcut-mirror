package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ItemNotionSelectBinding
import com.smoothapp.notionshortcut.databinding.ViewShortcutSelectBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate

class ShortcutSelectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = "",
    private var selected: NotionPostTemplate.Select? = null, val listener: Listener? = null
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutSelectBinding
    private lateinit var selectedBinding: ItemNotionSelectBinding

    init {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_shortcut_select, this)
        binding = ViewShortcutSelectBinding.bind(this)
        binding.apply {
            this.name.text = this@ShortcutSelectView.name
            selectedBinding = ItemNotionSelectBinding.inflate(
                LayoutInflater.from(context),
                selectContainer,
                false
            ).apply {
                root.setOnClickListener { listener?.onClick(this@ShortcutSelectView) }
            }
            selectContainer.addView(selectedBinding.root)
        }
    }

    override fun getContents(): NotionDatabaseProperty {
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.SELECT,
            name,
            listOf(selected?.name ?: "")
        )
    }

    fun getSelected(): List<NotionPostTemplate.Select>{
        return when(selected){
            null -> mutableListOf()
            else -> mutableListOf(selected!!)
        }
    }

    fun setSelected(selected: NotionPostTemplate.Select?) {
        this.selected = selected
        applySelected()
    }

    private fun applySelected() {
        selectedBinding.name.text = when (selected) {
            null -> "+"
            else -> selected!!.name
        }
    }

    interface Listener {
        fun onClick(shortcutSelectView: ShortcutSelectView)
    }

}