package com.accenture.marvel.domain.error

import com.accenture.marvel.domain.error.exception.ApiResponseError
import com.accenture.marvel.domain.error.exception.CommunicationException
import com.accenture.marvel.data.model.ErrorResponse
import com.google.gson.Gson
import okhttp3.ResponseBody

class ErrorHandler {

    private val gson by lazy { Gson() }

    fun getMessage(error: Throwable): String {
        val defaultMessage = "An unexpected error has occurred"

        val message = when (error) {
            is ApiResponseError -> error.message
            else -> null
        }

        return message?:defaultMessage
    }

    fun getError(statusCode: Int, response: ResponseBody?): Throwable {
        return when (statusCode) {
            409 -> ApiResponseError(getResponseMessage(response))

            else -> CommunicationException()
        }
    }

    private fun getResponseMessage(response: ResponseBody?): String {
        var message = ""
        response?.let {
            val json = it.string()
            val errorResponse = gson.fromJson(json, ErrorResponse::class.java)
            message = errorResponse.message
        }

        return message
    }
}

