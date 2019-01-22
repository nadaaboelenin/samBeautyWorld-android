package com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.salonListBasedOnService.SalonListBasedOnServicePojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class SalonServicesModel : MyViewModel() {

    var response = MutableLiveData<SalonListBasedOnServicePojo>()
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
}