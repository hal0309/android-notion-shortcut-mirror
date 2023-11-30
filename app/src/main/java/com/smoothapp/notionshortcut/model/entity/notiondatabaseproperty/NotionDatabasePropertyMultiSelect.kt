package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.controller.exception.DifferentListSizeException
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum

class NotionDatabasePropertyMultiSelect(
    name: String,
    private var multiSelectName: List<String>,
    private var multiSelectColor: List<NotionColorEnum?>
) : NotionDatabaseProperty(NotionApiPropertyEnum.MULTI_SELECT, name, listOf()) {

    init {
        updateParentContents()
    }

    private fun updateParentContents() {
        val size = multiSelectName.size
        if(size != multiSelectColor.size) throw DifferentListSizeException("multiSelect color")

        val contents: MutableList<String?> = MutableList(size* SET_SIZE){null}
        for(i in 0 until size){
            contents[i* SET_SIZE + NAME_INDEX] = multiSelectName[i]
            contents[i* SET_SIZE + COLOR_INDEX] = multiSelectColor[i]?.getName()
        }
        setPropertyContents(contents)
    }

    fun updateContents(multiSelectName: List<String>, multiSelectColor: List<NotionColorEnum?>){
        this.multiSelectName = multiSelectName
        this.multiSelectColor = multiSelectColor
        updateParentContents()
    }

    fun getMultiSelectName(): List<String> = multiSelectName

    fun getMultiSelectColor(): List<NotionColorEnum?> = multiSelectColor

    // todo: IDの参照を必要に応じて実装
//    fun getMultiSelectId(): List<String>  = multiSelectId

    companion object {
        private const val NAME_INDEX = 0 // primary
        private const val COLOR_INDEX = 1
        private const val ID_INDEX = 2

        private const val SET_SIZE = 3
    }
}


