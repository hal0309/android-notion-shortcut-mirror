package com.smoothapp.notionshortcut.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.smoothapp.notionshortcut.databinding.ItemNotionSelectBinding
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate

class NotionSelectAdapter(val context: Context, val selectList: List<NotionPostTemplate.Select>): BaseAdapter() {

    override fun getCount(): Int = selectList.size

    override fun getItem(pos: Int): Any = selectList[pos]

    override fun getItemId(pos: Int): Long = pos.toLong()

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup?): View {
        val select = selectList[pos]
        val binding = when(convertView){
            null -> {
                val inflater = LayoutInflater.from(context)
                ItemNotionSelectBinding.inflate(inflater, parent, false)
            }
            else -> {
                ItemNotionSelectBinding.bind(convertView)
            }
        }
        binding.apply {
            name.text = select.name
            return root
        }
    }
}