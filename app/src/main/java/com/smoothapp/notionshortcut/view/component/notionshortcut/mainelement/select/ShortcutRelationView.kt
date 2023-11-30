package com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement.select

import android.content.Context
import android.util.AttributeSet
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyRelation


class ShortcutRelationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, property: NotionDatabasePropertyRelation,
    selectedList: List<NotionPostTemplate.Select>? = null, listener: Listener? = null
) : BaseShortcutSelectView(context, attrs, defStyleAttr, property, selectedList, listener) {

    init {
        init()
    }

    private fun init() {

    }

    override fun getSelected(): List<NotionPostTemplate.Select> {
        property as NotionDatabasePropertyRelation
        val idList = property.getRelationId()
        val nameList = property.getRelationName()

        val selectedList = mutableListOf<NotionPostTemplate.Select>()
        for(i in idList.indices){
            selectedList.add(NotionPostTemplate.Select(
                    nameList[i]?: "", NotionColorEnum.DEFAULT, idList[i]
            ))
        }
        return selectedList
    }

    override fun setSelected(selectedList: List<NotionPostTemplate.Select>) {
        property as NotionDatabasePropertyRelation
        val idList = selectedList.map { it.id?: "" } // todo: nameからidをsearchする処理
        val nameList = selectedList.map { it.name } // todo: nameからidをsearchする処理
        property.updateContents(idList, nameList)
        applySelected()
    }

    override fun getContents(): NotionDatabasePropertyRelation {
        return property as NotionDatabasePropertyRelation
    }

}