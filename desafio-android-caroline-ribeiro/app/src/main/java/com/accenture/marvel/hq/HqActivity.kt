package com.accenture.marvel.hq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.load
import kotlinx.android.synthetic.main.activity_hq.*

class HqActivity : AppCompatActivity(), HqContract.View {

    private lateinit var presenter: HqContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hq)
        setPresenter(HqPresenter(this))

        presenter.start(intent.extras)
    }

    override fun showData(hq: Hq) {
        tv_name.text = hq.title
        tv_description.text = hq.description
        tv_price.text = "${hq.price.first().price}"
        iv_cover.load(hq.coverUrl)
    }

    override fun setPresenter(presenter: HqContract.Presenter) {
        this.presenter = presenter
    }

}
