package com.accenture.marvel.data.network

import com.accenture.marvel.util.AppConstants
import com.accenture.marvel.util.md5
import okhttp3.Interceptor
import okhttp3.Response


private const val PRIVATE_KEY = AppConstants.apiPrivateKey
private const val PUBLIC_KEY = AppConstants.apiPublicKey

object AuthInterceptor : Interceptor {

    private val ts = "${System.currentTimeMillis()}"
    private val hash = "${ts}$PRIVATE_KEY$PUBLIC_KEY".md5()

    override fun intercept(chain: Interceptor.Chain): Response {

        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", PUBLIC_KEY)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}