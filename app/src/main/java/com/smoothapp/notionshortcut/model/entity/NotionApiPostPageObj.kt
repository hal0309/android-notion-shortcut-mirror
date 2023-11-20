package com.smoothapp.notionshortcut.model.entity

import com.smoothapp.notionshortcut.controller.util.DateTimeUtil
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
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


    fun propertyCheckbox(name: String, checked: Boolean) = """
        "$name": {
            "checkbox": $checked
        }
    """.trimIndent()



    fun propertySelect(name: String, selectName: String, color: NotionColorEnum?): String{
        var result = """
            "$name": {
                "select": {
                    "name": "$selectName"
        """
        if(color != null){
            result += """ ,"color": "${color.getName()}"  """
        }
        result += "}}"

        return result.trimIndent()
    }

    fun propertyMultiSelect(name: String, selectNameList: List<String>, colorList: List<NotionColorEnum?>?): String{
        var result = """
            "$name": {
                "multi_select": [
        """

        for(i in selectNameList.indices){
            val selectName = selectNameList[i]
            result += """
                {
                    "name": "$selectName"
            """

            if(colorList != null){
                val color = colorList[i]
                if(color != null) {
                    result += """ ,"color": "${color.getName()}" """
                }
            }
            result += "},"
        }

        result = result.dropLast(1) + "]}"

        return result
    }

    //todo: 要素追加時の引数(group, color)
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


    fun propertyDate(name: String, fromDate: DateTimeUtil.DateTime, toDate: DateTimeUtil.DateTime?): String{
        var result = """
            "$name": {
                "date": {
                    "start": "${fromDate.convertToString()}"
        """

        if(toDate != null){
            result += """
                    ,
                    "end": "${toDate.convertToString()}"
            """
        }

        result += "}}"

        return result
    }


}