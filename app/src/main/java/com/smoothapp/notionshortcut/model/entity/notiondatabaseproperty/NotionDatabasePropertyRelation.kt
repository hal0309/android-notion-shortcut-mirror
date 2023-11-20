package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

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

    private fun hasContents(): Boolean{
        val size = contents.size
        if(size < SET_SIZE || size % SET_SIZE != 0) return false
        for (i in 0 until size / SET_SIZE ){
            if(contents[i*SET_SIZE + ID_INDEX].isNullOrEmpty()){ //ID部分に空があったらfalse
                return false
            }
        }
        return true
    }

    fun getRelationId(): List<String> = relationId

    fun getRelationName(): List<String?> = relationName

    companion object {
        private const val ID_INDEX = 0 // primary
        private const val NAME_INDEX = 1

        private const val SET_SIZE = 2
    }
}


