package com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.salonListBasedOnService.SalonListBasedOnServicePojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class SalonServicesModel : MyViewModel() {

    var response = MutableLiveData<SalonListBasedOnServicePojo>()
    var responseHomeBased = MutableLiveData<SalonListBasedOnServicePojo>()


    fun authenticate(service_id: String) {
        isLoading.value = true
        SalonServicesRepository.getData({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, service_id)
    }


    fun homeBased(user_id: String,
                  service_id: String,
                  latitude: String,
                  longitude: String) {
        isLoading.value = true
        SalonServicesRepository.homeBased({
            responseHomeBased.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id, service_id, latitude, longitude)
    }
}