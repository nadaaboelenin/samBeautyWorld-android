package com.app.sambeautyworld.ui.appointments

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class AppointmentModel : MyViewModel() {

    var response = MutableLiveData<ListYourBusinessPojo>()

    fun cancelService(booking_id: String,
                      user_id: String) {
        isLoading.value = true
        AppointmentRepository.cancelService({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, booking_id, user_id)
    }
}