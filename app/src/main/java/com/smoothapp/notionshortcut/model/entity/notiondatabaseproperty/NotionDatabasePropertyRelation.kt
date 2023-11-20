package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.controller.exception.DifferentListSizeException
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyRelation(
    name: String,
    private var relationId: List<String>,
    private var relationName: List<String?>
) : NotionDatabaseProperty(NotionApiPropertyEnum.RELATION, name, listOf()) {

    init {
        updateParentContents()
    }

    private fun updateParentContents() {
        val size = relationId.size
        if(size != relationName.size) throw DifferentListSizeException("relation name")

        val contents: MutableList<String?> = MutableList(size* SET_SIZE){null}
        for(i in 0 until size){
            contents[i* SET_SIZE + ID_INDEX] = relationId[i]
            contents[i* SET_SIZE + NAME_INDEX] = relationName[i]
        }
        setPropertyContents(contents)
    }

    fun updateContents(relationId: List<String>, relationName: List<String?>){
        this.relationId = relationId
        this.relationName = relationName
        updateParentContents()
    }

    fun getRelationId(): List<String> = relationId

    fun getRelationName(): List<String?> = relationName


    companion object {
        private const val ID_INDEX = 0 // primary
        private const val NAME_INDEX = 1

        private const val SET_SIZE = 2
    }
}


