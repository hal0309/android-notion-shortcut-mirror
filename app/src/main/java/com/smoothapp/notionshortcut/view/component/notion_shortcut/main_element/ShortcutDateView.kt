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
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val property: NotionDatabasePropertyDate,
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

        fromDateTime = DateTimeUtil.convertStringToDateTime(property.getDateFrom())
        toDateTime = DateTimeUtil.convertStringToDateTime(property.getDateTo())

        binding.apply {
            name.text = property.getName()
            setDateTime(fromDateTime, toDateTime)
            dateContainer.setOnClickListener{
                listener?.onClick(this@ShortcutDateView)
            }
        }
    }

    fun setDateTime(fromDateTime: DateTimeUtil.DateTime?, toDateTime: DateTimeUtil.DateTime?){
        this.fromDateTime = fromDateTime
        this.toDateTime = toDateTime
        binding.dateText.text = DateTimeUtil.getDisplayDateTimeToDateTimeString(fromDateTime, toDateTime)
        Log.d("", "from: ${fromDateTime?.convertToString()}")
        Log.d("", "to: ${toDateTime?.convertToString()}")
    }

    fun getFromDateTime() = fromDateTime
    fun getToDateTime() = toDateTime



    override fun getContents(): NotionDatabasePropertyDate {
        return NotionDatabasePropertyDate(
            property.getName(),
            fromDateTime?.convertToString(),
            toDateTime?.convertToString()
        )
    }

    interface Listener {
        fun onClick(shortcutDateView: ShortcutDateView)
    }

}