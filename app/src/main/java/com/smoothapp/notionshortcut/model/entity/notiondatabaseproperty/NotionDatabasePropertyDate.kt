package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.controller.util.NotionDateTimeUtil
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionDateTime

class NotionDatabasePropertyDate(
    name: String,
    private var dateFrom: NotionDateTime?,
    private var dateTo: NotionDateTime?,
    private var optionalIsTimeEnabled: Boolean = false,
    private var optionalIsToDateEnabled: Boolean = false
) : NotionDatabaseProperty(NotionApiPropertyEnum.DATE, name, listOf()) {

    init {
        updateParentContents()
    }

    private fun updateParentContents(){
        val contents: MutableList<String?> = MutableList(SET_SIZE){null}
        contents[FROM_INDEX] = dateFrom?.convertToString()
        contents[TO_INDEX] = dateTo?.convertToString()
        contents[OPTIONAL_IS_TIME_ENABLED_INDEX] = optionalIsTimeEnabled.toString()
        contents[OPTIONAL_IS_TO_DATE_ENABLED_INDEX] = optionalIsToDateEnabled.toString()
        setPropertyContents(contents)
    }

    fun updateContents(dateFrom: NotionDateTime?, dateTo: NotionDateTime?){
        this.dateFrom = dateFrom
        this.dateTo = dateTo
        updateParentContents()
    }

    //todo: dateFromの初期状態がnullの場合の処理
    fun updateFromDate(dateLong: Long){
        dateFrom?.setDate(dateLong)
        updateParentContents()
    }

    fun updateToDate(dateLong: Long){
        dateTo?.setDate(dateLong)
        updateParentContents()
    }

    fun updateFromTime(hour: Int, minute: Int){
        dateFrom?.setHour(hour)
        dateFrom?.setMinute(minute)
        updateParentContents()
    }

    fun updateToTime(hour: Int, minute: Int){
        dateTo?.setHour(hour)
        dateTo?.setMinute(minute)
        updateParentContents()
    }

    fun updateIsTimeEnabled(isTimeEnabled: Boolean){
        optionalIsTimeEnabled = isTimeEnabled
        updateParentContents()
    }

    fun updateIsToDateEnabled(isToDateEnabled: Boolean){
        optionalIsToDateEnabled = isToDateEnabled
        updateParentContents()
    }

    fun getDateFrom(): NotionDateTime? = dateFrom

    fun getDateTo(): NotionDateTime? = dateTo

    fun getIsTimeEnabled(): Boolean = optionalIsTimeEnabled

    fun getIsToDateEnabled(): Boolean = optionalIsToDateEnabled

    companion object {
        private const val FROM_INDEX = 0 // primary
        private const val TO_INDEX = 1
        private const val OPTIONAL_IS_TIME_ENABLED_INDEX = 2
        private const val OPTIONAL_IS_TO_DATE_ENABLED_INDEX = 3

        private const val SET_SIZE = 4
    }
}


