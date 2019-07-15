package com.fpr0001.nasaimages.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.databinding.ViewHolderImageBinding
import com.fpr0001.nasaimages.models.LinkResponse
import com.fpr0001.nasaimages.utils.BaseAdapter

open class SearchAdapter : BaseAdapter<LinkResponse, ImageViewHolder>() {

    var loadMoreFunc = {}

    fun nextPageFetched(newItems: List<LinkResponse>) {
        list.addAll(newItems)
        listDisplay = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val obj = listDisplay[position]

        Glide.with(holder.itemView.context)
            .load(obj.href!!)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .centerCrop())
            .into(holder.binding.root.findViewById(R.id.imageView))

        if (position == itemCount - 5) {
            loadMoreFunc.invoke()
        }
    }
}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = bind<ViewHolderImageBinding>(itemView)!!

}