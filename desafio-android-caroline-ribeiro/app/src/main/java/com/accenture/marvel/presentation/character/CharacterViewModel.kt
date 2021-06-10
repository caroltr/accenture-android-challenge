package com.accenture.marvel.presentation.character

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.accenture.marvel.data.model.Character
import com.accenture.marvel.data.repository.RemoteRepository
import com.accenture.marvel.domain.error.ErrorHandler
import com.accenture.marvel.presentation.character.model.CharacterPresentation
import com.accenture.marvel.presentation.character.model.Hq
import com.accenture.marvel.util.AspectRatio
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.Result
import com.accenture.marvel.util.getMaxPrice
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharacterViewModel @Inject constructor(
    private val repository: RemoteRepository
) : ViewModel() {

    private var disposable: Disposable? = null
    private val errorHandler = ErrorHandler()
    lateinit var id: String

    private val _character by lazy {
        MutableLiveData<CharacterPresentation>()
    }

    private val _hqMostExpensive by lazy {
        MutableLiveData<Result<Hq>>()
    }

    val character: LiveData<CharacterPresentation>
        get() = _character

    val hqMostExpensive: LiveData<Result<Hq>>
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
                val hq = comics.getMaxPrice()
                val url =
                    "${hq.thumbnail.path}/${AspectRatio.MEDIUM.value}.${hq.thumbnail.extension}"

                val result = Result.success(Hq(
                    hq.title,
                    hq.description,
                    url,
                    hq.prices
                ))
                _hqMostExpensive.postValue(result)

            }, {
                val message = errorHandler.getMessage(it)
                val result = Result.error<Hq>(message)
                _hqMostExpensive.postValue(result)
            })
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}