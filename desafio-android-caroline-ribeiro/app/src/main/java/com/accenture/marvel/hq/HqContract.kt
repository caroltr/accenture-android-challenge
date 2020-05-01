package com.accenture.marvel.hq

import android.os.Bundle
import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView
import com.accenture.marvel.model.Hq

interface HqContract {

    interface Presenter: BasePresenter {
        fun start(extras: Bundle?)
    }

    interface View: BaseView<Presenter> {
        fun showData(hq: Hq)
    }
}