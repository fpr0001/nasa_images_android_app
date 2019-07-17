package com.fpr0001.nasaimages.utils

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapterImpl<T, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>(), BaseAdapter<T> {

    override var list: MutableList<T> = mutableListOf()

    override fun refreshList(newItems: List<T>) {
        list.clear()
        list.addAll(newItems)
        notifyItemRangeChanged(0, newItems.size)
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