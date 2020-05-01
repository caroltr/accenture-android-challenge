package com.accenture.marvel.error

import com.accenture.marvel.error.exception.ApiResponseError
import com.accenture.marvel.model.ErrorResponse
import com.google.gson.Gson
import okhttp3.ResponseBody
import java.lang.Exception

class ErrorHandler {

    private val gson by lazy { Gson() }

    fun getError(statusCode: Int, response: ResponseBody?): Throwable {
        return when (statusCode) {
            409 -> ApiResponseError(getErrorMessage(response))

            else -> Exception()
        }
    }

    private fun getErrorMessage(response: ResponseBody?): String {
        var message = ""
        response?.let {
            val json = it.string()
            val errorResponse = gson.fromJson(json, ErrorResponse::class.java)
            message = errorResponse.message
        }

        return message
    }
}

