package com.accenture.marvel.main

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.accenture.marvel.error.ErrorHandler
import com.accenture.marvel.model.Character
import com.accenture.marvel.pagination.CharacterDataSource.Companion.PAGE_SIZE
import com.accenture.marvel.pagination.DataSourceFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    private val errorHandler = ErrorHandler()
    private val dataSourceFactory = DataSourceFactory()

    override fun getCharacters() {
        val disposable = getPageListData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                view.showCharacters(characters)
            }, {
                val message = errorHandler.getMessage(it)
                view.showError(message)
            })
    }

    private fun getPageListData(): Observable<PagedList<Character>> {

        return RxPagedListBuilder(dataSourceFactory, getPageListConfig())
            .setInitialLoadKey(1)
            .setFetchScheduler(Schedulers.io())
            .setNotifyScheduler(AndroidSchedulers.mainThread())
            .buildObservable()
    }

    private fun getPageListConfig(): PagedList.Config {
        return PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .build()
    }

}