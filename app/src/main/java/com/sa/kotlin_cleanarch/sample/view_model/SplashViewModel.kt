package com.sa.kotlin_cleanarch.sample.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sa.kotlin_cleanarch.sample.model.bean.requests.GetContactListRequest
import com.sa.kotlin_cleanarch.sample.model.bean.responses.ContactListResponse
import com.sa.kotlin_cleanarch.sample.model.remote.ApiResponse
import com.sa.kotlin_cleanarch.sample.model.repo.ContactRepository


class SplashViewModel constructor(private val contactRepository: ContactRepository) :
    BaseViewModel() {

    private val _commentListResponse by lazy {
        MutableLiveData<ApiResponse<ContactListResponse>>()
    }

    /*** LiveData that view observing
     * you can modify this as MediatorLiveData if you want to modify data model coming from api*/
    val commentListResponse: LiveData<ApiResponse<ContactListResponse>> = _commentListResponse

    fun getContactList(getContactListRequest: GetContactListRequest) {
        contactRepository.getCommentListFromNetwork(getContactListRequest, _commentListResponse)
    }

    fun createContactRequest(): GetContactListRequest {
        val request = GetContactListRequest()
        request.page = "1"
        return request
    }

}