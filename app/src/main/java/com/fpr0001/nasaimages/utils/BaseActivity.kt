package com.fpr0001.nasaimages.utils

import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.fpr0001.nasaimages.R

abstract class BaseAppCompatActivity : AppCompatActivity(), MvpView {

    protected var progressBar: ProgressBar? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setupActionBar()
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
        setupActionBar()
        progressBar = findViewById(R.id.progress_bar)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        super.setContentView(view, params)
        setupActionBar()
        progressBar = findViewById(R.id.progress_bar)
    }

    open fun setupActionBar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun displayError(throwable: Throwable?) {
        if (throwable == null) {
            showToast()
        } else {
            showToast(throwable.message
                ?: getString(R.string.general_error_message))
        }
    }

    override fun showLoader() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideLoader() {
        progressBar?.visibility = View.GONE
    }
}