package com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.controller.util.DateTimeUtil
import com.smoothapp.notionshortcut.databinding.ViewShortcutDateBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty

class ShortcutDateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val name: String = "",
    val listener: Listener? = null
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutDateBinding

    private var fromDateTime: DateTimeUtil.DateTime? = null
    private var toDateTime: DateTimeUtil.DateTime? = null

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_date, this)
        binding = ViewShortcutDateBinding.bind(this)
        binding.apply {
            this.name.text = this@ShortcutDateView.name
            dateContainer.setOnClickListener{
                listener?.onClick(this@ShortcutDateView)
            }
        }
    }

    fun setDateTime(fromDateTime: DateTimeUtil.DateTime?, toDateTime: DateTimeUtil.DateTime?){
        this.fromDateTime = fromDateTime
        this.toDateTime = toDateTime
        binding.dateText.text = DateTimeUtil.getDisplayDateTimeToDateTimeString(fromDateTime, toDateTime)
        Log.d("", "from: ${DateTimeUtil.convertDateTimeToString(fromDateTime?: DateTimeUtil.DateTime())}")
        Log.d("", "to: ${DateTimeUtil.convertDateTimeToString(toDateTime?: DateTimeUtil.DateTime())}")
    }



    override fun getContents(): NotionDatabaseProperty{
        val propertyList = mutableListOf<String>()
        if(fromDateTime != null){
            DateTimeUtil.convertDateTimeToString(fromDateTime!!).let{
                propertyList.add(it?: "")
            }
        }
        if(toDateTime != null){
            DateTimeUtil.convertDateTimeToString(toDateTime!!)?.let {
                propertyList.add(it)
            }
        }
        return NotionDatabaseProperty(
            NotionApiPropertyEnum.DATE,
            name,
            propertyList
        )
    }

    interface Listener {
        fun onClick(shortcutDateView: ShortcutDateView)
    }

}