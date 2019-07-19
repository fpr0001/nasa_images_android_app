package com.fpr0001.nasaimages

import com.fpr0001.nasaimages.models.ImageData
import com.fpr0001.nasaimages.search.SearchAdapter
import com.fpr0001.nasaimages.search.SearchMvpView
import com.fpr0001.nasaimages.search.SearchPresenterImpl.Companion.MAX_COUNT_PER_PAGE
import com.fpr0001.nasaimages.utils.ResponseRepository
import com.fpr0001.nasaimages.utils.SchedulerProviderTestImpl
import io.reactivex.Single
import io.reactivex.plugins.RxJavaPlugins
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SearchPresenterTest {

    lateinit var presenter: SearchPresenterImplForTests

    var schedulerProvider = SchedulerProviderTestImpl()

    @Mock
    lateinit var view: SearchMvpView

    @Mock
    lateinit var adapter: SearchAdapter

    @Mock
    lateinit var repository: ResponseRepository

    private val page = 1
    private val query = "a"

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        RxJavaPlugins.setErrorHandler {}
        presenter = SearchPresenterImplForTests(repository, schedulerProvider, adapter)
        presenter.attachView(view)
    }

    @After
    fun destroy() {
        presenter.destroy()
    }

    @Test
    fun shouldFetchMediasWhenSearchQueryIsSubmitted() {
        Mockito.`when`(repository.fetchImages(anyInt(), anyString()))
            .thenReturn(Single.error(RuntimeException()))
        val didHandleSearch = presenter.onQueryTextSubmit(query)
        verify(repository).fetchImages(page, query)
        verify(view).hideKeyboard()
        assert(didHandleSearch)
    }

    @Test
    fun shouldShowHideLoaderAndErrorViewsWhenMediasAreSuccessfullyFetched() {
        Mockito.`when`(repository.fetchImages(anyInt(), anyString()))
            .thenReturn(Single.just(emptyList()))
        presenter.onQueryTextSubmit(query)
        verify(view).showLoader()
        verify(view).hideEmptyListViewMessage()
        verify(view).hideLoader()
        verify(view).showEmptyListView()
    }

    @Test
    fun shouldShowHideLoaderAndErrorViewsWhenMediasFailedToBeFetched() {
        Mockito.`when`(repository.fetchImages(anyInt(), anyString()))
            .thenReturn(Single.error(RuntimeException()))
        presenter.onQueryTextSubmit(query)
        verify(view).showLoader()
        verify(view).hideEmptyListViewMessage()
        verify(view).hideLoader()
        verify(view).showRandomErrorView()
    }

    @Test
    fun shouldNotifyAdapterWhenMediasAreFetched() {
        val mockImageData = mock(ImageData::class.java)
        val mockedList = Array(MAX_COUNT_PER_PAGE) { mockImageData }.toList()
        Mockito.`when`(repository.fetchImages(anyInt(), anyString()))
            .thenReturn(Single.just(mockedList))
        presenter.onQueryTextSubmit(query)
        presenter.fetchMedias(false)
        presenter.fetchMedias(false)
        verify(adapter).refreshList(mockedList)
        verify(adapter, times(2)).nextPageFetched(mockedList)
    }
}