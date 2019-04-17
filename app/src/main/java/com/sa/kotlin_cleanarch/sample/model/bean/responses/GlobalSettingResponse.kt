package com.sa.kotlin_cleanarch.sample.model.bean.responses

import com.google.gson.annotations.SerializedName
import com.sa.kotlin_cleanarch.sample.model.bean.GlobalSetting
import java.io.Serializable


/* Created by Sahil Bharti on 22/1/19.
 *
*/
class GlobalSettingResponse : Serializable {

    @SerializedName("code")
    var code: Int = 0

    @SerializedName("message")
    var message: GlobalSetting? = null

}