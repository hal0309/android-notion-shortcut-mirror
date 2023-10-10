package com.smoothapp.notionshortcut.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ActivityShortcutBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.component.notion_shortcut.ShortcutRootView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.BaseShortcutSelectView
import com.smoothapp.notionshortcut.view.fragment.NotionSelectFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
            root.setOnClickListener {
                val d = Date()
                val sf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())
                val formatD = sf.format(d)
//                MainScope().launch {
//                    Log.d("", NotionApiPostPageUtil.postPageToDatabase(
//                        "94f6ca48-d506-439f-9d2e-0fa7a2bcd5d4",
//                        listOf(
//                            NotionDatabaseProperty(NotionApiPropertyEnum.TITLE,"名前", listOf("hoge")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.RICH_TEXT,"テキスト 1", listOf("こんにちは")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.NUMBER,"数値bar", listOf("30.5")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.CHECKBOX, "チェックボックス", listOf("true")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.SELECT, "セレクト", listOf("オプション1")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.MULTI_SELECT, "タグだよ", listOf("わ", "をおー")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.STATUS, "ステータス", listOf("Done")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.RELATION, "aiueoとのリレーション", listOf("ecd1c8b627f54ecca674a309b5826279", "f1d5a00e704f491080cf883d3815a6ba")),
//                            NotionDatabaseProperty(NotionApiPropertyEnum.DATE, "日付", listOf(formatD, formatD))
//                            )
//                    ))
//                }
            }

            shortcutRoot.setTemplate(
                NotionPostTemplate(
                    NotionPostTemplate.TemplateType.DATABASE,
                    listOf(
                        NotionPostTemplate.Property(NotionApiPropertyEnum.TITLE, "名前"),
                        NotionPostTemplate.Property(NotionApiPropertyEnum.RICH_TEXT, "テキスト 1"),
                        NotionPostTemplate.Property(NotionApiPropertyEnum.NUMBER, "数値bar"),
                        NotionPostTemplate.Property(NotionApiPropertyEnum.CHECKBOX, "チェックボックス"),
                        NotionPostTemplate.Property(NotionApiPropertyEnum.SELECT, "セレクト"),
                        NotionPostTemplate.Property(NotionApiPropertyEnum.MULTI_SELECT, "タグ"),
                        NotionPostTemplate.Property(NotionApiPropertyEnum.RELATION, "hoge")
                    )
                )
            )

        }
    }

    private fun ShortcutRootView.setTemplate(template: NotionPostTemplate) {
        for (property in template.propertyList) {
            when (property.type) {
                NotionApiPropertyEnum.TITLE -> addTitleBlock(property.name)
                NotionApiPropertyEnum.RICH_TEXT -> addRichTextBlock(property.name)
                NotionApiPropertyEnum.NUMBER -> addNumberBlock(property.name)
                NotionApiPropertyEnum.CHECKBOX -> addCheckboxBlock(property.name)
                NotionApiPropertyEnum.SELECT -> {
                    addSelectBlock(property.name, listener = createSelectListener(property))
                }

                NotionApiPropertyEnum.MULTI_SELECT -> {
                    addMultiSelectBlock(property.name, listener = createSelectListener(property))
                }

                NotionApiPropertyEnum.STATUS -> addStatusBlock(property.name)
                NotionApiPropertyEnum.RELATION -> {
                    addRelationBlock(property.name, createSelectListener(property))
                }

                NotionApiPropertyEnum.DATE -> addDateBlock(property.name)
            }
        }
    }

    private suspend fun getSelectList(property: NotionPostTemplate.Property) =
        withContext(Dispatchers.IO) {
            delay(500)
            return@withContext when (property.type) {
                NotionApiPropertyEnum.SELECT, NotionApiPropertyEnum.MULTI_SELECT -> listOf(
                    NotionPostTemplate.Select("default", NotionColorEnum.DEFAULT),
                    NotionPostTemplate.Select("gray", NotionColorEnum.GRAY),
                    NotionPostTemplate.Select("brown", NotionColorEnum.BROWN),
                    NotionPostTemplate.Select("orange", NotionColorEnum.ORANGE),
                    NotionPostTemplate.Select("yellow", NotionColorEnum.YELLOW),
                    NotionPostTemplate.Select("green", NotionColorEnum.GREEN),
                    NotionPostTemplate.Select("blue", NotionColorEnum.BLUE),
                    NotionPostTemplate.Select("purple", NotionColorEnum.PURPLE),
                    NotionPostTemplate.Select("pink", NotionColorEnum.PINK),
                    NotionPostTemplate.Select("red", NotionColorEnum.RED)
                )
                else -> listOf( /* relation */
                    NotionPostTemplate.Select("リレーション確認1", NotionColorEnum.DEFAULT, "c12b6304652a443292ea47b73bee7b84"),
                    NotionPostTemplate.Select("リレーション確認2", NotionColorEnum.DEFAULT, "77b73b9fa06e4cf18eadb37b5ca713c8"),
                    NotionPostTemplate.Select("リレーション確認3", NotionColorEnum.DEFAULT, "652c65adba874f08a1fd7cc236d52b1f"),
                    NotionPostTemplate.Select("こいつがメイン", NotionColorEnum.DEFAULT, "ecd1c8b627f54ecca674a309b5826279")
                )
            }
        }

    private fun createSelectListener(property: NotionPostTemplate.Property) =
        object : BaseShortcutSelectView.Listener {
            override fun onClick(shortcutSelectView: BaseShortcutSelectView) {
                val fragment = NotionSelectFragment.newInstance().apply {
                    when (property.type) {
                        NotionApiPropertyEnum.SELECT -> setCanSelectMultiple(false)
                        NotionApiPropertyEnum.MULTI_SELECT, NotionApiPropertyEnum.RELATION -> setCanSelectMultiple(
                            true
                        )

                        else -> {/* todo: exception */
                        }
                    }
                    setListener(
                        object : NotionSelectFragment.Listener {
                            override fun onSelectChanged(selectedList: List<NotionPostTemplate.Select>) {
                                shortcutSelectView.setSelected(selectedList)
                            }
                        }
                    )
                    MainScope().launch {
                        val unselectedList = getSelectList(property).toMutableList()
                        val selectedList = shortcutSelectView.getSelected()
                        unselectedList.removeAll(selectedList)
                        setSelectList(unselectedList, selectedList)
                    }
                }
                supportFragmentManager.beginTransaction()
                    .add(binding.overlayContainer.id, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }



}