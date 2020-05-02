package com.accenture.marvel.hq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R
import com.accenture.marvel.util.load
import kotlinx.android.synthetic.main.activity_hq.*

class HqActivity : AppCompatActivity(), HqContract.View {

    private lateinit var presenter: HqContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hq)
        setPresenter(HqPresenter(this))

        displayBackButton()

        presenter.start(intent.extras)
    }

    private fun displayBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showData(title: String, description: String, price: String, coverUrl: String) {
        tv_name.text = title
        tv_description.text = description
        tv_price.text = price
        iv_cover.load(coverUrl)
    }

    override fun setPresenter(presenter: HqContract.Presenter) {
        this.presenter = presenter
    }

}
