package com.accenture.marvel.main

import com.accenture.marvel.respository.RemoteRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {

    private val repository by lazy { RemoteRepository() }

    override fun getCharacters() {
        val disposable = repository.fetchCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                view.showCharacters(characters)
            }, {
                view.showError()
            })

    }

}