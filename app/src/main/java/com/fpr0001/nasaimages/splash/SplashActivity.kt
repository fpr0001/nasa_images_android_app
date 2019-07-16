package com.fpr0001.nasaimages.splash

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.fpr0001.nasaimages.search.SearchActivity

class SplashActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SearchActivity.startActivity(this)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}