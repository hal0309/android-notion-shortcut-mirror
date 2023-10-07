package com.smoothapp.notionshortcut.view.component.notion_shortcut.sub_element

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutSelectBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.adapter.NotionSelectAdapter
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutBlockInterface

class ShorcutSelectSubView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = "", val selectList: List<NotionPostTemplate.Select> = listOf()
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutSelectBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_select, this)
        binding = ViewShortcutSelectBinding.bind(this)
        binding.apply {
            this.name.text = this@ShorcutSelectSubView.name

            val items = mutableListOf<String>()
            for(select in selectList){
                items.add(select.name)
            }

//            val adapter: ArrayAdapter<String> = ArrayAdapter(
//                context, android.R.layout.simple_spinner_item, items
//            )
            val adapter = NotionSelectAdapter(
                context, selectList
            )
            content.adapter = adapter
        }
    }

    override fun getContents(): NotionDatabaseProperty{
        val selected = binding.content.selectedItem as NotionPostTemplate.Select
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.SELECT,
            name,
            listOf(selected.name)
        )
    }

}