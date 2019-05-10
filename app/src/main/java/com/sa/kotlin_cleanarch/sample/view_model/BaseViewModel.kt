package com.sa.kotlin_cleanarch.sample.view_model

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sa.kotlin_cleanarch.sample.MyApplication
import com.sa.kotlin_cleanarch.sample.model.bean.requests.GetContactListRequest
import com.sa.kotlin_cleanarch.sample.model.bean.responses.ContactListResponse
import com.sa.kotlin_cleanarch.sample.model.remote.ApiResponse
import com.sa.kotlin_cleanarch.sample.model.local.preference.PreferenceHelper
import com.sa.kotlin_cleanarch.sample.model.repo.ContactRepository
import org.koin.core.KoinComponent
import org.koin.core.inject


/* Created by Sahil Bharti on 5/4/19.
 *
*/
open class BaseViewModel constructor(app: MyApplication, private val contactRepository: ContactRepository) :
    AndroidViewModel(app), KoinComponent {

    private var commentListLiveData = MutableLiveData<ApiResponse<ContactListResponse>>()
    private val mPref: PreferenceHelper by inject()


    fun getContactList(getContactListRequest: GetContactListRequest) {
        contactRepository.getCommentList(getContactListRequest, commentListLiveData)
    }

    fun getCommentListResponse(): MutableLiveData<ApiResponse<ContactListResponse>> {
        return commentListLiveData
    }


    fun getCommentList(): GetContactListRequest {
        val request = GetContactListRequest()
        request.page = "1"
        return request
    }

}