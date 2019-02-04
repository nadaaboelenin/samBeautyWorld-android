package com.app.sambeautyworld.pojo.accountPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetAccountPojo {

    @SerializedName("status")
    @Expose
    var status: Int? = null
    @SerializedName("salons")
    @Expose
    var salons: List<Salon>? = null
    @SerializedName("userInformation")
    @Expose
    var userInformation: UserInformation? = null

}