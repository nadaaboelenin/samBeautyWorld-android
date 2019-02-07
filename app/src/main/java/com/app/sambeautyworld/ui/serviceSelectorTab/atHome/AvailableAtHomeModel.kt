package com.app.sambeautyworld.ui.serviceSelectorTab.atHome

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.salonLocations.SalonLocationsPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class AvailableAtHomeModel : MyViewModel() {

    var response = MutableLiveData<SalonLocationsPojo>()

    fun getSalonLocations(email: String,
                          service_id: String) {

        isLoading.value = true
        AvailableAtHomeRepository.getSalons({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, email,
                service_id)
    }
}