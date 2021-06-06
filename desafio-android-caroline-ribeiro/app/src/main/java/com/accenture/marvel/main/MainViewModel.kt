package com.accenture.marvel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.accenture.marvel.error.ErrorHandler
import com.accenture.marvel.model.Character
import com.accenture.marvel.pagination.CharacterDataSource.Companion.PAGE_SIZE
import com.accenture.marvel.pagination.DataSourceFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private lateinit var disposable: Disposable

    private val errorHandler = ErrorHandler()
    private val dataSourceFactory = DataSourceFactory()

    private val characters: MutableLiveData<List<Character>> by lazy {
        MutableLiveData<List<Character>>().also {
            loadCharacters()
        }
    }

    fun getCharacters(): LiveData<List<Character>> {
        return characters
    }

    private fun loadCharacters() {
        disposable = getPageListData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ charactersResult ->
                characters.postValue(charactersResult)
            }, {
                val message = errorHandler.getMessage(it)
//                view.showError(message)
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

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}