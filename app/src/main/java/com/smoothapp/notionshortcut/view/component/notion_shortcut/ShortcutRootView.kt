package com.smoothapp.notionshortcut.view.component.notion_shortcut

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.controller.util.NotionApiPostPageUtil
import com.smoothapp.notionshortcut.databinding.ViewShortcutRootBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutBlockInterface
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutCheckboxView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutNumberView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutRichTextView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutTitleView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ShortcutRootView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){

    private lateinit var binding: ViewShortcutRootBinding

    private val blockList = mutableListOf<ShortcutBlockInterface>()

    private lateinit var template: NotionPostTemplate

    init {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_shortcut_root, this)
        binding = ViewShortcutRootBinding.bind(this)
//        addTitleBlock("hoge")
//        addRichTextBlock("boke")
        binding.apply {
            sendBtn.setOnClickListener {
                Log.d("", blockList.toString())
                val sendList = mutableListOf<NotionDatabaseProperty>()
                for(b in blockList){
                    Log.d("","${b.getContents().type} ${b.getContents().name} ${b.getContents().contents} ${b.getContents().contents[0].isNotEmpty()}")
                    if(b.getContents().contents[0].isNotEmpty()){
                        sendList.add(b.getContents())
                    }
                }
                MainScope().launch {
                    Log.d("",NotionApiPostPageUtil.postPageToDatabase(
                        "94f6ca48-d506-439f-9d2e-0fa7a2bcd5d4",
                        sendList
                    ))
                }
            }
        }
    }

    fun setTemplate(template: NotionPostTemplate){
        for(property in template.propertyList){
            when(property.type){
                NotionApiPropertyEnum.TITLE -> addTitleBlock(property.name)
                NotionApiPropertyEnum.RICH_TEXT -> addRichTextBlock(property.name)
                NotionApiPropertyEnum.NUMBER -> addNumberBlock(property.name)
                NotionApiPropertyEnum.CHECKBOX -> addCheckboxBlock(property.name)
                NotionApiPropertyEnum.SELECT -> addSelectBlock(property.name)
                NotionApiPropertyEnum.MULTI_SELECT -> addMultiSelectBlock(property.name)
                NotionApiPropertyEnum.STATUS -> addStatusBlock(property.name)
                NotionApiPropertyEnum.RELATION -> addRelationBlock(property.name)
                NotionApiPropertyEnum.DATE -> addDateBlock(property.name)
            }
        }
    }

    private fun addTitleBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutTitleView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addRichTextBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutRichTextView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addNumberBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutNumberView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addCheckboxBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutCheckboxView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addSelectBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutSelectView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addMultiSelectBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutRichTextView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addStatusBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutRichTextView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addRelationBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutRichTextView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addDateBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutRichTextView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }
}