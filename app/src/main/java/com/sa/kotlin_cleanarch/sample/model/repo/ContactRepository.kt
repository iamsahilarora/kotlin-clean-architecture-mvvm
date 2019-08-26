package com.sa.kotlin_cleanarch.sample.model.repo

import androidx.lifecycle.MutableLiveData
import com.sa.kotlin_cleanarch.sample.model.bean.Contact
import com.sa.kotlin_cleanarch.sample.model.bean.requests.GetContactListRequest
import com.sa.kotlin_cleanarch.sample.model.bean.responses.ContactListResponse
import com.sa.kotlin_cleanarch.sample.model.local.preference.PreferenceConstants
import com.sa.kotlin_cleanarch.sample.model.local.preference.PreferenceHelper
import com.sa.kotlin_cleanarch.sample.model.local.room_db.dao.ContactDao
import com.sa.kotlin_cleanarch.sample.model.remote.ApiResponse
import com.sa.kotlin_cleanarch.sample.model.remote.ApiServices
import com.sa.kotlin_cleanarch.sample.model.remote.DataFetchCall
import com.sa.kotlin_cleanarch.sample.model.remote.ReflectionUtil
import kotlinx.coroutines.Deferred
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response


class ContactRepository(
    private val apiServices: ApiServices,
    private val preferences: PreferenceHelper,
    private val contactDao: ContactDao
) : KoinComponent {

    /** ReflectionUtil get Object using koin DI
     * used to convert  request Model class to HashMap */
    private val reflectionUtil: ReflectionUtil by inject()

    /** function to get CommentList data Request
     * params* 1. GetContactListRequest is requestDataModel
     * params* 2.LiveData of Response DataModel
     * in which response/error is posted after dataFetch either from network or DB.
     */
    fun getCommentList(
        getContactListRequest: GetContactListRequest,
        contactListResponse: MutableLiveData<ApiResponse<ContactListResponse>>
    ) {
        object : DataFetchCall<ContactListResponse>(contactListResponse) {


            /*** if return true loadFromDB called else createCallAsync is called */
            override fun shouldFetchFromDB(): Boolean {
                return preferences.getBoolean(PreferenceConstants.IS_CONTACT_SAVED_TO_DB)
            }

            /*** called when shouldFetchFromDB is true */
            override fun loadFromDB(): ContactListResponse? {
                return ContactListResponse().apply {
                    data = contactDao.retrieveAllContact() as? ArrayList<Contact>
                }

                //todo  fetch data from DB and post to live Data
            }

            /*** called when shouldFetchFromDB is false */
            override fun createCallAsync(): Deferred<Response<ContactListResponse>> {
                return apiServices.getContactsAsync(
                    reflectionUtil.convertPojoToMap(
                        getContactListRequest
                    )
                )
            }

            /***  called when  API Response is success and before post response to livedata */
            override fun saveResult(result: ContactListResponse) {
                result.data?.run {
                    forEach { contactDao.insertContact(it) }
                    preferences.put(PreferenceConstants.IS_CONTACT_SAVED_TO_DB, true)
                }
            }
        }.execute()
        /*** execute function is used to call the above dataFetch Request from network/DB */
    }


}