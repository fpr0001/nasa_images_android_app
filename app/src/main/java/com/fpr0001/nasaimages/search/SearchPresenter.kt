package com.fpr0001.nasaimages.search

import com.fpr0001.nasaimages.utils.BasePresenter
import com.fpr0001.nasaimages.utils.MvpView
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider
import io.reactivex.rxkotlin.addTo

open class SearchPresenter(
    private val repository: ResponseRepository,
    schedulerProvider: SchedulerProvider,
    val adapter: SearchAdapter
) : BasePresenter<SearchMvpView>(schedulerProvider) {

    init {
        adapter.loadMoreFunc = { fetchMedias(false) }
    }

    protected var page = 1
    private var isLoading = false
    private var hasReachedDataLimit = false

    fun fetchMedias(fromScratch: Boolean) {

        if (isLoading) return
        if (!fromScratch && hasReachedDataLimit) return

        isLoading = true
        if (fromScratch) {
            page = 1
        }
        view?.showLoader()
        view?.hideErrorViews()
        schedulerProvider.async(repository.fetchImages(page, view?.getSearchQuery()?:""))
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
                    if (adapter.listDisplay.isEmpty()) {
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
    fun getSearchQuery(): String
}