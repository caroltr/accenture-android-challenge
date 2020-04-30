package com.accenture.marvel.base

interface BaseView<T: BasePresenter> {
    fun setPresenter(presenter: T)
}