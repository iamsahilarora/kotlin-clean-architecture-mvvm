package com.sa.kotlin_cleanarch.sample.view_model

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sa.kotlin_cleanarch.sample.MyApplication
import com.sa.kotlin_cleanarch.sample.model.bean.GlobalSetting
import com.sa.kotlin_cleanarch.sample.model.bean.requests.GeneralRequest
import com.sa.kotlin_cleanarch.sample.model.bean.responses.GlobalSettingResponse
import com.sa.kotlin_cleanarch.sample.model.networkCall.ApiResponse
import com.sa.kotlin_cleanarch.sample.model.preference.PreferenceConstants
import com.sa.kotlin_cleanarch.sample.model.preference.PreferenceHelper
import com.sa.kotlin_cleanarch.sample.model.repo.GlobalRepository
import org.koin.core.KoinComponent
import org.koin.core.inject


/* Created by Sahil Bharti on 5/4/19.
 *
*/
open class BaseViewModel constructor(app: MyApplication, private val globalRepository: GlobalRepository) :
    AndroidViewModel(app), KoinComponent {

    private var globalSettingsLiveData = MutableLiveData<ApiResponse<GlobalSettingResponse>>()
    private val mPref: PreferenceHelper by inject()


    fun getGlobalSettingFromNetwork(generalRequest: GeneralRequest) {
        globalRepository.getGlobalSettings(generalRequest, globalSettingsLiveData)
    }

    fun getGlobalSettingResponse(): MutableLiveData<ApiResponse<GlobalSettingResponse>> {
        return globalSettingsLiveData
    }

    fun getGlobalSettingsFromPreference(): GlobalSetting {
        return globalRepository.getGlobalSettingsFromPreference()
    }


    fun getGlobalRequest(): GeneralRequest {
        val request = GeneralRequest()
        request.token = mPref[PreferenceConstants.TOKEN]
        return request
    }

}