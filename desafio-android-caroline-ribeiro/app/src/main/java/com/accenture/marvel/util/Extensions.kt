package com.accenture.marvel.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.Transformation
import java.math.BigInteger
import java.security.MessageDigest

fun String.md5(): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun ImageView.load(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}

fun ImageView.loadCircle(url: String) {
    Glide.with(context)
        .load(url)
        .circleCrop()
        .into(this)
}