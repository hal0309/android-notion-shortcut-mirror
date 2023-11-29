package com.smoothapp.notionshortcut.controller.util

import android.content.Context
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_SHOW_DATE
import android.text.format.DateUtils.FORMAT_SHOW_YEAR
import com.smoothapp.notionshortcut.controller.exception.WrongDateFormatException
import com.smoothapp.notionshortcut.model.entity.NotionDateTime
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


object NotionDateTimeUtil {

    fun convertDateTimeToString(notionDateTime: NotionDateTime): String? {
        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
        val date = Date(notionDateTime.getTimeMillis())
        return sf.format(date)
    }

    fun convertDateLongUTCToDefaultLocal(dateLong: Long): Long{
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val timeAsUTCString = sdf.format(dateLong)

        sdf.timeZone = TimeZone.getDefault()
        val dateAsDefaultLocal = sdf.parse(timeAsUTCString)

        return dateAsDefaultLocal?.time?: throw WrongDateFormatException("UTC to default")
    }

    fun getDisplayDateString(context: Context, dateLong: Long?): String {
        return when(dateLong){
            null -> "undefined"
            else -> DateUtils.formatDateTime(context, dateLong,
                FORMAT_SHOW_YEAR or FORMAT_SHOW_DATE
            )
        }
    }

    fun getDisplayTimeString(hour: Long?, minute: Long?): String {
        return when(hour == null || minute == null){
            true -> "undefined"
            else -> String.format("%2d:%02d", hour, minute)
        }
    }

    fun getDisplayTimeString(hour: Int, minute: Int): String {
        return getDisplayTimeString(hour.toLong(), minute.toLong())
    }

    fun getDisplayDateTimeToDateTimeString(fromNotionDateTime: NotionDateTime?, toNotionDateTime: NotionDateTime?): String{
        val fromString: String = fromNotionDateTime?.convertToString() ?: return "set"
        val toString: String? = toNotionDateTime?.convertToString()
        var result = fromString
        if(toString != null){
            result += "→$toString"
        }
        return result
    }
}