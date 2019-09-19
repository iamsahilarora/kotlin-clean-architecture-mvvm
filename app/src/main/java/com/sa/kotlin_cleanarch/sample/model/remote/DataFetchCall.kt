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


    abstract fun createCallAsync(): Deferred<Response<ResultType>>
    abstract fun saveResult(result: ResultType)
    abstract fun shouldFetchFromDB(): Boolean
    abstract fun loadFromDB(): ResultType?

    fun execute() {
        responseLiveData.postValue(ApiResponse.loading())
        if (shouldFetchFromDB()) {
            GlobalScope.launch {
                try {
                    val request = async { return@async loadFromDB() }
                    val response = request.await()
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
                            ApiResponse.ApiError(
                                404,
                                exception.message.toString()
                            )
                        )
                    )
                }
            }
        } else {
            GlobalScope.launch {
                try {
                    val request = createCallAsync()
                    val response = request.await()
                    if (response.body() != null) {
                        saveResult(response.body()!!)
                        responseLiveData.postValue(ApiResponse.success(response.body()!!))
                    } else {
                        responseLiveData.postValue(
                            ApiResponse.error(
                                ApiResponse.ApiError(
                                    response.code(),
                                    response.message(),
                                    response.errorBody().toString()
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
    }

}