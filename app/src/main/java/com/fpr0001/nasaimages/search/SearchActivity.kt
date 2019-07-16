package com.fpr0001.nasaimages.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
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
    private var query: CharSequence? = null

    companion object {

        private const val KEY_LIST_STATE = "keyListState"
        private const val KEY_QUERY = "keyQuery"
        private const val KEY_LABEL_VISIBILITY = "keyLabelVisibility"
        private const val KEY_LABEL_TEXT = "keyLabelText"

        fun startActivity(context: Context) {
            val intent = Intent(context, SearchActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = presenter.adapter

        presenter.attachView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu?.findItem(R.id.action_search)
        searchView = menuItem?.actionView as SearchView
        searchView?.maxWidth = Integer.MAX_VALUE
        searchView?.setOnQueryTextListener(presenter)
        searchView?.setIconifiedByDefault(true)
        if (query != null) {
            menuItem.expandActionView()
            searchView?.setQuery(query, false)
            query = null
        }
        return true
    }

    override fun onSaveInstanceState(state: Bundle) {
        super.onSaveInstanceState(state)
        state.putParcelable(KEY_LIST_STATE, recyclerView.layoutManager?.onSaveInstanceState())
        state.putCharSequence(KEY_QUERY, searchView?.query)
        state.putCharSequence(KEY_LABEL_TEXT, textViewError.text)
        state.putInt(KEY_LABEL_VISIBILITY, textViewError.visibility)
        presenter.onSaveInstanceState(state)
    }

    override fun onRestoreInstanceState(state: Bundle?) {
        super.onRestoreInstanceState(state)
        if (state != null) {
            presenter.onRestoreInstanceState(state)
            textViewError.text = state.getCharSequence(KEY_LABEL_TEXT)
            textViewError.visibility = state.getInt(KEY_LABEL_VISIBILITY)
            state.getParcelable<Parcelable>(KEY_LIST_STATE)?.let {
                recyclerView.layoutManager?.onRestoreInstanceState(it)
            }
            query = state.getCharSequence(KEY_QUERY)
        }
    }

    override fun setupActionBar() {
        super.setupActionBar()
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = getString(R.string.app_name)
    }

    override fun hideErrorViews() {
        textViewError.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun onMediasRetrieved() {
    }

    override fun showRandomErrorView() {
        textViewError.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        textViewError.setText(R.string.general_error_message)
    }

    override fun showEmptyListView() {
        textViewError.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
        textViewError.setText(R.string.no_results_found)
    }

    override fun getSearchQuery(): String {
        return searchView?.query?.toString() ?: ""
    }
}