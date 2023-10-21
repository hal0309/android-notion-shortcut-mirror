package com.smoothapp.notionshortcut.controller.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateTimeUtil {
    class DateTime(
        var dateLong: Long? = null,
        var hourInt: Int? = null,
        var minuteInt: Int? = null
    ) {
        fun getOnlyDate() = DateTime(dateLong)
        fun getTimeMillis() : Long{
            var timeMillis = 0L
            if(dateLong != null) timeMillis += dateLong!!
            if(hourInt != null && minuteInt != null){
                timeMillis += (hourInt!! * 60 * 60 * 1000).toLong()
                timeMillis += (minuteInt!! * 60 * 1000).toLong()
            }
            return timeMillis
        }
    }


    fun convertDateTimeToString(dateTime: DateTime): String? {
        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
//        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        if (dateTime.dateLong == null) return null
        val date = Date(dateTime.getTimeMillis())

        return sf.format(date)
    }


    fun convertDateLongUTCToDefaultLocal(long: Long): Long{
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val timeAsUTCString = sdf.format(long)

        sdf.timeZone = TimeZone.getDefault()
        val dateAsDefaultLocal = sdf.parse(timeAsUTCString)

        return dateAsDefaultLocal.time
    }


}