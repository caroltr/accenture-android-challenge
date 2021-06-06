package com.accenture.marvel.presentation.hq

import com.accenture.marvel.model.Price
import com.accenture.marvel.util.asReais

class HqController {

    fun formatPrices(prices: List<Price>): String {
        return prices.sortedBy { it.price }.joinToString(" / ") { i -> i.price.asReais() }
    }
}