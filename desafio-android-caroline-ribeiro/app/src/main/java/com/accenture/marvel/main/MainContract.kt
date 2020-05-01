package com.accenture.marvel.main

import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.base.BaseView
import com.accenture.marvel.model.Character

interface MainContract {

    interface Presenter: BasePresenter {
        fun getCharacters()
    }

    interface View: BaseView<Presenter> {
        fun showError()
        fun showCharacters(characters: List<Character>)
    }
}