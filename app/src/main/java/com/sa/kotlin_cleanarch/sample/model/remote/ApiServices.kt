package com.sa.kotlin_cleanarch.sample.model.remote

import com.sa.kotlin_cleanarch.sample.model.bean.responses.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

/* Created by Sahil Bharti on 21/1/19.
 *
*/
interface ApiServices {


    @FormUrlEncoded
    @GET(ApiConstant.GET_CONTACTS)
    fun getPostCommentsAsync(@QueryMap params: HashMap<String, String>): Deferred<Response<ContactListResponse>>


}