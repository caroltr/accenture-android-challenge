package com.accenture.marvel.data.network

import com.accenture.marvel.util.AppConstants
import com.accenture.marvel.util.md5
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

    private val ts = "${System.currentTimeMillis()}"
    private val privateKey = AppConstants.apiPrivateKey
    private val publicKey = AppConstants.apiPublicKey
    private val hash = "$ts$privateKey$publicKey".md5()

    // Creating Auth Interceptor to add api_key query in front of all the requests.
    private val authInterceptor = Interceptor { chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("apikey", publicKey)
            .addQueryParameter("ts", ts)
            .addQueryParameter("hash", hash)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    //OkhttpClient for building http request url
    private val client = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://gateway.marvel.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val marvelApi: MarvelApi = retrofit().create(
        MarvelApi::class.java
    )
}