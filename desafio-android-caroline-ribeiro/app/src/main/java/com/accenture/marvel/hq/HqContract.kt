package com.accenture.marvel.hq

import android.os.Bundle
import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView

interface HqContract {

    interface Presenter: BasePresenter {
        fun start(extras: Bundle?)
    }

    interface View: BaseView<Presenter> {
        fun showData(title: String, description: String, price: String, coverUrl: String)
    }
}