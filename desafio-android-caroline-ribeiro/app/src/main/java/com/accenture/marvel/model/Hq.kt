package com.accenture.marvel.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Hq(
    val title: String,
    val description: String,
    val coverUrl: String,
    val price: List<Price>
) : Parcelable