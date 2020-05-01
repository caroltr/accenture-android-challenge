package com.accenture.marvel.util

import com.accenture.marvel.BuildConfig

object AppConstants {
    const val apiPublicKey = BuildConfig.apiPublicKey
    const val apiPrivateKey = BuildConfig.apiPrivateKey
}

enum class Extra(val value: String) {
    CHARACTER("extra_character"),
    HQ("extra_hq")
}

enum class AspectRatio(val value: String) {
    MEDIUM("portrait_medium")
}