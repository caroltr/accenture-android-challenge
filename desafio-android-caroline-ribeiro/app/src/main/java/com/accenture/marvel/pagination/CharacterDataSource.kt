package com.accenture.marvel.pagination

import androidx.paging.PageKeyedDataSource
import com.accenture.marvel.respository.RemoteRepository
import com.accenture.marvel.model.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins.onError
import io.reactivex.schedulers.Schedulers

class CharacterDataSource : PageKeyedDataSource<Int, Character>() {

    private val repository: RemoteRepository by lazy { RemoteRepository() }

    private var totalPages = 1
    private var currentPage: Long = 0

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        val disposable = repository.fetchCharacters() //currentPage + 1, true)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                currentPage = it.page.toLong()
//                totalPages = it.totalPages

                callback.onResult(it, 1, 2)
            }, {
                onError(it)
            })

        disposables.add(disposable)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Character>
    ) {
        val disposable = repository.fetchCharacters() //currentPage + 1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                currentPage = it.page.toLong()
                callback.onResult(it, params.key + 1)
            }, {
                onError(it)
            })

        disposables.add(disposable)
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Character>
    ) {

    }

    override fun invalidate() {
        super.invalidate()
        disposables.dispose()
    }

    companion object {
        const val PAGE_SIZE = 20
    }
}