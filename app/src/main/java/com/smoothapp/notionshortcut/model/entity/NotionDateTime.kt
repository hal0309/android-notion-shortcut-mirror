package com.smoothapp.notionshortcut.model.entity

import android.util.Log
import com.smoothapp.notionshortcut.controller.exception.WrongDateFormatException
import com.smoothapp.notionshortcut.controller.util.NotionDateTimeUtil
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class NotionDateTime() {

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
        return NotionDateTimeUtil.convertDateTimeToString(this)
    }
}