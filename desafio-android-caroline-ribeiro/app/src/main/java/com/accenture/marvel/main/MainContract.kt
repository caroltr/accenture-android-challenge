package com.accenture.marvel.main

import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView

interface MainContract {

    interface Presenter: BasePresenter {

    }

    interface View: BaseView<Presenter> {

    }
}