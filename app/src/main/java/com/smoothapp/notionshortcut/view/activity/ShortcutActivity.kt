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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ShortcutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShortcutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = this.getColor(R.color.transparent)

        binding.apply {
            root.setOnClickListener{
                val d = Date()
                val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
                val formatD = sf.format(d)
                MainScope().launch {
                    Log.d("", NotionApiPostPageUtil.postPageToDatabase(
                        "94f6ca48-d506-439f-9d2e-0fa7a2bcd5d4",
                        listOf(
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.TITLE,"名前", listOf("hoge")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.RICH_TEXT,"テキスト 1", listOf("こんにちは")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.NUMBER,"数値bar", listOf("30.5")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.CHECKBOX, "チェックボックス", listOf("true")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.SELECT, "セレクト", listOf("オプション1")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.MULTI_SELECT, "タグだよ", listOf("わ", "をおー")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.STATUS, "ステータス", listOf("Done")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.RELATION, "aiueoとのリレーション", listOf("ecd1c8b627f54ecca674a309b5826279", "f1d5a00e704f491080cf883d3815a6ba")),
                            NotionApiPostPageUtil.NotionProperty(NotionApiPropertyEnum.DATE, "日付", listOf(formatD, formatD))
                            )
                    ))
                }
            }
        }
    }
}