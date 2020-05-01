package com.accenture.marvel.hq

import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView

interface HqContract {

    interface Presenter: BasePresenter {
    }

    interface View: BaseView<Presenter> {
    }
}