package com.sa.kotlin_cleanarch.sample.model.remote

/* Created by Sahil Bharti on 9/1/19.
**/

class ApiResponse<T>(val status: Status, val data: T?, val error: ApiError?) {


    companion object {
        fun <T> loading(): ApiResponse<T> {
            return ApiResponse(
                Status.LOADING,
                null,
                null
            )
        }

        fun <T> success(data: T): ApiResponse<T> {
            return ApiResponse(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: ApiError?): ApiResponse<T> {
            return ApiResponse(
                Status.ERROR,
                null,
                error
            )
        }
    }

    enum class Status {
        LOADING,
        SUCCESS,
        ERROR,
    }



    class ApiError(val code: Int, val message: String, errorBody: String="") {

    }
}