package com.smoothapp.notionshortcut.view.component.notionshortcut.mainelement

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ViewShortcutNumberBinding
import com.smoothapp.notionshortcut.model.entity.notiondatabaseproperty.NotionDatabasePropertyNumber

class ShortcutNumberView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, val property: NotionDatabasePropertyNumber
) : LinearLayout(context, attrs, defStyleAttr), ShortcutBlockInterface {

    private lateinit var binding: ViewShortcutNumberBinding

    init {
        init()
    }
    private fun init() {
        inflate(context, R.layout.view_shortcut_number, this)
        binding = ViewShortcutNumberBinding.bind(this)
        binding.apply {
            name.text = property.getName()
            content.setText(property.getNumber())
            content.doOnTextChanged { text, _, _, _ ->
                property.updateContents(text?.toString())
            }
        }
    }

    override fun getContents(): NotionDatabasePropertyNumber {
        return property
    }

}