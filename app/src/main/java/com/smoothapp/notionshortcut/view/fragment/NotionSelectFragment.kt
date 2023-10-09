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
import com.smoothapp.notionshortcut.model.constant.NotionColorEnum
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.adapter.NotionSelectListAdapter

class NotionSelectFragment : Fragment() {


    private lateinit var binding: FragmentNotionSelectBinding
    private var listener: Listener? = null
    private lateinit var unselectedList: MutableList<NotionPostTemplate.Select>
    private lateinit var selectedList: MutableList<NotionPostTemplate.Select>

    private lateinit var unselectedListAdapter: NotionSelectListAdapter
    private lateinit var selectedListAdapter: NotionSelectListAdapter

    private var isViewCreated = false
    private var isListInitialized = false
    private var canSelectMultiple = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotionSelectBinding.inflate(inflater, container, false)
        binding.apply {
            isViewCreated = true
            initSelectList()


            unselectedListAdapter = NotionSelectListAdapter(object : NotionSelectListAdapter.Listener{
                override fun onClickItem(select: NotionPostTemplate.Select) {
                    unselectedList.remove(select)
                    if(!canSelectMultiple && selectedList.isNotEmpty()){
                        val removed = selectedList.removeAt(0)
                        unselectedList.add(removed)
                        unselectedList = unselectedList.sort()
                    }
                    selectedList.add(select)
                    selectedList = selectedList.sort()
                    updateSelectList()
                }
            })
            selectedListAdapter = NotionSelectListAdapter(object : NotionSelectListAdapter.Listener{
                override fun onClickItem(select: NotionPostTemplate.Select) {
                    selectedList.remove(select)
                    unselectedList.add(select)
                    unselectedList = unselectedList.sort()
                    updateSelectList()
                }
            })
            sendBtn.setOnClickListener {
                parentFragmentManager.popBackStack()
            }

            return root
        }
    }

    private fun MutableList<NotionPostTemplate.Select>.sort(): MutableList<NotionPostTemplate.Select>{
        return sortedWith(compareBy{it.name}).toMutableList()
    }

    fun setCanSelectMultiple(canSelectMultiple: Boolean){
        this.canSelectMultiple = canSelectMultiple
    }

    fun setListener(listener: Listener){
        this.listener = listener
    }

    fun setSelectList(unselectedList: List<NotionPostTemplate.Select>, selectedList: List<NotionPostTemplate.Select>){
        this.unselectedList = unselectedList.toMutableList().sort()
        this.selectedList = selectedList.toMutableList().sort()
        isListInitialized = true
        initSelectList()
    }

    private fun initSelectList(){
        // viewCreate と listInitializeどちらでも呼び出し、後に呼ばれた方で実行される
        if(isViewCreated && isListInitialized) {
            binding.apply {
                unselectedRecyclerView.apply {
                    adapter = unselectedListAdapter
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }
                selectedRecyclerView.apply {
                    adapter = selectedListAdapter
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }
                unselectedListAdapter.submitList(unselectedList)
                selectedListAdapter.submitList(selectedList)

                Log.d("", unselectedList.toString())
                Log.d("", selectedList.toString())
            }
        }
    }

    private fun updateSelectList(){
        binding.apply {
            unselectedListAdapter.submitList(unselectedList)
            selectedListAdapter.submitList(selectedList)
            Log.e("", unselectedList.toString())
            listener?.onSelectChanged(selectedList)
        }
    }

    interface Listener {
        fun onSelectChanged(selectedList: List<NotionPostTemplate.Select>)
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotionSelectFragment()
    }
}