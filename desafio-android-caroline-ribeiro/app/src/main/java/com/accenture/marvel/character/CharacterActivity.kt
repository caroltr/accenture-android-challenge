package com.accenture.marvel.character

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.databinding.ActivityCharacterBinding
import com.accenture.marvel.hq.HqActivity
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.loadCircle

class CharacterActivity : AppCompatActivity() {

    private val model: CharacterViewModel by viewModels()
    private val binding by lazy {
        ActivityCharacterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        displayBackButton()

        model.start(intent.extras)

        binding.btnHq.setOnClickListener { model.getMostExpensiveHq() }

        model.hqMostExpensive.observe(this, { hq ->
            displayMostExpensiveHq(hq)
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

    fun displayMostExpensiveHq(hq: Hq) {
        val extra = Bundle()
        extra.putParcelable(Extra.HQ.value, hq)

        val intent = Intent(this@CharacterActivity, HqActivity::class.java)
        intent.putExtras(extra)
        startActivity(intent)
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}