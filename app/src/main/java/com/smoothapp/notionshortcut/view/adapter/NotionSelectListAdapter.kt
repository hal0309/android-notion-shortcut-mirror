package com.smoothapp.notionshortcut.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smoothapp.notionshortcut.databinding.ItemNotionSelectBinding
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate

class NotionSelectListAdapter(val listener: Listener? = null) :
    ListAdapter<NotionPostTemplate.Select, NotionSelectListAdapter.Holder>(DIFF_UTIL_ITEM_CALLBACK) {

    class Holder(private val binding: ItemNotionSelectBinding, private val listener: Listener?) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(select: NotionPostTemplate.Select) {
            binding.apply {
                name.text = select.name
                root.setOnClickListener { listener?.onClickItem(select) }
                card.setCardBackgroundColor(select.color.getColor(card.context))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemNotionSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<NotionPostTemplate.Select>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    interface Listener {
        fun onClickItem(select: NotionPostTemplate.Select)
    }


}


/**
 * User の差分確認する
 */
val DIFF_UTIL_ITEM_CALLBACK = object : DiffUtil.ItemCallback<NotionPostTemplate.Select>() {
    override fun areContentsTheSame(
        oldItem: NotionPostTemplate.Select,
        newItem: NotionPostTemplate.Select
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: NotionPostTemplate.Select,
        newItem: NotionPostTemplate.Select
    ) = oldItem.name == newItem.name
}