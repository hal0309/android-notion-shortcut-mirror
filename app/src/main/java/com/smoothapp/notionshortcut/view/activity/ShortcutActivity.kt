package com.smoothapp.notionshortcut.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.controller.provider.NotionApiProvider
import com.smoothapp.notionshortcut.controller.util.NotionApiPostPageUtil
import com.smoothapp.notionshortcut.databinding.ActivityShortcutBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ShortcutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShortcutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = this.getColor(R.color.transparent)

        binding.apply {
            root.setOnClickListener{
                MainScope().launch {
                    Log.d("", NotionApiPostPageUtil.postPageToDatabase(
                        "94f6ca48-d506-439f-9d2e-0fa7a2bcd5d4",
                        listOf(
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.TITLE,"名前", "hoge"),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.SELECT, "セレクト", "オプション1"),
                            )
                    ))
                }
            }
        }
    }
}