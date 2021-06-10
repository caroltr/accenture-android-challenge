package com.accenture.marvel.util

import com.accenture.marvel.data.model.Price

class Formatter {

    fun pricesFormat(prices: List<Price>): String {
        return prices.sortedBy { it.price }.joinToString(" / ") { i -> i.price.asReais() }
    }
}