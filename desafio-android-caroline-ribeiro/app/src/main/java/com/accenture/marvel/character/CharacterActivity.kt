package com.accenture.marvel.character

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.databinding.ActivityCharacterBinding
import com.accenture.marvel.hq.HqActivity
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.loadCircle

class CharacterActivity : AppCompatActivity(), CharacterContract.View {

    private lateinit var binding: ActivityCharacterBinding
    private lateinit var presenter: CharacterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setPresenter(CharacterPresenter(this))

        displayBackButton()

        presenter.start(intent.extras)
        binding.btnHq.setOnClickListener { presenter.getHqId() }
    }

    private fun displayBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showData(name: String, description: String, avatarUrl: String) {
        binding.tvName.text = name
        binding.tvDescription.text = description
        binding.ivAvatar.loadCircle(avatarUrl)
    }

    override fun displayMostExpensiveHq(hq: Hq) {
        val extra = Bundle()
        extra.putParcelable(Extra.HQ.value, hq)

        val intent = Intent(this@CharacterActivity, HqActivity::class.java)
        intent.putExtras(extra)
        startActivity(intent)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: CharacterContract.Presenter) {
        this.presenter = presenter
    }

}