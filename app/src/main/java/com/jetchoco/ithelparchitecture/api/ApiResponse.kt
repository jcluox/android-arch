package com.jetchoco.ithelparchitecture.api

import android.util.Log
import retrofit2.Response
import java.io.IOException

class ApiResponse<T> {

    companion object {
        private const val TAG = "ApiResponse"
    }

    val code: Int
    val body: T?
    val errorMessage: String?

    constructor(error: Throwable) {
        code = 500
        body = null
        errorMessage = error.message
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            // Get error message from errorBody() first. If there is no error message, get from response.message()
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().toString()
                } catch (e: IOException) {
                    Log.e(TAG, "error while parsing response")
                }
            }
            if (message == null || message.trim().isEmpty()) {
                message = response.message()
            }
            errorMessage = message
            body = null
        }
    }

    fun isSuccessful() = (code in 200..299)
}