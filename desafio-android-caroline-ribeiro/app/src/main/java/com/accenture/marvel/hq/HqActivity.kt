package com.accenture.marvel.hq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R
import com.accenture.marvel.databinding.ActivityHqBinding
import com.accenture.marvel.util.load

class HqActivity : AppCompatActivity(), HqContract.View {

    private lateinit var binding: ActivityHqBinding
    private lateinit var presenter: HqContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHqBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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
        binding.tvName.text = title
        binding.tvDescription.text = description
        binding.tvPrice.text = price
        binding.ivCover.load(coverUrl)
    }

    override fun setPresenter(presenter: HqContract.Presenter) {
        this.presenter = presenter
    }

}
