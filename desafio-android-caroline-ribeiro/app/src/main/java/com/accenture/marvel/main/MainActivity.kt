package com.accenture.marvel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.accenture.marvel.R
import com.accenture.marvel.model.Character
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPresenter(MainPresenter(this))

        adapter = MainAdapter(listOf())
        rv_items.adapter = adapter

        presenter.getCharacters()
    }

    override fun showError() {
        // TODO
    }

    override fun showCharacters(characters: List<Character>) {
        adapter.updateItems(characters)
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
