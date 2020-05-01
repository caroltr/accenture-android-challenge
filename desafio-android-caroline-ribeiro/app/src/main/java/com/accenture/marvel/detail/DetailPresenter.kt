package com.accenture.marvel.detail

import android.os.Bundle
import com.accenture.marvel.network.model.Result

class DetailPresenter(private val view: DetailContract.View) : DetailContract.Presenter {

    override fun start(extras: Bundle?) {
        extras?.let { it ->
            val character = it["details"] as? Result
            character?.let { c ->
                val url = "${c.thumbnail.path}/portrait_medium.${c.thumbnail.extension}"
                val detail = DetailModel(c.name, url, c.description, c.comics.items, c.series.items, c.stories.items)
                view.showData(detail)
            }
        }
    }
}