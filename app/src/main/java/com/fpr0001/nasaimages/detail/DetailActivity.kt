package com.fpr0001.nasaimages.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.models.ImageData
import com.fpr0001.nasaimages.utils.BaseAppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_detail.*
import java.text.SimpleDateFormat
import javax.inject.Inject

class DetailActivity : BaseAppCompatActivity() {

    companion object {

        private const val EXTRA_MODEL = "ExtraModel"

        fun startActivity(context: Context, model: ImageData) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_MODEL, model)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var glide: RequestManager

    private val requestOptions = RequestOptions()
        .error(R.drawable.image_placeholder)
        .placeholder(R.drawable.image_placeholder)
        .centerCrop()


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val model = intent.getParcelableExtra<ImageData>(EXTRA_MODEL)

        glide
            .load(model.url)
            .apply(requestOptions)
            .into(imageView)

        title = model.title
        textViewDescription.text = model.description
        textViewDate.text = String.format(
            getString(R.string.created_on),
            SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG).format(model.dateCreated)
        )
    }
}