package com.accenture.marvel.presentation.hq

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.databinding.ActivityHqBinding

class HqActivity : AppCompatActivity() {

    private val model: HqViewModel by viewModels()
    private val binding by lazy {
        ActivityHqBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayBackButton()

        model.getHq(intent.extras)

        model.hq.observe(this, { hq ->
            showData(hq.title, hq.description, hq.price.toString(), hq.coverUrl)
        })
    }

    private fun displayBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun showData(title: String, description: String, price: String, coverUrl: String) {
        binding.tvName.text = title
        binding.tvDescription.text = description
        binding.tvPrice.text = price
        binding.ivCover.load(coverUrl)
    }
}
