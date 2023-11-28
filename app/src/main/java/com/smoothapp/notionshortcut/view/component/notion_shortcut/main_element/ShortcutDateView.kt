package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.controller.util.DateTimeUtil
import com.smoothapp.notionshortcut.databinding.ViewShortcutDateBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyDate

class ShortcutDateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, var property: NotionDatabasePropertyDate,
    val listener: Listener? = null
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutDateBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_date, this)
        binding = ViewShortcutDateBinding.bind(this)

        binding.apply {
            name.text = property.getName()
            setDateTime(property)
            dateContainer.setOnClickListener{
                listener?.onClick(this@ShortcutDateView)
            }
        }
    }

    fun setDateTime(property: NotionDatabasePropertyDate){
        this.property = property
        Log.d("", "from: ${property.getDateFrom()?.convertToString()}")
        Log.d("", "to: ${property.getDateTo()?.convertToString()}")
    }




    override fun getContents(): NotionDatabasePropertyDate {
        return property
    }

    interface Listener {
        fun onClick(shortcutDateView: ShortcutDateView)
    }

}