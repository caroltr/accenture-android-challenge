package com.accenture.marvel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CharacterResponse(
    val code: String,
    val data: Data,
    val status: String
)

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<Character>,
    val total: String
)

@Parcelize
data class Character(
    val comics: Comics,
    val description: String,
    val id: String,
    val name: String,
    val thumbnail: Thumbnail
) : Parcelable

@Parcelize
data class Comics(
    val items: List<Item>
) : Parcelable

@Parcelize
data class Item(
    val name: String,
    val resourceURI: String
) : Parcelable

@Parcelize
data class Thumbnail(
    val extension: String,
    val path: String
) : Parcelable
