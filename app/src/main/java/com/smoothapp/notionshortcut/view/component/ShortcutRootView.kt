package com.smoothapp.notionshortcut.view.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutRootBinding
import com.smoothapp.notionshortcut.databinding.ViewShortcutTitleBinding

class ShortcutRootView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){

    private lateinit var binding: ViewShortcutRootBinding

    private val blockList = mutableListOf<ShortcutBlockInterface>()

    init {
        init()
    }

    private fun init() {
        inflate(context, R.layout.view_shortcut_root, this)
        binding = ViewShortcutRootBinding.bind(this)
        addTitleBlock()
        addTitleBlock()
        addTitleBlock()
        binding.apply {
            sendBtn.setOnClickListener {
                Log.d("", blockList.toString())
                for(b in blockList){
                    Log.d("", b.getContents())
                }
            }
        }
    }

    private fun addTitleBlock() {
        binding.blockContainer.addView(
            ShortcutTitleView(context).apply {
                Log.e("", getContents())
                blockList.add(this)
            }
        )
    }

    fun addTextBlock() {

    }



}