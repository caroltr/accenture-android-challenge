package com.accenture.marvel.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setPresenter(DetailPresenter(this))
    }

    override fun setPresenter(presenter: DetailContract.Presenter) {
        this.presenter = presenter
    }

}