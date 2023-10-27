package com.smoothapp.notionshortcut.controller.util

import android.content.Context
import android.text.format.DateFormat
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

object MaterialComponentUtil {
    fun createTimePicker(context: Context, listener: PickerListener? = null): MaterialTimePicker {
        val isSystem24Hour = DateFormat.is24HourFormat(context)
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_24H else TimeFormat.CLOCK_12H
        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
//            .setHour(0)
//            .setMinute(0)
            .setTitleText("this is title")
            .setInputMode(MaterialTimePicker.INPUT_MODE_CLOCK)
            .build()
        if(listener != null){
            picker.apply {
                addOnPositiveButtonClickListener { listener.onPositiveButtonClicked() }
                addOnNegativeButtonClickListener { listener.onNegativeButtonClicked() }
                addOnCancelListener { listener.onCanceled() }
                addOnDismissListener { listener.onDismissed() }
            }
        }
        return picker
    }

    fun createDatePicker(listener: PickerListener? = null, fromLong: Long? = null, toLong: Long? = null): MaterialDatePicker<Long> {
        val constraintBuilder = CalendarConstraints.Builder()

        val constraints = when{
            fromLong != null -> constraintBuilder.setValidator(DateValidatorPointForward.from(fromLong)).build()
            toLong != null -> constraintBuilder.setValidator(DateValidatorPointBackward.before(toLong)).build()
            else -> null
        }
        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("this is title")
            .setCalendarConstraints(constraints)
            .build()
        if(listener != null){
            picker.apply {
                addOnPositiveButtonClickListener { listener.onPositiveButtonClicked() }
                addOnNegativeButtonClickListener { listener.onNegativeButtonClicked() }
                addOnCancelListener { listener.onCanceled() }
                addOnDismissListener { listener.onDismissed() }
            }
        }
        return picker
    }

    interface PickerListener {
        fun onPositiveButtonClicked(){}
        fun onNegativeButtonClicked(){}
        fun onCanceled(){}
        fun onDismissed(){}
    }
}