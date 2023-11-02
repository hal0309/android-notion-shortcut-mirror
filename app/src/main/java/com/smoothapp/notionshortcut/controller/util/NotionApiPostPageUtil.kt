package com.smoothapp.notionshortcut.controller.util

import android.util.Log
import com.smoothapp.notionshortcut.controller.provider.NotionApiProvider
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.entity.NotionApiPostPageObj
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabaseProperty
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyCheckbox
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyDate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyMultiSelect
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyNumber
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyRelation
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyRichText
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertySelect
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyStatus
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyTitle

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
                propertyString += when (getType()) {
                    NotionApiPropertyEnum.TITLE -> createPropertyTitleObject()
                    NotionApiPropertyEnum.RICH_TEXT -> createPropertyRichTextObject()
                    NotionApiPropertyEnum.NUMBER -> createPropertyNumberObject()
                    NotionApiPropertyEnum.CHECKBOX -> createPropertyCheckboxObject()
                    NotionApiPropertyEnum.SELECT -> createPropertySelectObject()
                    NotionApiPropertyEnum.MULTI_SELECT -> createPropertyMultiSelectObject()
                    NotionApiPropertyEnum.STATUS -> createPropertyStatusObject()
                    NotionApiPropertyEnum.RELATION -> createPropertyRelationObject()
                    NotionApiPropertyEnum.DATE -> createPropertyDateObject()
                }
            }
        }

        propertyString = propertyString.dropLast(1) + "}"

        Log.d("", propertyString)

        return propertyString
    }

    private fun NotionDatabaseProperty.createPropertyTitleObject(): String {
        this as NotionDatabasePropertyTitle
        return getTitle().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyTitle(getName(), it) + ","
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyRichTextObject(): String {
        this as NotionDatabasePropertyRichText
        return getRichText().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyRichText(getName(), it) + ","
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyNumberObject(): String {
        this as NotionDatabasePropertyNumber
        return getNumber().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyNumber(getName(), it) + ","
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyCheckboxObject(): String {
        this as NotionDatabasePropertyCheckbox
        return getCheckbox().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyCheckbox(getName(), it) + ","
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertySelectObject(): String {
        this as NotionDatabasePropertySelect
        return getSelectName().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertySelect(getName(), it, getSelectColor()) + ","  //todo 確認 colorを新規作成する場合などはどうなる？
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyMultiSelectObject(): String {
        this as NotionDatabasePropertyMultiSelect
        return getMultiSelectName().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyMultiSelect(getName(), it, getMultiSelectColor()) + ","
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyRelationObject(): String {
        this as NotionDatabasePropertyRelation
        return getRelationId().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyRelation(getName(), it) + ","
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyStatusObject(): String {
        this as NotionDatabasePropertyStatus
        return getStatusName().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyStatus(getName(), it) + ","
            }
        }
    }

    private fun NotionDatabaseProperty.createPropertyDateObject(): String {
        this as NotionDatabasePropertyDate
        return getDateFrom().let {
            when(it) {
                null -> ""
                else -> NotionApiPostPageObj.propertyDate(getName(), it, getDateTo()) + ","
            }
        }
    }

}