package com.sa.kotlin_cleanarch.sample.model.remote

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception


/** Created by Sahil Bharti on 21/1/19.
 * Copyright (c) 2019 Sahil Inc. All rights reserved.
 */
abstract class DataFetchCall<ResultType>(private val responseLiveData: MutableLiveData<ApiResponse<ResultType>>) {

    abstract suspend fun createCallAsync(): Response<ResultType>
    open fun saveResult(result: ResultType) {}
    open fun shouldFetchFromDB(): Boolean = false
    open fun loadFromDB(): ResultType? = null

    fun execute() {
        responseLiveData.postValue(ApiResponse.loading())
        if (shouldFetchFromDB()) {
            callLoadFromDB()
        } else {
            callNetworkData()
        }
    }

    private fun callNetworkData() {
        GlobalScope.launch {
            try {
                val request = createCallAsync()
                if (request.isSuccessful) {
                    if (request.body() != null)
                        saveResult(request.body()!!)
                    responseLiveData.postValue(ApiResponse.success(request.body()!!))
                } else {
                    responseLiveData.postValue(
                        ApiResponse.error(
                            ApiResponse.ApiError(
                                request.code(),
                                request.message(),
                                request.errorBody().toString()
                            )
                        )
                    )
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                responseLiveData.postValue(
                    ApiResponse.error(
                        ApiResponse.ApiError(
                            500,
                            exception.message.toString()
                        )
                    )
                )
            }
        }
    }

    private fun callLoadFromDB() {
        GlobalScope.launch {
            try {
                val response = loadFromDB()
                if (response != null) {
                    saveResult(response)
                    responseLiveData.postValue(ApiResponse.success(response))
                } else
                    responseLiveData.postValue(
                        ApiResponse.error(
                            ApiResponse.ApiError(
                                404,
                                "Not Found"
                            )
                        )
                    )
            } catch (exception: Exception) {
                responseLiveData.postValue(
                    ApiResponse.error(
                        ApiResponse.ApiError(404, exception.message.toString())
                    )
                )
            }
        }
    }

}