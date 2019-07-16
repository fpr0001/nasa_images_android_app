package com.fpr0001.nasaimages.utils

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>() {

    var list: MutableList<T> = mutableListOf()

    open fun refreshList(newItems: List<T>) {
        list.clear()
        list.addAll(newItems)
        notifyItemRangeChanged(0, newItems.size)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}