package com.fpr0001.nasaimages.detail

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
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

        fun startActivityWithTransitionAnimation(from: AppCompatActivity, view: View, model: ImageData) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                from,
                view,
                from.getString(R.string.transition_name)
            )
            val intent = Intent(from, DetailActivity::class.java)
            intent.putExtra(EXTRA_MODEL, model)
            from.startActivity(intent, options.toBundle())
        }
    }

    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val model = intent.getParcelableExtra<ImageData>(EXTRA_MODEL)

        glide
            .load(model.url)
            .apply(getRequestOptions())
            .into(imageView)

        title = model.title
        textViewDescription?.text = model.description
        textViewDate?.text = String.format(
            getString(R.string.created_on),
            SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG).format(model.dateCreated)
        )
    }

    private fun getRequestOptions(): RequestOptions {
        return RequestOptions()
            .error(R.drawable.image_placeholder)
            .placeholder(R.drawable.image_placeholder)
            .apply {
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> this.fitCenter()
                    else -> this.centerCrop()
                }
            }
    }
}