package com.smoothapp.notionshortcut.controller.util

import android.content.Context
import android.text.format.DateUtils
import android.text.format.DateUtils.FORMAT_SHOW_DATE
import android.text.format.DateUtils.FORMAT_SHOW_YEAR
import android.util.Log
import com.smoothapp.notionshortcut.controller.exception.WrongDateFormatException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.math.min


object DateTimeUtil {
    class DateTime() {

        private val calendar: Calendar = Calendar.getInstance()

        constructor(string: String): this(){
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
            val date: Date?
            try {
                date = sdf.parse(string)
            } catch (e: ParseException) {
                Log.e("parse", e.message.toString())
                throw WrongDateFormatException("string to datetime")
            }
            calendar.time = date?: throw WrongDateFormatException("string to datetime")
        }

        fun getDateLong() = calendar.timeInMillis
        fun getHourLong() = calendar.get(Calendar.HOUR_OF_DAY).toLong()
        fun getMinuteLong() = calendar.get(Calendar.MINUTE).toLong()

        fun setDate(dateLong: Long?){
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            calendar.timeInMillis = dateLong!!
            calendar.set(Calendar.HOUR_OF_DAY, hour)
            calendar.set(Calendar.MINUTE, minute)
        }

        fun setHour(hour: Int){
            calendar.set(Calendar.HOUR_OF_DAY, hour)
        }

        fun setMinute(minute: Int){
            calendar.set(Calendar.MINUTE, minute)
        }

        fun getTimeMillis(): Long = calendar.timeInMillis

        fun convertToString(): String? {
            return convertDateTimeToString(this)
        }
    }


    private fun convertDateTimeToString(dateTime: DateTime): String? {
        val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
        val date = Date(dateTime.getTimeMillis())
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

    fun getDisplayDateTimeToDateTimeString(fromDateTime: DateTime?, toDateTime: DateTime?): String{
        val fromString: String = fromDateTime?.convertToString() ?: return "set"
        val toString: String? = toDateTime?.convertToString()
        var result = fromString
        if(toString != null){
            result += "→$toString"
        }
        return result
    }


}