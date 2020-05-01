package com.accenture.marvel.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.item_character.*
import kotlinx.android.synthetic.main.item_character.tv_name

class DetailActivity : AppCompatActivity(), DetailContract.View {

    private lateinit var presenter: DetailContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setPresenter(DetailPresenter(this))

        presenter.start(intent.extras)


    }

    override fun showData(detail: DetailModel) {
        tv_name.text = detail.name
        tv_description.text = detail.description
//        iv_avatar.load(url)
    }

    override fun setPresenter(presenter: DetailContract.Presenter) {
        this.presenter = presenter
    }

}