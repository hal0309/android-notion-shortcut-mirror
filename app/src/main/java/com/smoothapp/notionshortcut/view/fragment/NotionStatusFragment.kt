package com.smoothapp.notionshortcut.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smoothapp.notionshortcut.databinding.FragmentNotionSelectBinding
import com.smoothapp.notionshortcut.databinding.FragmentNotionStatusBinding
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.adapter.NotionSelectListAdapter

class NotionStatusFragment(private val title: String) : Fragment() {


    private lateinit var binding: FragmentNotionStatusBinding
    private var listener: Listener? = null

    private var selected: NotionPostTemplate.Select? = null
    private lateinit var toDoList: MutableList<NotionPostTemplate.Select>
    private lateinit var inProgressList: MutableList<NotionPostTemplate.Select>
    private lateinit var completeList: MutableList<NotionPostTemplate.Select>


    private lateinit var selectedListAdapter: NotionSelectListAdapter
    private lateinit var toDoListAdapter: NotionSelectListAdapter
    private lateinit var inProgressListAdapter: NotionSelectListAdapter
    private lateinit var completeListAdapter: NotionSelectListAdapter

    private var isViewCreated = false
    private var isListInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotionStatusBinding.inflate(inflater, container, false)
        binding.apply {
            title.text = this@NotionStatusFragment.title
            toDoListAdapter = NotionSelectListAdapter(object : NotionSelectListAdapter.Listener {
                override fun onClickItem(select: NotionPostTemplate.Select) {
                    selected = select
                    updateSelectList()
                }
            })
            inProgressListAdapter =
                NotionSelectListAdapter(object : NotionSelectListAdapter.Listener {
                    override fun onClickItem(select: NotionPostTemplate.Select) {
                        selected = select
                        updateSelectList()
                    }
                })
            completeListAdapter =
                NotionSelectListAdapter(object : NotionSelectListAdapter.Listener {
                    override fun onClickItem(select: NotionPostTemplate.Select) {
                        selected = select
                        updateSelectList()
                    }
                })
            selectedListAdapter =
                NotionSelectListAdapter(object : NotionSelectListAdapter.Listener {
                    override fun onClickItem(select: NotionPostTemplate.Select) {
                        selected = null
                        updateSelectList()
                    }
                })
            sendBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            isViewCreated = true
            initSelectList()
            return root
        }
    }

    private fun MutableList<NotionPostTemplate.Select>.sort(): MutableList<NotionPostTemplate.Select> {
        return sortedWith(compareBy { it.name }).toMutableList()
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun setSelectList(
        toDoList: MutableList<NotionPostTemplate.Select>,
        inProgressList: MutableList<NotionPostTemplate.Select>,
        completeList: MutableList<NotionPostTemplate.Select>,
        selected: NotionPostTemplate.Select?
    ) {
        this.toDoList = toDoList.toMutableList().sort()
        this.inProgressList = inProgressList.toMutableList().sort()
        this.completeList = completeList.toMutableList().sort()
        this.selected = selected
        isListInitialized = true
        initSelectList()
    }

    private fun initSelectList() {
        // viewCreate と listInitializeどちらでも呼び出し、後に呼ばれた方で実行される
        if (isViewCreated && isListInitialized) {
            binding.apply {
                selectedRecyclerView.apply {
                    adapter = selectedListAdapter
                    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                }
                toDoRecyclerView.apply {
                    adapter = toDoListAdapter
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }
                inProgressRecyclerView.apply {
                    adapter = inProgressListAdapter
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }
                completeRecyclerView.apply {
                    adapter = completeListAdapter
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }
                selectedListAdapter.submitList(selected?.let { listOf(it) })
                toDoListAdapter.submitList(toDoList)
                inProgressListAdapter.submitList(inProgressList)
                completeListAdapter.submitList(completeList)
            }
        }
    }

    private fun updateSelectList() {
        binding.apply {
            selectedListAdapter.submitList(selected?.let { listOf(it) })
            toDoListAdapter.submitList(toDoList)
            inProgressListAdapter.submitList(inProgressList)
            completeListAdapter.submitList(completeList)
            listener?.onSelectChanged(selected)
        }
    }

    interface Listener {
        fun onSelectChanged(selected: NotionPostTemplate.Select?)
    }

    companion object {
        @JvmStatic
        fun newInstance(title: String) = NotionStatusFragment(title)
    }
}