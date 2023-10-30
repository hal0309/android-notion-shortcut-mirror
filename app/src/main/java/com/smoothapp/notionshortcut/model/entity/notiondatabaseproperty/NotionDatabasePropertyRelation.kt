package com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty

import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum

class NotionDatabasePropertyRelation(
    name: String,
    contents: List<String?>
) : NotionDatabaseProperty(NotionApiPropertyEnum.RELATION, name, contents) {
    private fun hasContents(): Boolean{
        val size = contents.size
        if(size < SET_SIZE || size % SET_SIZE != 0) return false
        for (i in 0 until size / SET_SIZE ){
            if(contents[i*SET_SIZE].isNullOrEmpty()){ //ID部分に空があったらfalse
                return false
            }
        }
        return true
    }


    fun getRelationId(): List<String>?{
        return when(hasContents()){
            false -> null
            true -> {
                val contentsName = contents.filterIndexed { index, _ ->  index % SET_SIZE == ID_INDEX}
                contentsName as List<String>
            }
        }
    }

    fun getRelationName(): List<String?>?{
        return when(hasContents()){
            false -> null
            true -> contents.filterIndexed { index, _ ->  index % SET_SIZE == NAME_INDEX}
        }
    }


    companion object {
        private const val ID_INDEX = 0 // primary
        private const val NAME_INDEX = 1

        private const val SET_SIZE = 2
    }
}


