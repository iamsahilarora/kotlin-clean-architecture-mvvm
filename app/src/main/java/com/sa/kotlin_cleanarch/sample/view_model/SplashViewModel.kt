package com.sa.kotlin_cleanarch.sample.view_model

import androidx.lifecycle.MutableLiveData
import com.sa.kotlin_cleanarch.sample.model.bean.requests.GetContactListRequest
import com.sa.kotlin_cleanarch.sample.model.bean.responses.ContactListResponse
import com.sa.kotlin_cleanarch.sample.model.remote.ApiResponse
import com.sa.kotlin_cleanarch.sample.model.repo.ContactRepository

class SplashViewModel constructor(private val contactRepository: ContactRepository) :
    BaseViewModel() {

     val commentListResponse by lazy {
         MutableLiveData<ApiResponse<ContactListResponse>>()
    }

    fun getContactList(getContactListRequest: GetContactListRequest) {
        contactRepository.getCommentList(getContactListRequest, commentListResponse)
    }



    fun createContactRequest(): GetContactListRequest {
        val request = GetContactListRequest()
        request.page = "1"
        return request
    }

}