package com.accenture.marvel.network.model.comic

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)