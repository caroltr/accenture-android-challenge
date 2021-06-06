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
import io.reactivex.schedulers.Schedulers

class CharacterViewModel : ViewModel() {

    private val controller = CharacterController()
    private val repository = RemoteRepository()
    private val errorHandler = ErrorHandler()
    lateinit var id: String

    private lateinit var _character: MutableLiveData<CharacterPresentation>

    val character : LiveData<CharacterPresentation>
        get() = _character

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

    fun getHqId() {
        val disposable = repository.fetchComic(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ comics ->
                val hq = controller.getComicMaxPrice(comics)
                val url =
                    "${hq.thumbnail.path}/${AspectRatio.MEDIUM.value}.${hq.thumbnail.extension}"

//                view.displayMostExpensiveHq(
//                    Hq(
//                        hq.title,
//                        hq.description,
//                        url,
//                        hq.prices
//                    )
//                )
            }, {
                val message = errorHandler.getMessage(it)
//                view.showError(message)
            })
    }
}