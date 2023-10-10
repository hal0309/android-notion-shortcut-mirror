package com.smoothapp.notionshortcut.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.databinding.ActivityShortcutBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.component.notion_shortcut.ShortcutRootView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutRelationView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.BaseShortcutSelectView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.ShortcutSelectView
import com.smoothapp.notionshortcut.view.fragment.NotionRelationFragment
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
                        NotionPostTemplate.Property(NotionApiPropertyEnum.RELATION, "リレーション")
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
                    addSelectBlock(property.name, listener = createSelectListener(NotionApiPropertyEnum.SELECT))
                }
                NotionApiPropertyEnum.MULTI_SELECT -> {
                    addMultiSelectBlock(property.name, listener = createSelectListener(NotionApiPropertyEnum.MULTI_SELECT))
                }
                NotionApiPropertyEnum.STATUS -> addStatusBlock(property.name)
                NotionApiPropertyEnum.RELATION -> {
                    addRelationBlock(property.name, listener = createRelationListener())
                }
                NotionApiPropertyEnum.DATE -> addDateBlock(property.name)
            }
        }
    }

    private suspend fun getSelectList() = withContext(Dispatchers.IO) {
        delay(500)
        return@withContext listOf(
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
    }

    private fun createSelectListener(type: NotionApiPropertyEnum) = object : BaseShortcutSelectView.Listener {
        override fun onClick(shortcutSelectView: BaseShortcutSelectView) {
            val fragment = NotionSelectFragment.newInstance().apply {
                when(type) {
                    NotionApiPropertyEnum.SELECT -> setCanSelectMultiple(false)
                    NotionApiPropertyEnum.MULTI_SELECT, NotionApiPropertyEnum.RELATION -> setCanSelectMultiple(true)
                    else -> {/* todo: exception */}
                }
                setListener(
                    object : NotionSelectFragment.Listener {
                        override fun onSelectChanged(selectedList: List<NotionPostTemplate.Select>) {
                            shortcutSelectView.setSelected(selectedList)
                        }
                    }
                )
                MainScope().launch {
                    val unselectedList = getSelectList().toMutableList()
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

    private suspend fun getRelationList() = withContext(Dispatchers.IO) {
        delay(500)
        return@withContext listOf(
            NotionPostTemplate.Relation("page1", "page1Id"),
            NotionPostTemplate.Relation("page2", "page2Id"),
            NotionPostTemplate.Relation("page3", "page3Id"),
            NotionPostTemplate.Relation("page4", "page4Id"),
            NotionPostTemplate.Relation("page5", "page5Id"),
            NotionPostTemplate.Relation("page6", "page6Id")
        )
    }

    private fun createRelationListener() = object : ShortcutRelationView.Listener {
        override fun onClick(shortcutRelationView: ShortcutRelationView) {
            val fragment = NotionRelationFragment.newInstance().apply {
                setListener(
                    object : NotionRelationFragment.Listener {
                        override fun onSelectChanged(selectedList: List<NotionPostTemplate.Relation>) {
                            shortcutRelationView.setSelected(selectedList)
                        }
                    }
                )
                MainScope().launch {
                    val unselectedList = getRelationList().toMutableList()
                    val selectedList = shortcutRelationView.getSelected()
                    unselectedList.removeAll(selectedList)
                    setSelectList(unselectedList, selectedList)
                }
            }
            supportFragmentManager.beginTransaction()
                .replace(binding.overlayContainer.id, fragment)
                .addToBackStack(null)
                .commit()
        }
    }


}