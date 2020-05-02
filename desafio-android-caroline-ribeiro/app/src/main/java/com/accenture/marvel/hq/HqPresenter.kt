package com.accenture.marvel.hq

import android.os.Bundle
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.Extra

class HqPresenter(private val view: HqContract.View) : HqContract.Presenter {

    private val controller = HqController()

    override fun start(extras: Bundle?) {
        extras?.let {bundle ->
            val hq = bundle[Extra.HQ.value] as? Hq
            hq?.let {
                val price = controller.formatPrices(it.price)
                view.showData(it.title, it.description, price, it.coverUrl)
            }
        }
    }
}