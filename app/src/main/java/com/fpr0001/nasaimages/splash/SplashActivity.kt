package com.fpr0001.nasaimages.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.databinding.ActivitySplashBinding
import com.fpr0001.nasaimages.search.SearchActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        imageView.postDelayed({ SearchActivity.startActivity(this) }, 3000)
    }
}