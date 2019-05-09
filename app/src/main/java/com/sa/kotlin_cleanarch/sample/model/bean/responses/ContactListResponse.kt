package com.sa.kotlin_cleanarch.sample.model.bean.responses

import com.google.gson.annotations.SerializedName
import com.sa.kotlin_cleanarch.sample.model.bean.Contact
import org.w3c.dom.Comment
import java.io.Serializable


/* Created by Sahil Bharti on 22/1/19.
 *
*/
class ContactListResponse : Serializable {

    val page: Int = 0
    val per_page: Int = 0
    val total: Int = 0
    val total_pages: Int = 0
    var data: ArrayList<Contact>? = null

}