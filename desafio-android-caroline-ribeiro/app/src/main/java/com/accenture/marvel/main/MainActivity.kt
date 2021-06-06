package com.accenture.marvel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.paging.PagedList
import androidx.activity.viewModels
import com.accenture.marvel.databinding.ActivityMainBinding
import com.accenture.marvel.model.Character

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private var adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvItems.adapter = adapter

        val model: MainViewModel by viewModels()
        model.getCharacters().observe(this, { result ->
            showCharacters(result)
        })
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showCharacters(characters: PagedList<Character>) {
        adapter.submitList(characters)
    }
}
