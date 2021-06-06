package com.accenture.marvel.presentation.character.model

import android.os.Parcelable
import com.accenture.marvel.data.model.Price
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hq(
    val title: String,
    val description: String,
    val coverUrl: String,
    val price: List<Price>
) : Parcelable