package com.accenture.marvel.presentation.hq

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.MarvelApp
import com.accenture.marvel.databinding.ActivityHqBinding
import com.accenture.marvel.presentation.hq.model.HqPresentation
import com.accenture.marvel.util.load
import javax.inject.Inject

class HqActivity : AppCompatActivity() {

    @Inject lateinit var model: HqViewModel

    private val binding by lazy {
        ActivityHqBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MarvelApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayBackButton()

        model.getHq(intent.extras)

        model.hq.observe(this, { hq ->
            showData(hq)
        })
    }

    private fun displayBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun showData(hq: HqPresentation) {
        binding.tvName.text = hq.title
        binding.tvDescription.text = hq.description
        binding.tvPrice.text = hq.price
        binding.ivCover.load(hq.coverUrl)
    }
}
