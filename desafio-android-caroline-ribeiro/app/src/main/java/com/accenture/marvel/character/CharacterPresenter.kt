package com.accenture.marvel.character

import android.os.Bundle
import com.accenture.marvel.network.model.Result

class CharacterPresenter(private val view: CharacterContract.View) : CharacterContract.Presenter {

    override fun start(extras: Bundle?) {
        extras?.let { it ->
            val character = it["details"] as? Result
            character?.let { c ->
                val url = "${c.thumbnail.path}/portrait_medium.${c.thumbnail.extension}"
                val detail = CharacterModel(c.name, url, c.description, c.comics.items, c.series.items, c.stories.items)
                view.showData(detail)
            }
        }
    }
}