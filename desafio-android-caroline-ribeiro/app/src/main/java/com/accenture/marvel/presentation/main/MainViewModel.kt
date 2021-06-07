package com.accenture.marvel.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.accenture.marvel.data.model.Character
import com.accenture.marvel.data.pagination.CharacterDataSource.Companion.PAGE_SIZE
import com.accenture.marvel.data.pagination.DataSourceFactory
import com.accenture.marvel.domain.error.ErrorHandler
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val dataSourceFactory: DataSourceFactory
) : ViewModel() {

    private var disposable: Disposable? = null

    private val errorHandler = ErrorHandler()

    private val characters: MutableLiveData<PagedList<Character>> by lazy {
        MutableLiveData<PagedList<Character>>().also {
            loadCharacters()
        }
    }

    fun getCharacters(): LiveData<PagedList<Character>> {
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
        disposable?.dispose()
    }
}