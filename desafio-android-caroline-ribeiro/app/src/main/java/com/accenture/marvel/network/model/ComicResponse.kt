package com.accenture.marvel.network.model

data class ComicResponse(
    val code: String,
    val data: ComicData,
    val status: String
)

data class ComicData(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<ComicResult>,
    val total: String
)

data class ComicResult(
    val description: String,
    val id: String,
    val prices: List<Price>,
    val thumbnail: Thumbnail,
    val title: String
)

data class Price(
    val price: Double
)