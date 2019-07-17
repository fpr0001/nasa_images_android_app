package com.fpr0001.nasaimages.utils

import androidx.recyclerview.widget.RecyclerView
import com.fpr0001.nasaimages.models.BaseModel

abstract class BaseAdapterImpl<T : BaseModel, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>(),
    BaseAdapter<T> {

    init {
        this.setHasStableIds(true)
    }

    override var list: MutableList<T> = mutableListOf()

    override fun refreshList(newItems: List<T>) {
        list.clear()
        list.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return list[position].id
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun asRecyclerAdapter() = this
}

interface BaseAdapter<T> {
    var list: MutableList<T>
    fun refreshList(newItems: List<T>)
    fun asRecyclerAdapter(): RecyclerView.Adapter<*>
}