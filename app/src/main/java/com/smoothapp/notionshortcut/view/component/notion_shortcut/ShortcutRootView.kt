package com.smoothapp.notionshortcut.view.component.notion_shortcut

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.controller.util.NotionApiPostPageUtil
import com.smoothapp.notionshortcut.databinding.ViewShortcutRootBinding
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutBlockInterface
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutCheckboxView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutNumberView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutRichTextView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.ShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutTitleView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.BaseShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.ShortcutMultiSelectView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ShortcutRootView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

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
                for (b in blockList) {
                    Log.d(
                        "",
                        "${b.getContents().type} ${b.getContents().name} ${b.getContents().contents} ${b.getContents().contents[0].isNotEmpty()}"
                    )
                    if (b.getContents().contents[0].isNotEmpty()) {
                        sendList.add(b.getContents())
                    }
                }
                MainScope().launch {
                    Log.d(
                        "", NotionApiPostPageUtil.postPageToDatabase(
                            "94f6ca48-d506-439f-9d2e-0fa7a2bcd5d4",
                            sendList
                        )
                    )
                }
            }
        }
    }


    fun addTitleBlock(name: String) {
        ShortcutTitleView(context, name = name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addRichTextBlock(name: String) {
        ShortcutRichTextView(context, name = name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addNumberBlock(name: String) {
        ShortcutNumberView(context, name = name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addCheckboxBlock(name: String) {
        ShortcutCheckboxView(context, name = name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addSelectBlock(name: String, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutSelectView(context, name = name, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addMultiSelectBlock(name: String, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutMultiSelectView(context, name = name, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addStatusBlock(name: String) {
        ShortcutRichTextView(context, name = name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addRelationBlock(name: String) {
        ShortcutRichTextView(context, name = name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addDateBlock(name: String) {
        ShortcutRichTextView(context, name = name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }
}