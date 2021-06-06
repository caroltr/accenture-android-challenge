package com.accenture.marvel.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.paging.PagedList
import com.accenture.marvel.databinding.ActivityMainBinding
import com.accenture.marvel.model.Character

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.rvItems.adapter = adapter

//        val model: MainViewModel by viewModels()
//        model.getMovies().observe(this, { result ->
//
//            result.data?.also {
//                adapter.updateList(it)
//            }
//
//            result.message?.also {
//                Log.e("ERROR", it)
//            }
//        })
    }

    fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showCharacters(characters: PagedList<Character>) {
        adapter.submitList(characters)
    }
}
