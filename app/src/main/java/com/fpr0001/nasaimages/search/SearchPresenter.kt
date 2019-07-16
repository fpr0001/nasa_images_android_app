package com.fpr0001.nasaimages.search

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.fpr0001.nasaimages.utils.BasePresenter
import com.fpr0001.nasaimages.utils.MvpView
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider
import io.reactivex.rxkotlin.addTo

open class SearchPresenter(
    private val repository: ResponseRepository,
    schedulerProvider: SchedulerProvider,
    val adapter: SearchAdapter
) : BasePresenter<SearchMvpView>(schedulerProvider), SearchView.OnQueryTextListener {

    init {
        adapter.loadMoreFunc = { fetchMedias(false) }
    }

    private var page = 1
    private var isLoading = false
    private var hasReachedDataLimit = false

    companion object {
        private const val KEY_PAGE = "keyPage"
        private const val KEY_DATA_LIMIT = "keyDataLimit"
    }

    override fun onSaveInstanceState(state: Bundle) {
        state.putInt(KEY_PAGE, page)
        state.putBoolean(KEY_DATA_LIMIT, hasReachedDataLimit)
        repository.cachedImageDataList = adapter.list
    }

    override fun onRestoreInstanceState(state: Bundle) {
        page = state.getInt(KEY_PAGE)
        hasReachedDataLimit = state.getBoolean(KEY_DATA_LIMIT)
        adapter.list = repository.cachedImageDataList
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        fetchMedias(true)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    fun fetchMedias(fromScratch: Boolean) {

        if (isLoading) return
        if (!fromScratch && hasReachedDataLimit) return

        isLoading = true
        if (fromScratch) {
            page = 1
        }
        view?.showLoader()
        view?.hideErrorViews()
        schedulerProvider.async(repository.fetchImages(page, view?.getSearchQuery()?.toString() ?: ""))
            .subscribe { responseList, exception ->
                view?.hideLoader()
                if (exception != null) {
                    view?.showRandomErrorView()
                } else {
                    page++
                    hasReachedDataLimit = responseList.size != 100
                    if (fromScratch) {
                        adapter.refreshList(responseList)
                    } else {
                        adapter.nextPageFetched(responseList)
                    }
                    if (adapter.list.isEmpty()) {
                        view?.showEmptyListView()
                    } else {
                        view?.onMediasRetrieved()
                    }
                }
                isLoading = false
            }.addTo(compositeDisposable)
    }
}


interface SearchMvpView : MvpView {
    fun showEmptyListView()
    fun onMediasRetrieved()
    fun showRandomErrorView()
    fun hideErrorViews()
    fun getSearchQuery(): CharSequence?
}