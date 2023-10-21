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

class NotionDateFragment : Fragment() {


    private lateinit var binding: FragmentNotionDateBinding
    private var listener: Listener? = null

    private var isViewCreated = false
    private var isListInitialized = false
    private var isPickerShowing = false

    private var isTimeEnabled = false
    private var isToDateEnabled = false

    private val fromDateTime = DateTimeUtil.DateTime()
    private val toDateTime = DateTimeUtil.DateTime()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotionDateBinding.inflate(inflater, container, false)
        binding.apply {
            val commonPickerListener = createCommonPickerListener()

            fromDateContainer.setOnClickListener {
                if(!isPickerShowing){
                    isPickerShowing = true
                    val picker = MaterialComponentUtil.createDatePicker(listener = commonPickerListener).apply {
                        addOnPositiveButtonClickListener {
                            fromDateTime.dateLong = DateTimeUtil.convertDateLongUTCToDefaultLocal(it)
                            sendDateTime()
                        }
                    }
                    picker.show(parentFragmentManager, null)
                }
            }
            fromTimeContainer.setOnClickListener {
                if(!isPickerShowing){
                    isPickerShowing = true
                    val picker = MaterialComponentUtil.createTimePicker(requireContext(), listener = commonPickerListener).apply {
                        addOnPositiveButtonClickListener {
                            Log.d("", "$hour $minute")
                            fromDateTime.hourInt = hour
                            fromDateTime.minuteInt = minute
                            sendDateTime()
                        }
                    }
                    picker.show(parentFragmentManager, null)
                }
            }

            includeTimeCheckbox.setOnCheckedChangeListener { _, checked ->
                isTimeEnabled = checked
            }

            includeToDateCheckbox.setOnCheckedChangeListener { _, checked ->
                isToDateEnabled = checked
            }

            sendBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            isViewCreated = true
            initSelectList()
            return root
        }
    }

    fun createCommonPickerListener(): MaterialComponentUtil.PickerListener {
        return object : MaterialComponentUtil.PickerListener{
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
        when(isTimeEnabled) {
            true -> {
                when(isToDateEnabled) {
                    true -> listener?.onDateChanged(fromDateTime, toDateTime)
                    false -> listener?.onDateChanged(fromDateTime, null)
                }
            }
            false -> {
                when(isToDateEnabled) {
                    true -> listener?.onDateChanged(fromDateTime.getOnlyDate(), toDateTime.getOnlyDate())
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
        fun newInstance() = NotionDateFragment()
    }
}