package com.accenture.marvel.pagination

import androidx.paging.PageKeyedDataSource
import com.accenture.marvel.respository.RemoteRepository
import com.accenture.marvel.model.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.plugins.RxJavaPlugins.onError
import io.reactivex.schedulers.Schedulers
import java.util.*

class CharacterDataSource : PageKeyedDataSource<Int, Character>() {

    private val repository: RemoteRepository by lazy { RemoteRepository() }

    private var totalPages = 1
    private var offset = 0

    private var disposables: CompositeDisposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        val disposable = repository.fetchCharacters(offset)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                offset += PAGE_SIZE
                totalPages = it.total

                callback.onResult(it.results, 1, 2)
            }, {
                onError(it)
            })

        disposables.add(disposable)
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, Character>
    ) {
        if (offset >= totalPages) {
            callback.onResult(Collections.emptyList(), null)
        } else {
            val disposable = repository.fetchCharacters(offset)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    offset += PAGE_SIZE

                    callback.onResult(it.results, params.key + 1)
                }, {
                    onError(it)
                })

            disposables.add(disposable)
        }
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