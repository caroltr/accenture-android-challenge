package com.accenture.marvel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.accenture.marvel.R
import com.accenture.marvel.base.BasePresenter

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPresenter(MainPresenter(this))
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
