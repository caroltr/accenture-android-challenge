package com.accenture.marvel

import com.accenture.marvel.data.model.ComicResult
import com.accenture.marvel.data.model.Price
import com.accenture.marvel.data.model.Thumbnail
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class CharacterControllerTest {

    @Test
    fun `When get comic with max price, Expect success`() {
        val tb = Thumbnail("jpg", "path")
        val comic1 = ComicResult("description1", "1", listOf(Price("type", 13.0), Price("type", 24.6)), tb, "title1")
        val comic2 = ComicResult("description2", "2", listOf(Price("type", 37.9)), tb, "title2")

        val characterController = CharacterController()
        val result = characterController.getComicMaxPrice(listOf(comic1, comic2))

        assertThat(result, equalTo(comic2))
    }

    @Test
    fun `When get comic with max price with multiple prices, Expect success`() {
        val tb = Thumbnail("jpg", "path")
        val comic1 = ComicResult("description1", "1", listOf(Price("type", 43.0), Price("type", 24.6)), tb, "title1")
        val comic2 = ComicResult("description2", "2", listOf(Price("type", 37.9)), tb, "title2")

        val characterController = CharacterController()
        val result = characterController.getComicMaxPrice(listOf(comic1, comic2))

        assertThat(result, equalTo(comic1))
    }

}