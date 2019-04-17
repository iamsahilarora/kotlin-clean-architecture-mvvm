package com.sa.kotlin_cleanarch.sample.model.repo

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sa.kotlin_cleanarch.sample.model.bean.GlobalSetting
import com.sa.kotlin_cleanarch.sample.model.bean.requests.GeneralRequest
import com.sa.kotlin_cleanarch.sample.model.bean.responses.GlobalSettingResponse
import com.sa.kotlin_cleanarch.sample.model.networkCall.*
import com.sa.kotlin_cleanarch.sample.model.preference.PreferenceConstants
import com.sa.kotlin_cleanarch.sample.model.preference.PreferenceHelper
import kotlinx.coroutines.Deferred
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Response


class GlobalRepository constructor(
    private val apiServices: ApiServices,
    private val preferences: PreferenceHelper
) : KoinComponent {

    /** ReflectionUtil get Object using koin DI
     * used to convert  request Model class to HashMap */
    private val reflectionUtil: ReflectionUtil by inject()

    /** function to get GlobalSettings data Request
     * params* 1. GeneralRequest is requestDataModel
     * params* 2.LiveData of Response DataModel
     * in which response/error is posted after dataFetch either from network or DB.
     */
    fun getGlobalSettings(
        generalRequest: GeneralRequest,
        globalSettingResponse: MutableLiveData<ApiResponse<GlobalSettingResponse>>
    ) {
        object : DataFetchCall<GlobalSettingResponse>(globalSettingResponse) {


            /*** if return true loadFromDB called else createCallAsync is called */
            override fun shouldFetchFromDB(): Boolean {
                return false
            }

            /*** called when shouldFetchFromDB is true */
            override fun loadFromDB() {
                //todo  fetch data from DB and post to live Data
            }

            /*** called when shouldFetchFromDB is false */
            override fun createCallAsync(): Deferred<Response<GlobalSettingResponse>> {
                return apiServices.globalSettingAsync(reflectionUtil.convertPojoToMap(generalRequest))
            }

            /***  called when  API Response is success and before post response to livedata */
            override fun onSuccess(result: GlobalSettingResponse) {
                if (result.message != null) {
                    preferences.put(PreferenceConstants.GLOBAL_SETTING_MODEL, Gson().toJson(result.message))
                    preferences.put(PreferenceConstants.LAST_GLOBAL_UPDATE_TIMESTAMP, System.currentTimeMillis())
                }
            }
        }.execute()
        /*** execute function is used to call the above dataFetch Request from network/DB */
    }

    fun getGlobalSettingsFromPreference(): GlobalSetting {
        var settings = preferences[PreferenceConstants.GLOBAL_SETTING_MODEL]
        return Gson().fromJson<GlobalSetting>(settings, GlobalSetting::class.java)
    }

}