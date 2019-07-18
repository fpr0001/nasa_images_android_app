package com.fpr0001.nasaimages.detail

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
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

        const val EXTRA_MODEL = "ExtraModel"

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
            .transition(DrawableTransitionOptions.withCrossFade())
            .apply(getRequestOptions())
            .into(imageView as ImageView)

        title = model.title
        textViewDescription?.text = try {
            Html.fromHtml(model.description, Html.FROM_HTML_MODE_COMPACT)
        } catch (e: Throwable) {
            model.description
        }

        textViewDate?.text = String.format(
            getString(R.string.created_on),
            SimpleDateFormat.getDateInstance(SimpleDateFormat.LONG).format(model.dateCreated)
        )
    }

    private fun getRequestOptions(): RequestOptions {
        return RequestOptions()
            .apply {
                when (resources.configuration.orientation) {
                    Configuration.ORIENTATION_LANDSCAPE -> this.fitCenter()
                    else -> this.centerCrop()
                }
            }
    }
}