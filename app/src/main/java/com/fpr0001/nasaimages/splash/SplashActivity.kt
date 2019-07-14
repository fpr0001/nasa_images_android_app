package com.fpr0001.nasaimages.splash

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.databinding.ActivitySplashBinding
import dagger.android.AndroidInjection

class SplashActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
    }
}