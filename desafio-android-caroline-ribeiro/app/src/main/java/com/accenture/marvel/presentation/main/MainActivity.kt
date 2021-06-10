package com.accenture.marvel.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedList
import com.accenture.marvel.MarvelApp
import com.accenture.marvel.data.model.Character
import com.accenture.marvel.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var adapter: MainAdapter
    @Inject lateinit var viewModel: MainViewModel

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as MarvelApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewSetup()
        observers()
    }

    private fun viewSetup() {
        binding.rvItems.adapter = adapter
    }

    private fun observers() {
        viewModel.getCharacters().observe(this, { result ->
            result.data?.also {
                showCharacters(it)
            }

            result.message?.also {
                showError(it)
            }
        })
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun showCharacters(characters: PagedList<Character>) {
        adapter.submitList(characters)
    }
}
