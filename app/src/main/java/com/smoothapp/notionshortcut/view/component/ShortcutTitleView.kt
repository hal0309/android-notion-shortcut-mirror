package com.smoothapp.notionshortcut.view.component

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import android.widget.LinearLayout
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutTitleBinding

class ShortcutTitleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutTitleBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_title, this)
        binding = ViewShortcutTitleBinding.bind(this)
    }

    override fun getContents(): String{
        return binding.editTitle.text.toString()
    }

}