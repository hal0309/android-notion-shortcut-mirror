package com.smoothapp.notionshortcut.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.smoothapp.notionshortcut.R
import com.smoothapp.notionshortcut.controller.util.DateTimeUtil
import com.smoothapp.notionshortcut.controller.util.NotionApiPostPageUtil
import com.smoothapp.notionshortcut.databinding.ActivityShortcutBinding
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyEnum
import com.smoothapp.notionshortcut.model.constant.NotionApiPropertyStatusEnum
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.component.notion_shortcut.ShortcutRootView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutDateView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.ShortcutStatusView
import com.smoothapp.notionshortcut.view.component.notion_shortcut.main_element.select.BaseShortcutSelectView
import com.smoothapp.notionshortcut.view.fragment.NotionDateFragment
import com.smoothapp.notionshortcut.view.fragment.NotionSelectFragment
import com.smoothapp.notionshortcut.view.fragment.NotionStatusFragment
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

            shortcutRoot.apply{
                setTemplate(
                    NotionPostTemplate(
                        NotionPostTemplate.TemplateType.DATABASE,
                        listOf(
                            NotionPostTemplate.Property(NotionApiPropertyEnum.TITLE, "名前"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.RICH_TEXT, "テキスト 1"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.NUMBER, "数値bar"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.CHECKBOX, "チェックボックス"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.SELECT, "セレクト"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.MULTI_SELECT, "タグ"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.RELATION, "hoge"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.STATUS, "ステータス"),
                            NotionPostTemplate.Property(NotionApiPropertyEnum.DATE, "日付")
                        )
                    )
                )
                setListener(object : ShortcutRootView.Listener{
                    override fun onSendBtnClicked() {
                        val blockList = getBlockList()
                        Log.d("", blockList.toString())
                        for (b in blockList) {
                            val contents = b.getContents()
                            Log.d(
                                "",
                                "${contents.type} ${contents.name} ${contents.contents}"
                            )
                        }
                        MainScope().launch {
                            Log.d(
                                "", NotionApiPostPageUtil.postPageToDatabase(
                                    "94f6ca48-d506-439f-9d2e-0fa7a2bcd5d4",
                                    blockList.map{it.getContents()}
                                )
                            )
                        }
                    }
                })
            }

        }
    }

    private fun ShortcutRootView.setTemplate(template: NotionPostTemplate) {
        for (property in template.propertyList) {
            when (property.type) {
                NotionApiPropertyEnum.TITLE -> addTitleBlock(property)
                NotionApiPropertyEnum.RICH_TEXT -> addRichTextBlock(property)
                NotionApiPropertyEnum.NUMBER -> addNumberBlock(property)
                NotionApiPropertyEnum.CHECKBOX -> addCheckboxBlock(property)
                NotionApiPropertyEnum.SELECT -> {
                    addSelectBlock(property, listener = createSelectListener(property))
                }

                NotionApiPropertyEnum.MULTI_SELECT -> {
                    addMultiSelectBlock(property, listener = createSelectListener(property))
                }

                NotionApiPropertyEnum.STATUS -> {
                    addStatusBlock(property, createStatusListener(property))
                }

                NotionApiPropertyEnum.RELATION -> {
                    addRelationBlock(property, createSelectListener(property))
                }

                NotionApiPropertyEnum.DATE -> {
                    addDateBlock(property, createDateListener())
                }
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

                NotionApiPropertyEnum.RELATION -> listOf(
                    NotionPostTemplate.Select(
                        "リレーション確認1",
                        NotionColorEnum.DEFAULT,
                        "c12b6304652a443292ea47b73bee7b84"
                    ),
                    NotionPostTemplate.Select(
                        "リレーション確認2",
                        NotionColorEnum.DEFAULT,
                        "77b73b9fa06e4cf18eadb37b5ca713c8"
                    ),
                    NotionPostTemplate.Select(
                        "リレーション確認3",
                        NotionColorEnum.DEFAULT,
                        "652c65adba874f08a1fd7cc236d52b1f"
                    ),
                    NotionPostTemplate.Select(
                        "こいつがメイン",
                        NotionColorEnum.DEFAULT,
                        "ecd1c8b627f54ecca674a309b5826279"
                    )
                )

                NotionApiPropertyEnum.STATUS -> listOf(
                    NotionPostTemplate.Select(
                        "come soon",
                        NotionColorEnum.DEFAULT,
                        NotionApiPropertyStatusEnum.TO_DO.getName()
                    ),
                    NotionPostTemplate.Select(
                        "Not started",
                        NotionColorEnum.DEFAULT,
                        NotionApiPropertyStatusEnum.TO_DO.getName()
                    ),
                    NotionPostTemplate.Select(
                        "In progress",
                        NotionColorEnum.BLUE,
                        NotionApiPropertyStatusEnum.IN_PROGRESS.getName()
                    ),
                    NotionPostTemplate.Select(
                        "Done",
                        NotionColorEnum.ORANGE,
                        NotionApiPropertyStatusEnum.COMPLETE.getName()
                    )
                )

                else -> listOf()
            }
        }

    private fun createSelectListener(property: NotionPostTemplate.Property) =
        object : BaseShortcutSelectView.Listener {
            override fun onClick(baseShortcutSelectView: BaseShortcutSelectView) {
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
                                baseShortcutSelectView.setSelected(selectedList)
                            }
                        }
                    )
                    MainScope().launch {
                        val unselectedList = getSelectList(property).toMutableList()
                        val selectedList = baseShortcutSelectView.getSelected()
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

    private fun createStatusListener(property: NotionPostTemplate.Property) =
        object : ShortcutStatusView.Listener {
            override fun onClick(shortcutStatusView: ShortcutStatusView) {
                val fragment = NotionStatusFragment.newInstance().apply {
                    setListener(
                        object : NotionStatusFragment.Listener {
                            override fun onSelectChanged(selected: NotionPostTemplate.Select?) {
                                shortcutStatusView.setSelected(selected)
                            }
                        }
                    )
                    MainScope().launch {
                        val allStatusList = getSelectList(property)
                        val toDoList =
                            allStatusList.filter { it.id == NotionApiPropertyStatusEnum.TO_DO.getName() }
                                .toMutableList()
                        val inProgressList =
                            allStatusList.filter { it.id == NotionApiPropertyStatusEnum.IN_PROGRESS.getName() }
                                .toMutableList()
                        val completeList =
                            allStatusList.filter { it.id == NotionApiPropertyStatusEnum.COMPLETE.getName() }
                                .toMutableList()
                        val selected = shortcutStatusView.getSelected()
                        setSelectList(toDoList, inProgressList, completeList, selected)
                    }
                }
                supportFragmentManager.beginTransaction()
                    .add(binding.overlayContainer.id, fragment)
                    .addToBackStack(null)
                    .commit()
            }
        }

    private fun createDateListener() =
        object : ShortcutDateView.Listener {
            override fun onClick(shortcutDateView: ShortcutDateView) {
                val fragment = NotionDateFragment.newInstance().apply {
                    setListener(
                        object: NotionDateFragment.Listener {
                            override fun onDateChanged(
                                fromDateTime: DateTimeUtil.DateTime?,
                                toDateTime: DateTimeUtil.DateTime?
                            ) {
                                shortcutDateView.setDateTime(fromDateTime, toDateTime)
                            }

                        }
                    )
                }
                supportFragmentManager.beginTransaction()
                    .add(binding.overlayContainer.id, fragment)
                    .addToBackStack(null)
                    .commit()
            }

        }
}