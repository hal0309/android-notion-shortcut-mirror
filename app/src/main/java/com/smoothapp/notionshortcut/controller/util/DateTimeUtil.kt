package com.smoothapp.notionshortcut.controller.util

import android.content.Context
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_SHOW_DATE
import android.text.format.DateUtils.FORMAT_SHOW_YEAR
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.min


object DateTimeUtil {
    class DateTime(
        dateLong: Long? = null,
        hourLong: Long? = null,
        minuteLong: Long? = null
    ) {

        val calendar = Calendar.getInstance()
        init {
            if(dateLong != null) calendar.timeInMillis = dateLong
            if(hourLong != null) calendar.set(Calendar.HOUR_OF_DAY, hourLong.toInt())
            if(minuteLong != null) calendar.set(Calendar.MINUTE, minuteLong.toInt())
        }

        fun getDateLong() = calendar.timeInMillis
        fun getHourLong() = calendar.get(Calendar.HOUR_OF_DAY).toLong()
        fun getMinuteLong() = calendar.get(Calendar.MINUTE).toLong()

        fun setDate(dateLong: Long?){
//            this.dateLong = dateLong
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            calendar.timeInMillis = dateLong!!
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
        }

        fun setHour(hour: Long?){
//            this.hourLong = hour
            calendar.set(Calendar.HOUR_OF_DAY, hour!!.toInt())
        }
        fun setHour(hour: Int){
//            this.hourLong = hour.toLong()
            calendar.set(Calendar.HOUR_OF_DAY, hour)
        }

        fun setMinute(minute: Long?){
//            this.minuteLong = minuteLong
            calendar.set(Calendar.MINUTE, minute!!.toInt())
        }
        fun setMinute(minute: Int){
//            this.minuteLong = minute.toLong()
            calendar.set(Calendar.MINUTE, minute)
        }

//        fun getOnlyDate() = DateTime(dateLong)
//        fun getTimeMillis() : Long{
//            var timeMillis = 0L
//            if(dateLong != null) timeMillis += dateLong!!
//            if(hourLong != null && minuteLong != null){
//                timeMillis += hourLong!! * 60 * 60 * 1000
//                timeMillis += minuteLong!! * 60 * 1000
//            }
//            return timeMillis
//        }

        fun getTimeMillis(): Long = calendar.timeInMillis

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


    fun convertDateTimeToString(dateTime: DateTime?): String? {
        if(dateTime == null) return null
        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
//        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
        if (dateTime.getDateLong() == null) return null
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

    fun getDisplayDateTimeToDateTimeString(fromDateTime: DateTime?, toDateTime: DateTime?): String{
        val fromString = convertDateTimeToString(fromDateTime) ?: return "set"
        val toString = convertDateTimeToString(toDateTime)
        var result = fromString
        if(toString != null){
            result += "→"
            result += toString
        }

        return result
    }


}