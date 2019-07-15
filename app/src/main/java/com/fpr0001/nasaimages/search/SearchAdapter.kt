package com.fpr0001.nasaimages.search

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.bind
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.databinding.ViewHolderImageBinding
import com.fpr0001.nasaimages.models.LinkResponse
import com.fpr0001.nasaimages.utils.BaseAdapter
import kotlinx.android.synthetic.main.view_holder_image.view.*

open class SearchAdapter(private val glide: RequestManager) : BaseAdapter<LinkResponse, ImageViewHolder>() {

    private val requestOptions = RequestOptions()
        .error(R.drawable.image_placeholder)
        .placeholder(R.drawable.image_placeholder)
        .centerCrop()

    var loadMoreFunc = {}

    fun nextPageFetched(newItems: List<LinkResponse>) {
        if (newItems.isNotEmpty()) {
            list.addAll(newItems)
            listDisplay = list
            notifyItemRangeInserted(listDisplay.size - newItems.size, newItems.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val obj = listDisplay[position]

        holder.binding.imageView.setImageResource(R.drawable.image_placeholder)

        holder.binding.textViewTitle.text = obj.prompt
        glide
            .load(obj.href!!)
            .apply(requestOptions)
//            .listener(object: RequestListener<Drawable> {
//                override fun onLoadFailed(
//                    e: GlideException?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    holder.binding.gradientView.visibility = View.GONE
//                    holder.binding.textViewTitle.visibility = View.GONE
//                    return true
//                }
//
//                override fun onResourceReady(
//                    resource: Drawable?,
//                    model: Any?,
//                    target: Target<Drawable>?,
//                    dataSource: DataSource?,
//                    isFirstResource: Boolean
//                ): Boolean {
//                    holder.binding.gradientView.visibility = View.VISIBLE
//                    holder.binding.textViewTitle.visibility = View.VISIBLE
//                    return true
//                }
//
//            })
            .into(holder.binding.root.imageView)

        if (position == itemCount - 10) {
            loadMoreFunc.invoke()
        }
    }
}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = bind<ViewHolderImageBinding>(itemView)!!

}