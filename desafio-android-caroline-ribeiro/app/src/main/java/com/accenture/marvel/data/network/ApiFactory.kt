package com.accenture.marvel.data.network

import android.content.Context
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://gateway.marvel.com/v1/"
private const val CACHE_SIZE = 5 * 1024 * 1024L // 5 MB de cache

class ApiFactory(
    private val application: Context
) {

    private val client = OkHttpClient().newBuilder()
        .cache(cacheSize())
        .addNetworkInterceptor(CacheInterceptor)
        .addInterceptor(AuthInterceptor)
        .build()

    private fun cacheSize(): Cache {
        return Cache(application.cacheDir, CACHE_SIZE)
    }

    private fun retrofit(): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val marvelApi: MarvelApi = retrofit().create(
        MarvelApi::class.java
    )
}