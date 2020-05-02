package com.accenture.marvel.character

import com.accenture.marvel.model.ComicResult

class CharacterController {

    fun getComicMaxPrice(comics: List<ComicResult>): ComicResult {
        val sortedResults = comics.sortedByDescending { r ->
            r.prices.maxBy { it.price }?.price
        }

        return sortedResults.first()
    }
}