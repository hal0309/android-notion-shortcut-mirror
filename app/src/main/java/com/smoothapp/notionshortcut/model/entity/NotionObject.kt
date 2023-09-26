package com.smoothapp.notionshortcut.model.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object NotionObject {

    @Serializable
    data class Root(
        val results: List<PageOrDatabase>
    )

    @Serializable
    data class PageOrDatabase(
        @SerialName("object") val obj: String,
        val id: String,
        val parent: Parent
//        val url: String
    )

    @Serializable
    data class Parent(
        val type: String,
        @SerialName("page_id") val pageId: String? = null,
        @SerialName("database_id") val databaseId: String? = null
    )



}