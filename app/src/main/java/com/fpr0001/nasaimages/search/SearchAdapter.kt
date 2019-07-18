package com.fpr0001.nasaimages.search

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.bind
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.databinding.ViewHolderImageBinding
import com.fpr0001.nasaimages.detail.DetailActivity
import com.fpr0001.nasaimages.models.ImageData
import com.fpr0001.nasaimages.utils.BaseAdapter
import com.fpr0001.nasaimages.utils.BaseAdapterImpl


open class SearchAdapterImpl(private val glide: RequestManager) : BaseAdapterImpl<ImageData, ImageViewHolder>(),
    SearchAdapter {

    companion object {
        private const val DELTA_TO_REQUEST_NEXT_PAGE = 20
    }

    private val requestOptions = RequestOptions()
        .error(R.drawable.image_placeholder)
        .placeholder(R.drawable.image_placeholder)
        .centerCrop()

    private val transitionFade =
        DrawableTransitionOptions.withCrossFade(DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build())

    override var loadMoreFunc: (() -> Unit)? = null

    override fun nextPageFetched(newItems: List<ImageData>) {
        if (newItems.isNotEmpty()) {
            list.addAll(newItems)
            notifyItemRangeInserted(list.size - newItems.size, newItems.size)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.view_holder_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageData = list[position]

        holder.binding.textViewTitle.text = imageData.title ?: holder.binding.root.context.getString(R.string.no_title)
        holder.setTextVisible(false)
        holder.itemView.setOnClickListener { DetailActivity.startActivity(holder.itemView.context, imageData) }

        glide
            .load(imageData.url)
            .transition(transitionFade)
            .apply(requestOptions)
            .listener(holder)
            .into(holder.binding.imageView)

        if (position == itemCount - DELTA_TO_REQUEST_NEXT_PAGE) {
            loadMoreFunc?.invoke()
        }
    }
}

interface SearchAdapter : BaseAdapter<ImageData> {
    fun nextPageFetched(newItems: List<ImageData>)
    var loadMoreFunc: (() -> Unit)?
}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), RequestListener<Drawable> {

    val binding = bind<ViewHolderImageBinding>(itemView)!!

    fun setTextVisible(visible: Boolean) {
        if (visible) {
            binding.gradientView.animate().apply { alpha(1.0f) }.start()
            binding.textViewTitle.animate().apply { alpha(1.0f) }.start()
        } else {
            binding.gradientView.alpha = 0.0f
            binding.textViewTitle.alpha = 0.0f
        }
    }

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: com.bumptech.glide.request.target.Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: com.bumptech.glide.request.target.Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        setTextVisible(true)
        return false
    }
}

