package com.fpr0001.nasaimages

import com.fpr0001.nasaimages.models.ImageData
import com.fpr0001.nasaimages.search.SearchAdapter
import com.fpr0001.nasaimages.search.SearchPresenterImpl
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProvider

class SearchPresenterImplForTests(
    repository: ResponseRepository,
    schedulerProvider: SchedulerProvider,
    adapter: SearchAdapter
) : SearchPresenterImpl(repository, schedulerProvider, adapter) {

    override fun nextPageFetched(responseList: List<ImageData>) {
        adapter.nextPageFetched(responseList)
    }
}