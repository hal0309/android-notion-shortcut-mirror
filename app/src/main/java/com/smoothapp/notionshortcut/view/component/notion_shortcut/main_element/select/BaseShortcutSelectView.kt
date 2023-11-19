package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutBaseSelectBinding
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty
import com.smoothapp.notionshortcut.view.adapter.NotionSelectListAdapter
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutBlockInterface

abstract class BaseShortcutSelectView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val property: NotionDatabaseProperty,
    selectedList: List<NotionPostTemplate.Select>? = null, val listener: Listener? = null
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    protected lateinit var binding: ViewShortcutBaseSelectBinding

    private lateinit var selectedListAdapter: NotionSelectListAdapter

//    protected var selectedList: List<NotionPostTemplate.Select>

    init {
//        this.selectedList = when(selectedList) {
//            null -> mutableListOf()
//            else -> selectedList
//        }
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_shortcut_base_select, this)
        binding = ViewShortcutBaseSelectBinding.bind(this)
        binding.apply {
            this.name.text = property.getName()

            selectedListAdapter = NotionSelectListAdapter(object : NotionSelectListAdapter.Listener{
                override fun onClickItem(select: NotionPostTemplate.Select) {
                    listener?.onClick(this@BaseShortcutSelectView)
                }
            })
            selectedRecyclerView.apply {
                adapter = selectedListAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            }
            applySelected()
        }
    }

    abstract fun getSelected(): List<NotionPostTemplate.Select>
//    {
//        return selectedList
//    }

    abstract fun setSelected(selectedList: List<NotionPostTemplate.Select>)
//    {
//        this.selectedList = when(selectedList) {
//            null -> mutableListOf()
//            else -> selectedList
//        }
//        applySelected()
//    }

    protected fun applySelected() {
        val selectedList = getSelected()
        selectedListAdapter.submitList(when(selectedList.isEmpty()){
            true -> listOf(NotionPostTemplate.Select(" + ", NotionColorEnum.DEFAULT))
            else -> selectedList
        })
    }

    interface Listener {
        fun onClick(baseShortcutSelectView: BaseShortcutSelectView)
    }

}