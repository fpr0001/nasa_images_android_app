package com.fpr0001.nasaimages.utils

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>() {

    var list: MutableList<T> = mutableListOf()
    var listDisplay = listOf<T>()

    open fun refreshList(newItems: List<T>) {
        listDisplay.size
        list.clear()
        list.addAll(newItems)
        listDisplay = list
        notifyItemRangeChanged(0, newItems.size)
    }

    override fun getItemCount(): Int {
        return listDisplay.size
    }
}