package com.accenture.marvel.detail

import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView

interface DetailContract {

    interface Presenter: BasePresenter {

    }

    interface View: BaseView<Presenter> {

    }
}