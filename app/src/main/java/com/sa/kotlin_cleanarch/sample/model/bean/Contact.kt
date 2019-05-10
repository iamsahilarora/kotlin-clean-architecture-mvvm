package com.sa.kotlin_cleanarch.sample.model.bean

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.koin.core.qualifier.named
import java.io.Serializable


/* Created by Sahil Bharti on 22/1/19.
 *
*/
@Entity(tableName = "tb_contact")
data class Contact(@PrimaryKey var id: Int = 0) : Serializable {


    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    var firstName: String = ""

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    var last_name: String = ""

    @ColumnInfo(name = "email")
    @SerializedName("email")
    var email: String = ""

    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    var avatar: String = ""


}