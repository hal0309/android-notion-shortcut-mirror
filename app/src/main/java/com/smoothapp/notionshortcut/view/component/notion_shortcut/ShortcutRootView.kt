package com.smoothapp.notionshortcut.view.component.notion_shortcut

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.controller.util.NotionApiPostPageUtil
import com.smoothapp.notionshortcut.databinding.ViewShortcutRootBinding
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyTitle
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutBlockInterface
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutCheckboxView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutDateView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutNumberView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutRichTextView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutStatusView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.ShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutTitleView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.BaseShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.ShortcutMultiSelectView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.ShortcutRelationView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ShortcutRootView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    interface Listener {
        fun onSendBtnClicked()
    }

    private var listener: Listener? = null

    private lateinit var binding: ViewShortcutRootBinding

    private val blockList = mutableListOf<ShortcutBlockInterface>()

    init {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_shortcut_root, this)
        binding = ViewShortcutRootBinding.bind(this)
        binding.apply {
            sendBtn.setOnClickListener {
                listener?.onSendBtnClicked()
            }
        }
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun getBlockList() = blockList


    fun addTitleBlock(property: NotionDatabasePropertyTitle) {
        ShortcutTitleView(context, name = property.getName()).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addRichTextBlock(property: NotionPostTemplate.Property) {
        ShortcutRichTextView(context, name = property.name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addNumberBlock(property: NotionPostTemplate.Property) {
        ShortcutNumberView(context, name = property.name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addCheckboxBlock(property: NotionPostTemplate.Property) {
        ShortcutCheckboxView(context, name = property.name).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addSelectBlock(property: NotionPostTemplate.Property, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutSelectView(context, name = property.name, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addMultiSelectBlock(property: NotionPostTemplate.Property, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutMultiSelectView(context, name = property.name, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addStatusBlock(property: NotionPostTemplate.Property, listener: ShortcutStatusView.Listener? = null) {
        ShortcutStatusView(context, name = property.name, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addRelationBlock(property: NotionPostTemplate.Property, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutRelationView(context, name = property.name, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addDateBlock(property: NotionPostTemplate.Property, listener: ShortcutDateView.Listener? = null) {
        ShortcutDateView(context, name = property.name, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }
}