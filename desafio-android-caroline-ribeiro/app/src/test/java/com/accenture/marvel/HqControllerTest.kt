package com.accenture.marvel

import com.accenture.marvel.hq.HqController
import com.accenture.marvel.model.Price
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HqControllerTest {

    @Test
    fun `When format price list, Expect ascending order and formatted in Reais`() {
        val hqController = HqController()

        val prices = listOf(Price("test2", 12.6), Price("test", 2.6))
        val result = hqController.formatPrices(prices)

        val expected = "R$ 2,60 / R\$ 12,60"
        assertThat(result, equalTo(expected))
    }
}