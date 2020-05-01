package com.accenture.marvel.character

import android.os.Bundle
import com.accenture.marvel.model.Character
import com.accenture.marvel.model.Hq
import com.accenture.marvel.respository.RemoteRepository
import com.accenture.marvel.util.AspectRatio
import com.accenture.marvel.util.Extra
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterPresenter(private val view: CharacterContract.View) : CharacterContract.Presenter {

    private val repository by lazy { RemoteRepository() }
    lateinit var id: String

    override fun start(extras: Bundle?) {
        extras?.let { it ->
            val character = it[Extra.CHARACTER.value] as? Character
            character?.let { c ->
                val url = "${c.thumbnail.path}/${AspectRatio.MEDIUM.value}.${c.thumbnail.extension}"

                c.comics.items.first()
                view.showData(c.name, c.description, url)

                id = c.id
            }
        }
    }

    override fun getHqId() {
        val test = repository.fetchComic(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ comics ->

                val sortedResults = comics.sortedByDescending { r ->
                    r.prices.maxBy { it.price }?.price
                }

                val hq = sortedResults.first()
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
                view.showError()
            })
    }
}