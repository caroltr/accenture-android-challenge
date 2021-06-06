package com.accenture.marvel.hq

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.databinding.ActivityHqBinding
import com.accenture.marvel.main.MainViewModel
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.load

class HqActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHqBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHqBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        displayBackButton()

        val model: HqViewModel by viewModels()
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

    fun showData(title: String, description: String, price: String, coverUrl: String) {
        binding.tvName.text = title
        binding.tvDescription.text = description
        binding.tvPrice.text = price
        binding.ivCover.load(coverUrl)
    }
}
