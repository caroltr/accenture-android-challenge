package com.accenture.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

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

@Parcelize
data class Price(
    val type: String,
    val price: Double
) : Parcelable