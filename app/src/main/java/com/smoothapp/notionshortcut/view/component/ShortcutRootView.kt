package com.smoothapp.notionshortcut.view.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutRootBinding
import com.smoothapp.notionshortcut.databinding.ViewShortcutTitleBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionApiPostPageObj
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate

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
        addTitleBlock("hoge")
        addRichTextBlock("boke")
        binding.apply {
            sendBtn.setOnClickListener {
                Log.d("", blockList.toString())
                for(b in blockList){
                    Log.d("","${b.getContents().type} ${b.getContents().name} ${b.getContents().contents}")
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
            ShortcutRichTextView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addCheckboxBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutRichTextView(context, name = name).apply {
                Log.e("", getContents().toString())
                blockList.add(this)
            }
        )
    }

    private fun addSelectBlock(name: String) {
        binding.blockContainer.addView(
            ShortcutRichTextView(context, name = name).apply {
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