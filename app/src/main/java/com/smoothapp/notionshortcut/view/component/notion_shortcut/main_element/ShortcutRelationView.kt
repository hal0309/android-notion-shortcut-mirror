package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutBaseSelectBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.adapter.NotionRelationListAdapter
import com.smoothapp.notionshortcut.view.adapter.NotionSelectListAdapter

class ShortcutRelationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = "",
    relationList: List<NotionPostTemplate.Relation>? = null, val listener: Listener? = null
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutBaseSelectBinding

    private lateinit var selectedRelationListAdapter: NotionRelationListAdapter

    private var selectedRelationList: List<NotionPostTemplate.Relation>

    init {
        this.selectedRelationList = when(relationList) {
            null -> mutableListOf()
            else -> relationList
        }
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_shortcut_base_select, this)
        binding = ViewShortcutBaseSelectBinding.bind(this)
        binding.apply {
            this.name.text = this@ShortcutRelationView.name

            selectedRelationListAdapter = NotionRelationListAdapter(object : NotionRelationListAdapter.Listener{
                override fun onClickItem(relation: NotionPostTemplate.Relation) { //todo: 引数いらない
                    listener?.onClick(this@ShortcutRelationView)
                }
            })
            selectedRecyclerView.apply {
                adapter = selectedRelationListAdapter
                layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            }
//            root.setOnClickListener{
//                listener?.onClick(this@BaseShortcutSelectView)
//            }
        }
    }

    fun getSelected(): List<NotionPostTemplate.Relation>{
        return selectedRelationList
    }

    fun setSelected(selectedList: List<NotionPostTemplate.Relation>?) {
        this.selectedRelationList = when(selectedList) {
            null -> mutableListOf()
            else -> selectedList
        }
        applySelected()
    }

    private fun applySelected() {
        Log.d("", "relation apply $selectedRelationList")
        selectedRelationListAdapter.submitList(when(selectedRelationList.isEmpty()){
            true -> listOf(NotionPostTemplate.Relation(" + ", ""))
            else -> selectedRelationList
        })
    }

    interface Listener {
        fun onClick(shortcutRelationView: ShortcutRelationView)
    }

    override fun getContents(): NotionDatabaseProperty {
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.RELATION,
            name,
            selectedRelationList.map { it.name },
            optionalId = selectedRelationList.map { it.id }
        )
    }

}