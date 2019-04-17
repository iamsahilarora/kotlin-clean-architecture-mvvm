package com.sa.kotlin_cleanarch.sample.model.networkCall

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception


/* Created by Sahil Bharti on 21/1/19.
 *
*/
abstract class DataFetchCall<ResultType>(private val responseLiveData: MutableLiveData<ApiResponse<ResultType>>) {


    abstract fun createCallAsync(): Deferred<Response<ResultType>>
    abstract fun onSuccess(result: ResultType)
    abstract fun shouldFetchFromDB(): Boolean
    abstract fun loadFromDB()

     fun execute() {
        if (shouldFetchFromDB()) {
            loadFromDB()
        } else {
            GlobalScope.launch {
                try {
                    responseLiveData.postValue(ApiResponse.loading())
                    val request = createCallAsync()
                    val response = request.await()
                    if (response?.body() != null) {
                        onSuccess(response.body()!!)
                        responseLiveData.postValue(ApiResponse.success(response.body()!!))
                    } else {
                        responseLiveData.postValue(ApiResponse.error(Throwable(response.message())))
                    }
                } catch (exception: Exception) {
                    responseLiveData.postValue(ApiResponse.error(exception))
                }
            }
        }
    }

}