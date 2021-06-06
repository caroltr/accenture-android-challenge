package com.accenture.marvel

import com.accenture.marvel.domain.error.ErrorHandler
import com.accenture.marvel.domain.error.exception.ApiResponseError
import com.accenture.marvel.domain.error.exception.CommunicationException
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class ErrorHandlerTest {

    @Test
    fun `When get error that is error expected by the api with null response, Expect ApiResponseError with empty message`() {
        val errorHandler = ErrorHandler()
        val response: Throwable = errorHandler.getError(409, null)

        assertThat(response is ApiResponseError, equalTo(true))
        assertThat(response.message, equalTo(""))
    }

    @Test
    fun `When get error that is not expected by the api, Expect CommunicationException with null message`() {
        val errorHandler = ErrorHandler()
        val response: Throwable = errorHandler.getError(404, null)

        assertThat(response is CommunicationException, equalTo(true))
        assertThat(response.message, nullValue())
    }

    @Test
    fun `When get message from ApiResponseError, Expect success`() {
        val errorHandler = ErrorHandler()
        val error = ApiResponseError("My message")
        val response = errorHandler.getMessage(error)

        assertThat(response, equalTo("My message"))
    }

    @Test
    fun `When get message from generic error, Expect the default message`() {
        val errorHandler = ErrorHandler()
        val error = NullPointerException()
        val response = errorHandler.getMessage(error)

        assertThat(response, equalTo("An unexpected error has occurred"))
    }

}