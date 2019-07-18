package com.fpr0001.nasaimages.search

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.widget.SearchView
import com.fpr0001.nasaimages.utils.*
import io.reactivex.rxkotlin.addTo

open class SearchPresenterImpl(
    private val repository: ResponseRepository,
    schedulerProvider: SchedulerProvider,
    final override val adapter: SearchAdapter
) : BasePresenterImpl<SearchMvpView>(schedulerProvider), SearchPresenter {

    init {
        adapter.loadMoreFunc = { fetchMedias(false) }
    }

    private var page = 1
    private var isLoading = false
    private var hasReachedDataLimit = false
    override var query: CharSequence? = null

    companion object {
        private const val KEY_PAGE = "keyPage"
        private const val KEY_QUERY = "keyQuery"
        private const val KEY_DATA_LIMIT = "keyDataLimit"
        private const val KEY_IS_LOADING = "keyIsLoading"
        const val MAX_COUNT_PER_PAGE = 100
    }

    override fun onSaveInstanceState(state: Bundle) {
        state.putInt(KEY_PAGE, page)
        state.putBoolean(KEY_DATA_LIMIT, hasReachedDataLimit)
        state.putCharSequence(KEY_QUERY, query)
        state.putBoolean(KEY_IS_LOADING, isLoading)
        repository.cachedImageDataList = adapter.list
    }

    override fun onRestoreInstanceState(state: Bundle) {
        page = state.getInt(KEY_PAGE)
        hasReachedDataLimit = state.getBoolean(KEY_DATA_LIMIT)
        query = state.getCharSequence(KEY_QUERY)
        adapter.list = repository.cachedImageDataList
        if (state.getBoolean(KEY_IS_LOADING)) {
            fetchMedias(false)
        }
    }

    fun fetchMedias(fromScratch: Boolean) {

        if (isLoading) return
        if (!fromScratch && hasReachedDataLimit) return

        isLoading = true
        if (fromScratch) {
            page = 1
        }
        view?.showLoader()
        view?.hideEmptyListViewMessage()
        schedulerProvider.async(repository.fetchImages(page, query?.toString() ?: ""))
            .subscribe { responseList, exception ->
                view?.hideLoader()
                if (exception != null) {
                    view?.showRandomErrorView()
                } else {
                    page++
                    hasReachedDataLimit = responseList.size != MAX_COUNT_PER_PAGE
                    if (fromScratch) {
                        adapter.refreshList(responseList)
                    } else {
                        Handler(Looper.getMainLooper()).post {adapter.nextPageFetched(responseList)}
                    }
                    if (adapter.list.isEmpty()) {
                        view?.showEmptyListView()
                    }
                }
                isLoading = false
            }.addTo(compositeDisposable)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        this.query = query
        fetchMedias(true)
        view?.hideKeyboard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}

interface SearchPresenter : BasePresenter<SearchMvpView>, SearchView.OnQueryTextListener {
    val query: CharSequence?
    val adapter: SearchAdapter
}