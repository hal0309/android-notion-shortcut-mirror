package com.smoothapp.notionshortcut.controller.util

import com.smoothapp.notionshortcut.controller.provider.NotionApiProvider
import com.smoothapp.notionshortcut.model.entity.NotionObject
import kotlinx.serialization.json.Json
object NotionApiUtil {
    suspend fun getAllNotionPageAndDatabase(): NotionObject.Root{
        val provider = NotionApiProvider()
        val json = Json{ignoreUnknownKeys = true}
        return json.decodeFromString(provider.getAllObjects())
    }

    class PageOrDatabaseTree(val me: NotionObject.PageOrDatabase){
        val children: MutableList<PageOrDatabaseTree> = mutableListOf()
    }

    fun createPageOrDatabaseTree(list: List<NotionObject.PageOrDatabase>, parent: NotionObject.PageOrDatabase? = null): List<PageOrDatabaseTree>{

        return when(parent){
            null -> {
                val root = NotionObject.PageOrDatabase("workspace", "", NotionObject.Parent(""))

                listOf(PageOrDatabaseTree(root).apply {
                    children.addAll(createPageOrDatabaseTree(list, root))
                })
            }
            else -> {
                val mList = list.toMutableList()
                val treeList = mutableListOf<PageOrDatabaseTree>()
                val childList = when(parent.obj){
                    "workspace" -> {
                        mList.filter { it.parent.type == "workspace" }
                    }
                    "page" -> {
                        mList.filter { it.parent.pageId == parent.id}
                    }
                    "database" -> {
                        mList.filter { it.parent.databaseId == parent.id}
                    }
                    else -> {
                        mutableListOf()
                    }
                }

                mList.removeAll(childList)

                for(child in childList){
                    val tree = PageOrDatabaseTree(child).apply {
                        children.addAll(createPageOrDatabaseTree(mList, child))
                    }
                    treeList.add(tree)
//                    Log.d("", tree.me.toString())
                }

                treeList
            }
        }
    }
}