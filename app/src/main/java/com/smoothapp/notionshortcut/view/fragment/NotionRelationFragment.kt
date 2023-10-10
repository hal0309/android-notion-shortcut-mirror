package com.smoothapp.notionshortcut.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smoothapp.notionshortcut.databinding.FragmentNotionRelationBinding
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate
import com.smoothapp.notionshortcut.view.adapter.NotionRelationListAdapter

class NotionRelationFragment : Fragment() {


    private lateinit var binding: FragmentNotionRelationBinding
    private var listener: Listener? = null
    private lateinit var unselectedList: MutableList<NotionPostTemplate.Relation>
    private lateinit var selectedList: MutableList<NotionPostTemplate.Relation>

    private lateinit var unselectedListAdapter: NotionRelationListAdapter
    private lateinit var selectedListAdapter: NotionRelationListAdapter

    private var isViewCreated = false
    private var isListInitialized = false
    private var canSelectMultiple = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotionRelationBinding.inflate(inflater, container, false)
        binding.apply {
            isViewCreated = true
            initSelectList()


            unselectedListAdapter = NotionRelationListAdapter(object : NotionRelationListAdapter.Listener{
                override fun onClickItem(relation: NotionPostTemplate.Relation) {
                    unselectedList.remove(relation)
                    if(!canSelectMultiple && selectedList.isNotEmpty()){
                        val removed = selectedList.removeAt(0)
                        unselectedList.add(removed)
                        unselectedList = unselectedList.sort()
                    }
                    selectedList.add(relation)
                    selectedList = selectedList.sort()
                    updateSelectList()
                }
            })
            selectedListAdapter = NotionRelationListAdapter(object : NotionRelationListAdapter.Listener{
                override fun onClickItem(relation: NotionPostTemplate.Relation) {
                    selectedList.remove(relation)
                    unselectedList.add(relation)
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

    private fun MutableList<NotionPostTemplate.Relation>.sort(): MutableList<NotionPostTemplate.Relation>{
        return sortedWith(compareBy{it.name}).toMutableList()
    }

//    fun setCanSelectMultiple(canSelectMultiple: Boolean){
//        this.canSelectMultiple = canSelectMultiple
//    }

    fun setListener(listener: Listener){
        this.listener = listener
    }

    fun setSelectList(unselectedList: List<NotionPostTemplate.Relation>, selectedList: List<NotionPostTemplate.Relation>){
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
        fun onSelectChanged(selectedList: List<NotionPostTemplate.Relation>)
    }

    companion object {
        @JvmStatic
        fun newInstance() = NotionRelationFragment()
    }
}