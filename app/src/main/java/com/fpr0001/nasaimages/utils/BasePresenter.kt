package com.fpr0001.nasaimages.utils

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : MvpView>(protected val schedulerProvider: SchedulerProvider) {

    open var view: T? = null
    protected val compositeDisposable = CompositeDisposable()

    fun attachView(mvpView: T) {
        view = mvpView
    }

    fun onDetach() {
        view = null
        compositeDisposable.clear()
    }

}

interface MvpView {

    fun showLoader()

    fun hideLoader()

    fun displayError(throwable: Throwable? = null)

}