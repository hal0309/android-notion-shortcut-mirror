package com.smoothapp.notionshortcut.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.smoothapp.notionshortcut.databinding.ItemNotionSelectBinding
import com.smoothapp.notionshortcut.model.entity.NotionPostTemplate

class NotionRelationListAdapter(val listener: Listener? = null) :
    ListAdapter<NotionPostTemplate.Relation, NotionRelationListAdapter.Holder>(RELATION_DIFF_UTIL_CALLBACK) {

    class Holder(private val binding: ItemNotionSelectBinding, private val listener: Listener?) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(relation: NotionPostTemplate.Relation) {
            binding.apply {
                name.text = relation.name
                root.setOnClickListener { listener?.onClickItem(relation) }
//                card.setCardBackgroundColor(relation.color.getColor(card.context))
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

    override fun submitList(list: List<NotionPostTemplate.Relation>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    interface Listener {
        fun onClickItem(relation: NotionPostTemplate.Relation)
    }


}


/**
 * User の差分確認する
 */
val RELATION_DIFF_UTIL_CALLBACK = object : DiffUtil.ItemCallback<NotionPostTemplate.Relation>() {
    override fun areContentsTheSame(
        oldItem: NotionPostTemplate.Relation,
        newItem: NotionPostTemplate.Relation
    ) = oldItem == newItem

    override fun areItemsTheSame(
        oldItem: NotionPostTemplate.Relation,
        newItem: NotionPostTemplate.Relation
    ) = oldItem.name == newItem.name
}