package com.accenture.marvel.character

import android.os.Bundle
import com.accenture.marvel.error.ErrorHandler
import com.accenture.marvel.model.Character
import com.accenture.marvel.model.Hq
import com.accenture.marvel.repository.RemoteRepository
import com.accenture.marvel.util.AspectRatio
import com.accenture.marvel.util.Extra
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterPresenter(private val view: CharacterContract.View) : CharacterContract.Presenter {

    private val controller = CharacterController()
    private val repository = RemoteRepository()
    private val errorHandler = ErrorHandler()
    lateinit var id: String

    override fun start(extras: Bundle?) {
        extras?.let { it ->
            val character = it[Extra.CHARACTER.value] as? Character
            character?.let { c ->
                val url = "${c.thumbnail.path}/${AspectRatio.MEDIUM.value}.${c.thumbnail.extension}"
                view.showData(c.name, c.description, url)

                id = c.id
            }
        }
    }

    override fun getHqId() {
        val disposable = repository.fetchComic(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ comics ->
                val hq = controller.getComicMaxPrice(comics)
                val url =
                    "${hq.thumbnail.path}/${AspectRatio.MEDIUM.value}.${hq.thumbnail.extension}"

                view.displayMostExpensiveHq(
                    Hq(
                        hq.title,
                        hq.description,
                        url,
                        hq.prices
                    )
                )
            }, {
                val message = errorHandler.getMessage(it)
                view.showError(message)
            })
    }
}