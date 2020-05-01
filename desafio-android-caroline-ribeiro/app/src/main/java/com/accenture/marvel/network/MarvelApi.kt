package com.accenture.marvel.network

import com.accenture.marvel.network.model.CharacterResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface MarvelApi {

    @GET("public/characters")
    fun getCharacters(): Observable<Response<CharacterResponse>>

}