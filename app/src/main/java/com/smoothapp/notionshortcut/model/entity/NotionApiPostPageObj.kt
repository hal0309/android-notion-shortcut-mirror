package com.smoothapp.notionshortcut.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object NotionApiPostPageObj {

    fun parentInDatabase(dbId: String) = """
        "parent": {
            "database_id": "$dbId" 
        }
    """.trimIndent()

    fun parentInPage(){

    }

    fun propertyTitle(name: String, title: String) = """
        "$name": {
            "title": [
                {
                    "text": {
                        "content": "$title"
                    }
                }
            ]
        }
    """.trimIndent()

    fun propertySelect(name: String, selectName: String) = """
        "$name": {
            "select": {
                "name": "$selectName"
            }
        }
    """.trimIndent()

}