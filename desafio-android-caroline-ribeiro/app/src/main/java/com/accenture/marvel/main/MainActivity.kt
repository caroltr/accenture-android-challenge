package com.accenture.marvel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.accenture.marvel.R
import com.accenture.marvel.network.ApiFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setPresenter(MainPresenter(this))

        adapter = MainAdapter(listOf())

        rv_items.layoutManager = GridLayoutManager(this, 3)
        rv_items.adapter = adapter

            ApiFactory.marvelApi.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println("SUCESSO")

                    it.body()?.let { response ->
                        val results = response.data.results
                        adapter.updateItems(results)
                    }

                }, {
                    println("ERROR")
                }, {
                    println("COMPLETED")
                })
    }

    override fun setPresenter(presenter: MainContract.Presenter) {
        this.presenter = presenter
    }
}
