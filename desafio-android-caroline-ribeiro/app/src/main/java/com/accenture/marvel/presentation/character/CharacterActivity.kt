package com.accenture.marvel.presentation.character

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.MarvelApp
import com.accenture.marvel.databinding.ActivityCharacterBinding
import com.accenture.marvel.presentation.character.model.Hq
import com.accenture.marvel.presentation.hq.HqActivity
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.loadCircle
import javax.inject.Inject

class CharacterActivity : AppCompatActivity() {

    @Inject lateinit var model: CharacterViewModel

    private val binding by lazy {
        ActivityCharacterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MarvelApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewSetup()
        observers()
    }

    private fun viewSetup() {
        displayBackButton()

        model.start(intent.extras)

        binding.btnHq.setOnClickListener { model.getMostExpensiveHq() }
    }

    private fun observers() {
        model.hqMostExpensive.observe(this, { result ->
            result.data?.also {
                displayMostExpensiveHq(it)
            }

            result.message?.also {
                showError(it)
            }
        })

        model.character.observe(this, { char ->
            showData(char.name, char.description, char.url)
        })
    }

    private fun displayBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun showData(name: String, description: String, avatarUrl: String) {
        binding.tvName.text = name
        binding.tvDescription.text = description
        binding.ivAvatar.loadCircle(avatarUrl)
    }

    private fun displayMostExpensiveHq(hq: Hq) {
        val extra = Bundle()
        extra.putParcelable(Extra.HQ.value, hq)

        val intent = Intent(this@CharacterActivity, HqActivity::class.java)
        intent.putExtras(extra)
        startActivity(intent)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}