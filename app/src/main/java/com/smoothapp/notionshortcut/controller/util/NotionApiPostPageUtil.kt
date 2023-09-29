package com.smoothapp.notionshortcut.controller.util

import android.util.Log
import com.smoothapp.notionshortcut.controller.provider.NotionApiProvider
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionApiPostPageObj

object NotionApiPostPageUtil {
    suspend fun postPageToDatabase(dbId: String, propertyList: List<NotionProperty>): String{
        val provider = NotionApiProvider()

        val requestBodyString = """
        {
            ${NotionApiPostPageObj.parentInDatabase(dbId)},
            ${createPostPageProperties(propertyList)}
        }
        """.trimIndent()

        return provider.postPage(requestBodyString).toString()
    }

    data class NotionProperty(
        val type: NotionApiPropertyEnum,
        val name: String,
        val content: String
    )

    fun createPostPageProperties(propertyList: List<NotionProperty>): String{
        var propertyString = """"properties": {"""

        for(prop in propertyList){
            prop.apply {
                propertyString += when(type){
                    NotionApiPropertyEnum.TITLE -> NotionApiPostPageObj.propertyTitle(name, content)
                    NotionApiPropertyEnum.SELECT -> NotionApiPostPageObj.propertySelect(name, content)
                }
            }
            propertyString += ","
        }

        propertyString = propertyString.dropLast(1) +  "}"

        Log.d("", propertyString)

        return propertyString
    }
}