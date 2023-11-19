package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum


class NotionDatabasePropertyNumber(
    name: String,
    private var number: String?
) : NotionDatabaseProperty(NotionApiPropertyEnum.NUMBER, name, listOf()) {

    init {
        updateParentContents()
    }
    private fun updateParentContents(){
        setPropertyContents(listOf(number))
    }

    fun updateContents(number: String?){
        this.number = number
        updateParentContents()
    }

    fun hasContents(): Boolean{
        return contents.isNotEmpty() && !contents[0].isNullOrEmpty()
    }

    fun getNumber(): String? = number

//    fun getNumber(): String?{
//        return when(hasContents()){
//            false -> null
//            true -> contents[0]
//        }
//    }
}


