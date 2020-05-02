package com.accenture.marvel.character

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R
import com.accenture.marvel.hq.HqActivity
import com.accenture.marvel.model.Hq
import com.accenture.marvel.util.Extra
import com.accenture.marvel.util.load
import com.accenture.marvel.util.loadCircle
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity(), CharacterContract.View {

    private lateinit var presenter: CharacterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        setPresenter(CharacterPresenter(this))

        displayBackButton()

        presenter.start(intent.extras)
        btn_hq.setOnClickListener { presenter.getHqId() }
    }

    private fun displayBackButton() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun showData(name: String, description: String, avatarUrl: String) {
        tv_name.text = name
        tv_description.text = description
        iv_avatar.loadCircle(avatarUrl)
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