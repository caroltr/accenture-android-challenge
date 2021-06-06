package com.accenture.marvel.data.network

import com.accenture.marvel.model.CharacterResponse
import com.accenture.marvel.model.ComicResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    @GET("public/characters")
    fun getCharacters(
        @Query("offset") offset: Int
    ): Observable<Response<CharacterResponse>>

    @GET("public/characters/{characterId}/comics")
    fun getComicByCharacter(
        @Path("characterId") characterId: String
    ): Observable<Response<ComicResponse>>
}