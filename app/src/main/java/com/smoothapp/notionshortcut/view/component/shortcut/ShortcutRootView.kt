package com.smoothapp.notionshortcut.view.component.shortcut

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutRootBinding

class ShortcutRootView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){

    private lateinit var binding: ViewShortcutRootBinding

    init {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_shortcut_root, this)
        binding = ViewShortcutRootBinding.bind(this)

        binding.apply {
            editTitle.setText("タイトルです")
        }
        addTitleBlock()
    }

    private fun addTitleBlock() {
        binding.blockContainer.addView(
            EditText(context).apply {
                hint = "Title"
                setBackgroundColor(resources.getColor(R.color.transparent, null))
            }
        )

    }

    fun addTextBlock() {

    }



}