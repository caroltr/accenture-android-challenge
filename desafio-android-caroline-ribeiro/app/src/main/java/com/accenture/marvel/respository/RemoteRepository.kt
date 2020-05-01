package com.accenture.marvel.respository

import com.accenture.marvel.network.ApiFactory
import com.accenture.marvel.model.ComicResult
import com.accenture.marvel.model.CharacterData
import io.reactivex.Observable

class RemoteRepository {

    fun fetchCharacters(offset: Int): Observable<CharacterData> {
        return ApiFactory.marvelApi
            .getCharacters(offset)
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.data
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