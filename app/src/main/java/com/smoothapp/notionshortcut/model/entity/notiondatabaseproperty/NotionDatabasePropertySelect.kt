package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum

class NotionDatabasePropertySelect(
    name: String,
    private var selectName: String?,
    private var selectColor: NotionColorEnum?
) : NotionDatabaseProperty(NotionApiPropertyEnum.SELECT, name, listOf()) {

    init {
        updateParentContents()
    }

    private fun updateParentContents() {
        val contents: MutableList<String?> = MutableList(SET_SIZE){null}
        contents[NAME_INDEX] = selectName
        contents[COLOR_INDEX] = selectColor?.getName()
        setPropertyContents(contents)
    }

    fun updateContents(selectName: String?, selectColor: NotionColorEnum?) {
        this.selectName = selectName
        this.selectColor = selectColor
        updateParentContents()
    }

    private fun hasContents(): Boolean{
        return  contents.size == SET_SIZE && !contents[NAME_INDEX].isNullOrEmpty()
    }

    fun getSelectName(): String?{
        return selectName
//        when(hasContents()){
//            false -> null
//            true -> contents[NAME_INDEX]
//        }
    }

    fun getSelectColor(): NotionColorEnum?{
        return selectColor
    }

//    fun getSelectColor(): String?{
//        return when(hasContents()){
//            false -> null
//            true -> contents[COLOR_INDEX]
//        }
//    }

    fun getSelectId(): String?{
        return when(hasContents()){
            false -> null
            true -> contents[ID_INDEX]
        }
    }

    companion object {
        private const val NAME_INDEX = 0 // primary
        private const val COLOR_INDEX = 1
        private const val ID_INDEX = 2

        private const val SET_SIZE = 3
    }
}


