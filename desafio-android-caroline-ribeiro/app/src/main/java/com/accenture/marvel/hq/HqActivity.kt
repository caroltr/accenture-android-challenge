package com.accenture.marvel.hq

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R

class HqActivity : AppCompatActivity(), HqContract.View {

    private lateinit var presenter: HqContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_hq)
        setPresenter(HqPresenter(this))

//        presenter.start(intent.extras)
    }

    override fun setPresenter(presenter: HqContract.Presenter) {
        this.presenter = presenter
    }

}
