package com.app.sambeautyworld.pojo.accountPojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Salon {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("admin_id")
    @Expose
    var admin_id: String? = null
    @SerializedName("country_name")
    @Expose
    var country_name: String? = null
    @SerializedName("total_salons")
    @Expose
    var total_salons: String? = null
    @SerializedName("active")
    @Expose
    var active: Int? = null

    var selected: Boolean? = false

}