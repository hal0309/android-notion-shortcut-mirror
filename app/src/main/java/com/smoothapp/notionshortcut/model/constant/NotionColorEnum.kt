package com.smoothapp.notionshortcut.model.constant

import android.content.Context
import com.smoothapp.notionshortcut.R

enum class NotionColorEnum(
    private val propertyName: String,
    private val resourceId: Int
) {

    DEFAULT("default", R.color.notion_light_default),
    GRAY("gray", R.color.notion_light_gray),
    BROWN("brown", R.color.notion_light_brown),
    ORANGE("orange", R.color.notion_light_orange),
    YELLOW("yellow", R.color.notion_light_yellow),
    GREEN("green", R.color.notion_light_green),
    BLUE("blue", R.color.notion_light_blue),
    PURPLE("purple", R.color.notion_light_purple),
    PINK("pink", R.color.notion_light_pink),
    RED("red", R.color.notion_light_red);

    fun getName(): String = propertyName

    fun getColor(context: Context): Int {
        return context.getColor(resourceId)
    }
}