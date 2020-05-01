package com.accenture.marvel.character

import android.os.Bundle
import com.accenture.marvel.network.ApiFactory
import com.accenture.marvel.model.Character
import com.accenture.marvel.model.ComicResult
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.AspectRatio
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterPresenter(private val view: CharacterContract.View) : CharacterContract.Presenter {

    lateinit var id: String

    override fun start(extras: Bundle?) {
        extras?.let { it ->
            val character = it["details"] as? Character
            character?.let { c ->
                val url = "${c.thumbnail.path}/portrait_medium.${c.thumbnail.extension}"

                c.comics.items.first()
                view.showData(c.name, c.description, url)

                id = c.id
            }
        }
    }

    override fun getHqId() {
        val test = ApiFactory.marvelApi.getComicByCharacter(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->

                response.body()?.let { comic ->
                    val allResults: List<ComicResult> = comic.data.results

                    val sortedResults = allResults.sortedByDescending { r ->
                        r.prices.maxBy { it.price }?.price
                    }

                    val hq = sortedResults.first()
                    val url = "${hq.thumbnail.path}/${AspectRatio.MEDIUM.value}.${hq.thumbnail.extension}"

                    view.displayMostExpensiveHq(
                        Hq(
                            hq.title,
                            hq.description,
                            url,
                            hq.prices
                        )
                    )
                }
            }, {

            })
    }
}