package com.app.sambeautyworld.pojo.accountPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserInformation {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("full_name")
    @Expose
    var full_name: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("dob")
    @Expose
    var dob: String? = null

}