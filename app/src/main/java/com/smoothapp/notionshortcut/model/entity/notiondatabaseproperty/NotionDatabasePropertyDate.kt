package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyDate(
    name: String,
    dateFrom: String?,
    dateTo: String?
) : NotionDatabaseProperty(NotionApiPropertyEnum.DATE, name, listOf()) {

    init {
        val contents: MutableList<String?> = MutableList(SET_SIZE){null}
        contents[FROM_INDEX] = dateFrom
        contents[TO_INDEX] = dateTo
        setPropertyContents(contents)
    }
    private fun hasContents(): Boolean{
        return  contents.size == SET_SIZE && !contents[FROM_INDEX].isNullOrEmpty()
    }
    fun getDateFrom(): String?{
        return when(hasContents()){
            false -> null
            true -> contents[FROM_INDEX]
        }
    }

    fun getDateTo(): String?{
        return when(hasContents()){
            false -> null
            true -> contents[TO_INDEX]
        }
    }


    companion object {
        private const val FROM_INDEX = 0 // primary
        private const val TO_INDEX = 1

        private const val SET_SIZE = 2
    }
}


