package com.sa.kotlin_cleanarch.sample.model.networkCall

import com.sa.kotlin_cleanarch.sample.model.bean.responses.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/* Created by Sahil Bharti on 21/1/19.
 *
*/
interface ApiServices {


    @FormUrlEncoded
    @POST(ApiConstant.GLOBAL_SETTINGS_API)
    fun globalSettingAsync(@FieldMap params: HashMap<String, String>): Deferred<Response<GlobalSettingResponse>>


}