package com.accenture.marvel.character

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.accenture.marvel.error.ErrorHandler
import com.accenture.marvel.model.Character
import com.accenture.marvel.model.Hq
import com.accenture.marvel.repository.RemoteRepository
import com.accenture.marvel.util.AspectRatio
import com.accenture.marvel.util.Extra
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class CharacterViewModel : ViewModel() {

    private var disposable: Disposable? = null
    private val controller = CharacterController()
    private val repository = RemoteRepository()
    private val errorHandler = ErrorHandler()
    lateinit var id: String

    private val _character by lazy {
        MutableLiveData<CharacterPresentation>()
    }

    private val _hqMostExpensive by lazy {
        MutableLiveData<Hq>()
    }

    val character: LiveData<CharacterPresentation>
        get() = _character

    val hqMostExpensive: LiveData<Hq>
        get() = _hqMostExpensive

    fun start(extras: Bundle?) {
        extras?.let { it ->
            val character = it[Extra.CHARACTER.value] as? Character
            character?.let { c ->
                val url = "${c.thumbnail.path}/${AspectRatio.MEDIUM.value}.${c.thumbnail.extension}"
                val char = CharacterPresentation(c.name, c.description, url)
                _character.postValue(char)
                id = c.id
            }
        }
    }

    fun getMostExpensiveHq() {

        disposable = repository.fetchComic(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ comics ->
                val hq = controller.getComicMaxPrice(comics)
                val url =
                    "${hq.thumbnail.path}/${AspectRatio.MEDIUM.value}.${hq.thumbnail.extension}"

                _hqMostExpensive.postValue(
                    Hq(
                        hq.title,
                        hq.description,
                        url,
                        hq.prices
                    )
                )

            }, {
                val message = errorHandler.getMessage(it)
//                view.showError(message)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}