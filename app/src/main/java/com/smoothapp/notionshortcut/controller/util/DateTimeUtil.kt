package com.smoothapp.notionshortcut.controller.util

import android.content.Context
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_SHOW_DATE
import android.text.format.DateUtils.FORMAT_SHOW_YEAR
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


object DateTimeUtil {
    class DateTime(
        var dateLong: Long? = null,
        var hourLong: Long? = null,
        var minuteLong: Long? = null
    ) {

        fun setDate(dateLong: Long?){
            this.dateLong = dateLong
        }

        fun setHour(hour: Long?){
            this.hourLong = hour
        }
        fun setHour(hour: Int){
            this.hourLong = hour.toLong()
        }

        fun setMinute(minute: Long?){
            this.minuteLong = minuteLong
        }
        fun setMinute(minute: Int){
            this.minuteLong = minute.toLong()
        }

        fun getOnlyDate() = DateTime(dateLong)
        fun getTimeMillis() : Long{
            var timeMillis = 0L
            if(dateLong != null) timeMillis += dateLong!!
            if(hourLong != null && minuteLong != null){
                timeMillis += hourLong!! * 60 * 60 * 1000
                timeMillis += minuteLong!! * 60 * 1000
            }
            return timeMillis
        }

        fun convertToString(): String? {
            return convertDateTimeToString(this)
        }
    }

    fun convertStringToDateTime(string: String?): DateTime? {
        if(string == null) return null
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
        var date: Date? = null
        try {
            date = sdf.parse(string)
        } catch (e: ParseException) {
            Log.e("parse", e.message.toString())
        }
        return when(date){
            null -> null
            else -> {
                val dateLong = getOnlyDate(date) ?: return null
                val hourLong = getOnlyHour(date)
                val minuteLong = getOnlyMinute(date)



                Log.d("", "dayLong ${getOnlyDate(date)}")
                Log.d("", "hourLong ${getOnlyHour(date)}")
                Log.d("", "minuteLong ${getOnlyMinute(date)}")
                DateTime(dateLong, hourLong, minuteLong)
            }
        }
    }

    fun getOnlyDate(date: Date): Long?{
        val sdf = SimpleDateFormat("yyyy-MM-ddXXX", Locale.getDefault())
        val dateString =sdf.format(date)
        Log.d("", "date: $dateString")
        var dayLong: Long? = null
        try {
            dayLong = sdf.parse(dateString).time
        } catch (e: ParseException){
            Log.e("parse", e.message.toString())
        }
        return dayLong
    }

    fun getOnlyHour(date: Date): Long{
        val sdf = SimpleDateFormat("HH", Locale.getDefault())
        val hourString =sdf.format(date)
        Log.d("", "hour: $hourString")
        return hourString.toLong()
    }

    fun getOnlyMinute(date: Date): Long{
        val sdf = SimpleDateFormat("mm", Locale.getDefault())
        val minuteString =sdf.format(date)
        Log.d("", "minute: $minuteString")
        return minuteString.toLong()
    }


    fun convertDateTimeToString(dateTime: DateTime): String? {
        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
//        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        if (dateTime.dateLong == null) return null
        val date = Date(dateTime.getTimeMillis())

        return sf.format(date)
    }


    fun convertDateLongUTCToDefaultLocal(dateLong: Long): Long{
        val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        val timeAsUTCString = sdf.format(dateLong)

        sdf.timeZone = TimeZone.getDefault()
        val dateAsDefaultLocal = sdf.parse(timeAsUTCString)

        return dateAsDefaultLocal.time
    }

    fun getDisplayDateString(context: Context, dateLong: Long): String {
        return DateUtils.formatDateTime(context, dateLong,
            FORMAT_SHOW_YEAR or FORMAT_SHOW_DATE
        )
    }

    fun getDisplayTimeString(hour: Long, minute: Long): String {
        return String.format("%2d:%02d", hour, minute)
    }

    fun getDisplayTimeString(hour: Int, minute: Int): String {
        return getDisplayTimeString(hour.toLong(), minute.toLong())
    }

    fun getDisplayDateTimeToDateTimeString(fromDateTime: DateTime?, toDateTime: DateTime?): String{
        if(fromDateTime == null){
            return "set"
        }
        var result = fromDateTime.getTimeMillis().toString()
        if(toDateTime != null){
            result += "→"
            result += toDateTime.getTimeMillis().toString()
        }

        return result
    }


}