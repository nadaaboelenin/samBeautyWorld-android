package com.app.sambeautyworld.pojo.salonScreen

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SubService {
    var subservice_id: String? = null
    var subservice_name: String? = null
    var service_time: String? = null
    var service_price: String? = null
    @SerializedName("count")
    @Expose
    var count = false
}