package com.accenture.marvel.data.repository

import com.accenture.marvel.data.model.CharacterData
import com.accenture.marvel.data.model.ComicResult
import com.accenture.marvel.data.network.ApiFactory
import com.accenture.marvel.domain.error.ErrorHandler
import com.accenture.marvel.domain.error.exception.ApiResponseError
import io.reactivex.Single
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val api: ApiFactory
) {
    private val errorHandler = ErrorHandler()

    fun fetchCharacters(offset: Int): Single<CharacterData> {
        return api.marvelApi
            .getCharacters(offset)
            .map { response ->
                if (response.isSuccessful) {
                    response.body()?.data ?: run {
                        error(ApiResponseError("Response is empty"))
                    }
                } else {
                    val error = errorHandler.getError(response.code(), response.errorBody())
                    error(error)
                }
            }
    }

    fun fetchComic(characterId: String): Single<List<ComicResult>> {
        return api.marvelApi
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