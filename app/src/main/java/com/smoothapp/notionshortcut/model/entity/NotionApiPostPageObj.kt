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

    fun propertyRichText(name: String, content: String) = """
        "$name": {
            "rich_text": [
                {
                    "text": {
                        "content": "$content"
                    }
                }
            ]
        }
    """.trimIndent()

    fun propertyNumber(name: String, number: String) = """
        "$name": {
            "number": $number
        }
    """.trimIndent()


    fun propertyCheckbox(name: String, checked: String) = """
        "$name": {
            "checkbox": $checked
        }
    """.trimIndent()



    fun propertySelect(name: String, selectName: String) = """
        "$name": {
            "select": {
                "name": "$selectName"
            }
        }
    """.trimIndent()

    fun propertyMultiSelect(name: String, selectNameList: List<String>): String{
        var result = """
            "$name": {
                "multi_select": [
        """

        for(selectName in selectNameList){
            result += """
                {
                    "name": "$selectName"
                }
            """.trimIndent()

            result += ","
        }

        result = result.dropLast(1) + "]}"

        return result
    }

    fun propertyStatus(name: String, statusName: String) = """
        "$name": {
            "status": {
                "name": "$statusName"
            }
        }
    """.trimIndent()

    /*todo: relation has more への対応*/
    fun propertyRelation(name: String, relationIdList: List<String>): String{
        var result = """
            "$name": {
                "relation": [
        """

        for(relationId in relationIdList){
            result += """
                {
                    "id": "$relationId"
                }
            """.trimIndent()

            result += ","
        }

        result = result.dropLast(1) + "]}"

        return result
    }


    fun propertyDate(name: String, startEndList: List<String>): String{
        var result = """
            "$name": {
                "date": {
                    "start": "${startEndList[0]}"
        """

        if(startEndList.size == 2){
            result += """
                    ,
                    "end": "${startEndList[1]}"
            """
        }

        result += "}}"

        return result
    }


}