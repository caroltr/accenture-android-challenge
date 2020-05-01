package com.accenture.marvel.network

import com.accenture.marvel.network.model.CharacterResponse
import com.accenture.marvel.network.model.comic.ComicResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("public/characters")
    fun getCharacters(): Observable<Response<CharacterResponse>>

    @GET("public/comics/{id}")
    fun getComic(
        @Path("id") id: String
    ): Observable<Response<ComicResponse>>

}