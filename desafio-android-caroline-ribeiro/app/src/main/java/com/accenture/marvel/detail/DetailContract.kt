package com.accenture.marvel.detail

import android.os.Bundle
import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView

interface DetailContract {

    interface Presenter: BasePresenter {
        fun start(extras: Bundle?)
    }

    interface View: BaseView<Presenter> {
        fun showData(detail: DetailModel)
    }
}