package com.fpr0001.nasaimages.utils

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenterImpl<T : MvpView>(protected val schedulerProvider: SchedulerProvider) : BasePresenter<T> {

    protected var view: T? = null
    protected val compositeDisposable = CompositeDisposable()

    override fun attachView(mvpView: T) {
        view = mvpView
    }

    override fun destroy() {
        view = null
        compositeDisposable.clear()
    }

    override fun onSaveInstanceState(state: Bundle) {
    }

    override fun onRestoreInstanceState(state: Bundle) {
    }

}

interface BasePresenter<T : MvpView> {
    fun destroy()
    fun attachView(mvpView: T)
    fun onSaveInstanceState(state: Bundle)
    fun onRestoreInstanceState(state: Bundle)
}