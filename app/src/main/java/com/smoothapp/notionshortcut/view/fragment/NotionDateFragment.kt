package com.smoothapp.notionshortcut.view.fragment

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import com.smoothapp.notionshortcut.controller.util.MaterialComponentUtil
import com.smoothapp.notionshortcut.controller.util.DateTimeUtil
import com.smoothapp.notionshortcut.databinding.FragmentNotionDateBinding
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotionDateFragment(private val property: NotionDatabasePropertyDate) : Fragment() {


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
            title.text = property.getName()
            setDisplayText()

            val commonPickerListener = createCommonPickerListener()

            fromDateContainer.setOnClickListener {
                if (!isPickerShowing) {
                    isPickerShowing = true
                    val picker = MaterialComponentUtil.createDatePicker(
                        listener = commonPickerListener,
                        toLong = property.getDateTo()?.getDateLong()
                    ).apply {
                        addOnPositiveButtonClickListener {
                            val defaultLocalDateLong =
                                DateTimeUtil.convertDateLongUTCToDefaultLocal(it)
//                            fromDateTime.setDate(defaultLocalDateLong)
                            property.updateFromDate(defaultLocalDateLong)
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
                            property.updateFromTime(hour, minute)
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
                        fromLong = property.getDateFrom()?.getDateLong()
                    ).apply {
                        addOnPositiveButtonClickListener {
                            val defaultLocalDateLong =
                                DateTimeUtil.convertDateLongUTCToDefaultLocal(it)
//                            toDateTime.setDate(defaultLocalDateLong)
                            property.updateToDate(defaultLocalDateLong)
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
                            property.updateToTime(hour, minute)
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

            timeCheckChanged(property.getIsTimeEnabled(), true)
            toCheckChanged(property.getIsToDateEnabled(), true)
            enableFromTimeSwitch.setOnClickListener {
                timeCheckChanged(enableFromTimeSwitch.isChecked)
            }
            enableToTimeSwitch.setOnClickListener {
                timeCheckChanged(enableToTimeSwitch.isChecked)
            }
            enableToSwitch.setOnClickListener {
                toCheckChanged(enableToSwitch.isChecked)
            }

            clearBtn.setOnClickListener {
                clear()
            }
            sendBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            isViewCreated = true
            initSelectList()
            return root
        }
    }

    private fun clear(){
        timeCheckChanged(false)
        toCheckChanged(false)
    }

    private fun toCheckChanged(isChecked: Boolean, skip: Boolean = false){
        binding.apply {
            property.updateIsToDateEnabled(isChecked)
            enableToSwitch.isChecked = isChecked
            enableToSwitch.isClickable = !isChecked

            val alpha = if(isChecked) 1f else 0.2f
            arrowIcon.alpha = alpha
            toContainer.alpha = alpha

            when(isChecked){
                true -> {
                    enableToSwitch.hide(skip)
                }
                false -> {
                    enableToSwitch.show()
                }
            }
        }
    }

    private fun timeCheckChanged(isChecked: Boolean, skip: Boolean = false){
        binding.apply {
            property.updateIsTimeEnabled(isChecked)
            enableFromTimeSwitch.isChecked = isChecked
            enableFromTimeSwitch.isClickable = !isChecked
            enableToTimeSwitch.isChecked = isChecked
            enableToTimeSwitch.isClickable = !isChecked

            val alpha = if(isChecked) 1f else 0.2f
            fromTimeIcon.alpha = alpha
            toTimeIcon.alpha = alpha

            when(isChecked){
                true -> {
                    enableFromTimeSwitch.hide(skip)
                    enableToTimeSwitch.hide(skip)
                }
                false -> {
                    enableFromTimeSwitch.show()
                    enableToTimeSwitch.show()
                }
            }
        }
    }

    private fun View.hide(skip: Boolean = false) {
        ObjectAnimator.ofFloat(this, "alpha", 0f).apply {
            startDelay = if(skip) 0 else 200
            duration = if(skip) 0 else 200
            start()
        }
    }

    private fun View.show() {
        alpha = 1f
    }

    private fun setDisplayText() {
        binding.apply {
            fromDateText.text = DateTimeUtil.getDisplayDateString(
                requireContext(),
                property.getDateFrom()?.getDateLong()
            )
            fromTimeText.text = DateTimeUtil.getDisplayTimeString(property.getDateFrom()?.getHourLong(), property.getDateFrom()?.getMinuteLong())

            toDateText.text = DateTimeUtil.getDisplayDateString(
                requireContext(),
                property.getDateTo()?.getDateLong()
            )
            toTimeText.text = DateTimeUtil.getDisplayTimeString(property.getDateTo()?.getHourLong(), property.getDateTo()?.getMinuteLong())
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
        listener?.onDateChanged(property)
    }

    interface Listener {
        fun onDateChanged(property: NotionDatabasePropertyDate)
    }

    companion object {
        @JvmStatic
        fun newInstance(property: NotionDatabasePropertyDate) = NotionDateFragment(property)
    }
}