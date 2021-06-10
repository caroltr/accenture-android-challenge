package com.accenture.marvel.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class CharacterResponse(
    val code: String,
    val data: CharacterData,
    val status: String
)

data class CharacterData(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Character>,
    val total: Int
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
