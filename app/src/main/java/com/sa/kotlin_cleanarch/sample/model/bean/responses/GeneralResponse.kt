package com.sa.kotlin_cleanarch.sample.model.bean.responses

import com.google.gson.annotations.SerializedName
import java.io.Serializable


/* Created by Sahil Bharti on 18/1/19.
 *
*/
open class GeneralResponse : Serializable {

    @SerializedName("status")
    var status: Int = 0

    @SerializedName("message")
    var message: String = ""

    @SerializedName("errors")
    var errors: Array<String>? = null

}