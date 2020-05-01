package com.accenture.marvel.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R
import com.accenture.marvel.util.load
import kotlinx.android.synthetic.main.activity_character.*

class CharacterActivity : AppCompatActivity(), CharacterContract.View {

    private lateinit var presenter: CharacterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        setPresenter(CharacterPresenter(this))

        presenter.start(intent.extras)
    }

    override fun showData(name: String, description: String, avatarUrl: String) {
        tv_name.text = name
        tv_description.text = description
        iv_avatar.load(avatarUrl)
    }

    override fun setPresenter(presenter: CharacterContract.Presenter) {
        this.presenter = presenter
    }

}