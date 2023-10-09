package com.smoothapp.notionshortcut.model.constant

import android.content.Context
import android.util.TypedValue
import com.smoothapp.notionshortcut.R

enum class NotionColorEnum(
    private val propertyName: String,
    private val attrId: Int
) {

    DEFAULT("default", R.attr.notionColorDefault),
    GRAY("gray", R.attr.notionColorGray),
    BROWN("brown", R.attr.notionColorBrown),
    ORANGE("orange", R.attr.notionColorOrange),
    YELLOW("yellow", R.attr.notionColorYellow),
    GREEN("green", R.attr.notionColorGreen),
    BLUE("blue", R.attr.notionColorBlue),
    PURPLE("purple", R.attr.notionColorPurple),
    PINK("pink", R.attr.notionColorPink),
    RED("red", R.attr.notionColorRed);

    fun getName(): String = propertyName

    fun getColor(context: Context): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attrId, typedValue, true)
        return typedValue.data
    }
}