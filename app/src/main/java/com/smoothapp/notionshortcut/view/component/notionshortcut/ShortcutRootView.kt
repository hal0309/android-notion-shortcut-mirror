package com.smoothapp.notionshortcut.view.component.notionshortcut

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutRootBinding
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyCheckbox
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyDate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyMultiSelect
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyNumber
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyRelation
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyRichText
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertySelect
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyStatus
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyTitle
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.ShortcutBlockInterface
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.ShortcutCheckboxView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.ShortcutDateView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.ShortcutNumberView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.ShortcutRichTextView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.ShortcutStatusView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.select.ShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.ShortcutTitleView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.select.BaseShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.select.ShortcutMultiSelectView
import com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.select.ShortcutRelationView

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
        ShortcutTitleView(context, property = property).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addRichTextBlock(property: NotionDatabasePropertyRichText) {
        ShortcutRichTextView(context, property = property).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addNumberBlock(property: NotionDatabasePropertyNumber) {
        ShortcutNumberView(context, property = property).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addCheckboxBlock(property: NotionDatabasePropertyCheckbox) {
        ShortcutCheckboxView(context, property = property).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }

    fun addSelectBlock(property: NotionDatabasePropertySelect, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutSelectView(context, property = property, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
//            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addMultiSelectBlock(property: NotionDatabasePropertyMultiSelect, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutMultiSelectView(context, property = property, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
//            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addStatusBlock(property: NotionDatabasePropertyStatus, listener: ShortcutStatusView.Listener? = null) {
        ShortcutStatusView(context, property = property, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
//            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addRelationBlock(property: NotionDatabasePropertyRelation, listener: BaseShortcutSelectView.Listener? = null) {
        ShortcutRelationView(context, property = property, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
//            setSelected(null) //todo: 規定値の設定
        }
    }

    fun addDateBlock(property: NotionDatabasePropertyDate, listener: ShortcutDateView.Listener? = null) {
        ShortcutDateView(context, property = property, listener = listener).apply {
            Log.e("", getContents().toString())
            blockList.add(this)
            binding.blockContainer.addView(this)
        }
    }
}