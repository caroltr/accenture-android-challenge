package com.accenture.marvel.character

import android.os.Bundle
import com.accenture.marvel.network.model.Character

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
//        ApiFactory.marvelApi.getComicByCharacter(id)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe ({ response ->
//
//                            response.body()?.let { comic ->
//                                val allResults: List<ComicResult> = comic.data.results
//                                val prices: List<Double> = allResults.map { data ->
//                                    data.prices.maxBy { p -> p.price }?.price
//                                }.filterNotNull()
//
//                                val maxPrice = prices.maxBy { it }
//
//                                println("Max: $maxPrice")
//                }
//            }, {
//
//            })
    }
}