package com.smoothapp.notionshortcut.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smoothapp.notionshortcut.controller.util.MaterialComponentUtil
import com.smoothapp.notionshortcut.controller.util.DateTimeUtil
import com.smoothapp.notionshortcut.databinding.FragmentNotionDateBinding

class NotionDateFragment(val fromDateTime: DateTimeUtil.DateTime, val toDateTime: DateTimeUtil.DateTime) : Fragment() {


    private lateinit var binding: FragmentNotionDateBinding
    private var listener: Listener? = null

    private var isViewCreated = false
    private var isListInitialized = false
    private var isPickerShowing = false

    private var isTimeEnabled = false
    private var isToDateEnabled = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotionDateBinding.inflate(inflater, container, false)
        binding.apply {
            setDisplayText()

            val commonPickerListener = createCommonPickerListener()

            fromDateContainer.setOnClickListener {
                if (!isPickerShowing) {
                    isPickerShowing = true
                    val picker = MaterialComponentUtil.createDatePicker(
                        listener = commonPickerListener,
                        toLong = toDateTime.dateLong
                    ).apply {
                        addOnPositiveButtonClickListener {
                            val defaultLocalDateLong =
                                DateTimeUtil.convertDateLongUTCToDefaultLocal(it)
                            fromDateTime.setDate(defaultLocalDateLong)
                            setDisplayText()

                            sendDateTime()
                        }
                    }
                    picker.show(parentFragmentManager, null)
                }
            }
            fromTimeContainer.setOnClickListener {
                if (!isPickerShowing) {
                    isPickerShowing = true
                    val picker = MaterialComponentUtil.createTimePicker(
                        requireContext(),
                        listener = commonPickerListener
                    ).apply {
                        addOnPositiveButtonClickListener {
                            Log.d("", "$hour $minute")
                            fromDateTime.setHour(hour)
                            fromDateTime.setMinute(minute)
                            setDisplayText()
                            sendDateTime()
                        }
                    }
                    picker.show(parentFragmentManager, null)
                }
            }
            toDateContainer.setOnClickListener {
                if (!isPickerShowing) {
                    isPickerShowing = true
                    val picker = MaterialComponentUtil.createDatePicker(
                        listener = commonPickerListener,
                        fromLong = fromDateTime.dateLong
                    ).apply {
                        addOnPositiveButtonClickListener {
                            val defaultLocalDateLong =
                                DateTimeUtil.convertDateLongUTCToDefaultLocal(it)
                            toDateTime.setDate(defaultLocalDateLong)
                            setDisplayText()
                            sendDateTime()
                        }
                    }
                    picker.show(parentFragmentManager, null)
                }
            }
            toTimeContainer.setOnClickListener {
                if (!isPickerShowing) {
                    isPickerShowing = true
                    val picker = MaterialComponentUtil.createTimePicker(
                        requireContext(),
                        listener = commonPickerListener
                    ).apply {
                        addOnPositiveButtonClickListener {
                            Log.d("", "$hour $minute")
                            toDateTime.setHour(hour)
                            toDateTime.setMinute(minute)
                            setDisplayText()
                            sendDateTime()
                        }
                    }
                    picker.show(parentFragmentManager, null)
                }
            }

//            includeTimeCheckbox.setOnCheckedChangeListener { _, checked ->
//                isTimeEnabled = checked
//            }
//
//            includeToDateCheckbox.setOnCheckedChangeListener { _, checked ->
//                isToDateEnabled = checked
//            }

            sendBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            isViewCreated = true
            initSelectList()
            return root
        }
    }

    fun setDisplayText() {
        binding.apply {
            fromDateText.text = DateTimeUtil.getDisplayDateString(
                requireContext(),
                fromDateTime.dateLong
            )
            fromTimeText.text = DateTimeUtil.getDisplayTimeString(fromDateTime.hourLong, fromDateTime.minuteLong)

            toDateText.text = DateTimeUtil.getDisplayDateString(
                requireContext(),
                toDateTime.dateLong
            )
            toTimeText.text = DateTimeUtil.getDisplayTimeString(toDateTime.hourLong, toDateTime.minuteLong)
        }
    }

    fun createCommonPickerListener(): MaterialComponentUtil.PickerListener {
        return object : MaterialComponentUtil.PickerListener {
            override fun onDismissed() {
                isPickerShowing = false
            }
        }
    }


    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun setSelectList(

    ) {
        isListInitialized = true
        initSelectList()
    }

    private fun initSelectList() {
        // viewCreate と listInitializeどちらでも呼び出し、後に呼ばれた方で実行される

    }

    private fun updateSelectList() {

    }

    private fun sendDateTime() {
        when (isTimeEnabled) {
            true -> {
                when (isToDateEnabled) {
                    true -> listener?.onDateChanged(fromDateTime, toDateTime)
                    false -> listener?.onDateChanged(fromDateTime, null)
                }
            }

            false -> {
                when (isToDateEnabled) {
                    true -> listener?.onDateChanged(
                        fromDateTime.getOnlyDate(),
                        toDateTime.getOnlyDate()
                    )

                    false -> listener?.onDateChanged(fromDateTime.getOnlyDate(), null)
                }
            }
        }
    }

    interface Listener {
        fun onDateChanged(fromDateTime: DateTimeUtil.DateTime?, toDateTime: DateTimeUtil.DateTime?)
    }

    companion object {
        @JvmStatic
        fun newInstance(fromDateTime: DateTimeUtil.DateTime?, toDateTime: DateTimeUtil.DateTime?)
            = NotionDateFragment(
                fromDateTime?: DateTimeUtil.DateTime(),
                toDateTime?: DateTimeUtil.DateTime()
            )
    }
}