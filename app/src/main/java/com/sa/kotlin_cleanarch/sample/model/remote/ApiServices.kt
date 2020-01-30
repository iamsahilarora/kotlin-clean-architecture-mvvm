package com.sa.kotlin_cleanarch.sample.model.remote


import com.sa.kotlin_cleanarch.sample.model.bean.responses.ContactListResponse
import retrofit2.Response
import retrofit2.http.*

/* Created by Sahil Bharti on 21/1/19.
 *
*/
interface ApiServices {


    @GET(ApiConstant.GET_CONTACTS)
    suspend fun getContactsAsync(@QueryMap params: HashMap<String, String>): Response<ContactListResponse>


}