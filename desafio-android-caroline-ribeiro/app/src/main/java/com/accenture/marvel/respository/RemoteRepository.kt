package com.accenture.marvel.respository

import com.accenture.marvel.network.ApiFactory
import com.accenture.marvel.model.Character
import com.accenture.marvel.model.ComicResult
import io.reactivex.Observable

class RemoteRepository {

    fun fetchCharacters(): Observable<List<Character>> {
        return ApiFactory.marvelApi
            .getCharacters()
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.data.results
                    } ?: run {
                        error(Exception()) // TODO
                    }
                } else {
                    error(Exception()) // TODO
                }
            }
    }

    fun fetchComic(characterId: String): Observable<List<ComicResult>> {
        return ApiFactory.marvelApi
            .getComicByCharacter(characterId)
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.data.results
                    } ?: run {
                        error(Exception()) // TODO
                    }
                } else {
                    error(Exception()) // TODO
                }
            }
    }
}