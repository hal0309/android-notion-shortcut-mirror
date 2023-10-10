package com.smoothapp.notionshortcut.controller.util

import android.util.Log
import com.smoothapp.notionshortcut.controller.provider.NotionApiProvider
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionApiPostPageObj
import com.smoothapp.notionshortcut.model.entity.NotionDatabaseProperty

object NotionApiPostPageUtil {
    suspend fun postPageToDatabase(
        dbId: String,
        propertyList: List<NotionDatabaseProperty>
    ): String {
        val provider = NotionApiProvider()

        val requestBodyString = """
        {
            ${NotionApiPostPageObj.parentInDatabase(dbId)},
            ${createPostPageProperties(propertyList)}
        }
        """.trimIndent()

        return provider.postPage(requestBodyString).toString()
    }

    fun createPostPageProperties(propertyList: List<NotionDatabaseProperty>): String {
        var propertyString = """"properties": {"""

        for (prop in propertyList) {
            prop.apply {
                propertyString += when (type) {
                    NotionApiPropertyEnum.TITLE -> createPropertyTitleObject()
                    NotionApiPropertyEnum.RICH_TEXT -> createPropertyRichTextObject()
                    NotionApiPropertyEnum.NUMBER -> createPropertyNumberObject()
                    NotionApiPropertyEnum.CHECKBOX -> createPropertyCheckboxObject()
                    NotionApiPropertyEnum.SELECT -> createPropertySelectObject()
                    NotionApiPropertyEnum.MULTI_SELECT -> createPropertyMultiSelectObject()

                    NotionApiPropertyEnum.STATUS -> NotionApiPostPageObj.propertyStatus(
                        name,
                        contents[0]
                    )

                    NotionApiPropertyEnum.RELATION -> createPropertyRelationObject()

                    NotionApiPropertyEnum.DATE -> NotionApiPostPageObj.propertyDate(name, contents)
                }
            }
        }

        propertyString = propertyString.dropLast(1) + "}"

        Log.d("", propertyString)

        return propertyString
    }

    private fun List<String?>?.hasSingleItem(): Boolean {
        return when (this) {
            null -> false
            else -> isNotEmpty() && !get(0).isNullOrEmpty()
        }
    }

    private fun isSameSize(contents: List<String>, option: List<String?>): Boolean {
        return contents.size == option.size
    }

    private fun NotionDatabaseProperty.createPropertyTitleObject(): String {
        return when (contents.hasSingleItem()) {
            false -> ""
            else -> NotionApiPostPageObj.propertyTitle(name, contents[0]) + ","
        }
    }

    private fun NotionDatabaseProperty.createPropertyRichTextObject(): String {
        return when (contents.hasSingleItem()) {
            false -> ""
            else -> NotionApiPostPageObj.propertyRichText(name, contents[0]) + ","
        }
    }

    private fun NotionDatabaseProperty.createPropertyNumberObject(): String {
        return when (contents.hasSingleItem()) {
            false -> ""
            else -> NotionApiPostPageObj.propertyNumber(name, contents[0]) + ","
        }
    }

    private fun NotionDatabaseProperty.createPropertyCheckboxObject(): String {
        return when (contents.hasSingleItem()) {
            false -> ""
            else -> NotionApiPostPageObj.propertyCheckbox(name, contents[0]) + ","
        }
    }

    private fun NotionDatabaseProperty.createPropertySelectObject(): String {
        return when (contents.hasSingleItem()) {
            false -> ""
            else -> when (optionalColor.hasSingleItem()) {
                false -> NotionApiPostPageObj.propertySelect(name, contents[0], null) + ","
                else -> NotionApiPostPageObj.propertySelect(
                    name,
                    contents[0],
                    optionalColor!![0]
                ) + ","  //todo: 整理 optionalColorのnullはhasSingleItemで排除されるため!!でも良いが安全性に欠ける
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyMultiSelectObject(): String {
        return when (contents.hasSingleItem()) {
            false -> ""
            else -> when (optionalColor.hasSingleItem() && isSameSize(contents, optionalColor!!)) {
                false -> NotionApiPostPageObj.propertySelect(name, contents[0], null) + ","
                else -> NotionApiPostPageObj.propertyMultiSelect(
                    name,
                    contents,
                    optionalColor
                ) + "," //todo: 整理 optionalColorのnullはhasSingleItemで排除されるため!!でも良いが安全性に欠ける
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyRelationObject(): String {
        return when (contents.hasSingleItem()) {
            false -> ""
            else -> NotionApiPostPageObj.propertyRelation(name, contents) + ","
        }
    }

}