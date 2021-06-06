package com.accenture.marvel.data.repository

import com.accenture.marvel.error.ErrorHandler
import com.accenture.marvel.error.exception.ApiResponseError
import com.accenture.marvel.data.network.ApiFactory
import com.accenture.marvel.model.ComicResult
import com.accenture.marvel.model.CharacterData
import io.reactivex.Observable

class RemoteRepository {

    private val errorHandler = ErrorHandler()

    fun fetchCharacters(offset: Int): Observable<CharacterData> {
        return ApiFactory.marvelApi
            .getCharacters(offset)
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.let {
                        it.data
                    } ?: run {
                        error(ApiResponseError("Response is empty"))
                    }
                } else {
                    val error = errorHandler.getError(response.code(), response.errorBody())
                    error(error)
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
                        error(ApiResponseError("Response is empty"))
                    }
                } else {
                    val error = errorHandler.getError(response.code(), response.errorBody())
                    error(error)
                }
            }
    }
}