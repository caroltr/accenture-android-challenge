package com.accenture.marvel.character

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.marvel.R
import com.accenture.marvel.util.load
import kotlinx.android.synthetic.main.activity_detail.*

class CharacterActivity : AppCompatActivity(), CharacterContract.View {

    private lateinit var presenter: CharacterContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setPresenter(CharacterPresenter(this))

        presenter.start(intent.extras)
    }

    override fun showData(character: CharacterModel) {
        tv_name.text = character.name
        tv_description.text = character.description
        iv_avatar.load(character.avatarUrl)

        character.series
        character.stories
    }

    override fun setPresenter(presenter: CharacterContract.Presenter) {
        this.presenter = presenter
    }

}