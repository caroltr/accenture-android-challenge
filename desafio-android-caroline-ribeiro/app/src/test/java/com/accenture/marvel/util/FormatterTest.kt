package com.accenture.marvel.util

import com.accenture.marvel.data.model.Price
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class FormatterTest {

    @Test
    fun `When format price list, Expect ascending order and formatted in Reais`() {

        val prices = listOf(Price("test2", 12.6), Price("test", 2.6))
        val result = Formatter().pricesFormat(prices)

        val expected = "R$ 2,60 / R\$ 12,60"
        assertThat(result, equalTo(expected))
    }
}