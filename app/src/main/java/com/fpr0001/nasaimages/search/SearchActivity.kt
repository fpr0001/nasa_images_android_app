package com.fpr0001.nasaimages.search

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.utils.BaseAppCompatActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseAppCompatActivity(), SearchMvpView {

    @Inject
    lateinit var presenter: SearchPresenter

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = presenter.adapter

        swipeToRefresh.setOnRefreshListener { presenter.fetchMedias(true) }

        presenter.attachView(this)
        presenter.fetchMedias(true)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        searchView = menuItem?.actionView as SearchView
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.fetchMedias(true)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView?.setIconifiedByDefault(true)
        return true
    }

    override fun setupActionBar() {
        super.setupActionBar()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.app_name)
    }

    override fun showLoader() {
        if (!swipeToRefresh.isRefreshing) {
            swipeToRefresh.isRefreshing = true
        }
    }

    override fun hideLoader() {
        swipeToRefresh.isRefreshing = false
    }

    override fun hideErrorViews() {
        llError.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun onMediasRetrieved() {
    }

    override fun showRandomErrorView() {
        llError.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
//        binding.textViewErrorTitle.setText(R.string.random_error_title)
//        binding.textViewErrorSubtitle.setText(R.string.random_error_message)
//        binding.imageViewError.setImageResource(R.mipmap.stop_friend)
    }

    override fun showEmptyListView() {
        llError.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
//        binding.textViewErrorTitle.setText(R.string.empty_list_error_title)
//        binding.textViewErrorSubtitle.setText(R.string.empty_list_error_message)
//        binding.imageViewError.setImageResource(R.mipmap.loupe_friend)
    }

    override fun getSearchQuery(): String {
        return searchView?.query?.toString() ?: ""
    }
}