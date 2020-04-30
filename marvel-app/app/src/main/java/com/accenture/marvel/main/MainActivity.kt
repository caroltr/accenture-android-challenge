package com.accenture.marvel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.accenture.marvel.R
import com.accenture.marvel.base.BasePresenter
import com.accenture.marvel.network.ApiFactory
import com.accenture.marvel.util.md5
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPresenter(MainPresenter(this))

        ApiFactory.marvelApi.getCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            println("SUCESSO")
        }, {
            println("ERROR")
        })


    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
