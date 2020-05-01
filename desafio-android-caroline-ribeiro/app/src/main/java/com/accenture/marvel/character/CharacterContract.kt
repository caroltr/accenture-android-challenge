package com.accenture.marvel.character

import android.os.Bundle
import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView
import com.accenture.marvel.model.Hq

interface CharacterContract {

    interface Presenter: BasePresenter {
        fun start(extras: Bundle?)
        fun getHqId()
    }

    interface View: BaseView<Presenter> {
        fun showData(name: String, description: String, avatarUrl: String)
        fun displayMostExpensiveHq(hq: Hq)
        fun showError()
    }
}