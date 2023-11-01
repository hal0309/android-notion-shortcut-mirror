package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import android.util.Log
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyMultiSelect(
    name: String,
    multiSelectName: List<String>,
    multiSelectColor: List<String>
) : NotionDatabaseProperty(NotionApiPropertyEnum.MULTI_SELECT, name, listOf()) {

    init {
        val size = multiSelectName.size
        if(size == multiSelectColor.size){
            val contents: MutableList<String?> = MutableList(size){null}
            for(i in 0 until size){
                contents[i* SET_SIZE + NAME_INDEX] = multiSelectName[i]
                contents[i* SET_SIZE + COLOR_INDEX] = multiSelectColor?.get(i)
            }
            setPropertyContents(contents)
        }
    }

    private fun hasContents(): Boolean{
        val size = contents.size
        if(size < SET_SIZE || size % SET_SIZE != 0) return false
        for (i in 0 until size / SET_SIZE ){
            if(contents[i*SET_SIZE].isNullOrEmpty()){ //NAME部分に空があったらfalse
                return false
            }
        }
        return true
    }


    fun getMultiSelectName(): List<String>?{
        return when(hasContents()){
            false -> null
            true -> {
                val contentsName = contents.filterIndexed { index, _ ->  index % SET_SIZE == NAME_INDEX}
                contentsName as List<String>
            }
        }
    }

    fun getMultiSelectColor(): List<String?>?{
        return when(hasContents()){
            false -> null
            true -> contents.filterIndexed { index, _ ->  index % SET_SIZE == COLOR_INDEX}
        }
    }

    fun getMultiSelectId(): List<String?>?{
        return when(hasContents()){
            false -> null
            true -> contents.filterIndexed { index, _ ->  index % SET_SIZE == ID_INDEX}
        }
    }

    companion object {
        private const val NAME_INDEX = 0 // primary
        private const val COLOR_INDEX = 1
        private const val ID_INDEX = 2

        private const val SET_SIZE = 3
    }
}


