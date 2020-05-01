package com.accenture.marvel.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class CharacterResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: String,
    val copyright: String,
    val data: Data,
    val etag: String,
    val status: String
)

data class Data(
    val count: String,
    val limit: String,
    val offset: String,
    val results: List<Result>,
    val total: String
)

@Parcelize
data class Result(
    val comics: Comics,
    val description: String,
    val events: Events,
    val id: String,
    val modified: String,
    val name: String,
    val resourceURI: String,
    val series: Series,
    val stories: Stories,
    val thumbnail: Thumbnail,
    val urls: List<Url>
) : Parcelable

@Parcelize
data class Comics(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
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

@Parcelize
data class Events(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
) : Parcelable

@Parcelize
data class Url(
    val type: String,
    val url: String
) : Parcelable

@Parcelize
data class Stories(
    val available: String,
    val collectionURI: String,
    val items: List<StoryItem>,
    val returned: String
) : Parcelable

@Parcelize
data class StoryItem(
    val name: String,
    val resourceURI: String,
    val type: String
) : Parcelable

@Parcelize
data class Series(
    val available: String,
    val collectionURI: String,
    val items: List<Item>,
    val returned: String
) : Parcelable