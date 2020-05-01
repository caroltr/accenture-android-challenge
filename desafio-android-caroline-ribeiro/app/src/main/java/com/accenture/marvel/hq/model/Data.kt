package com.accenture.marvel.hq.model

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<ComicResult>,
    val total: String
)