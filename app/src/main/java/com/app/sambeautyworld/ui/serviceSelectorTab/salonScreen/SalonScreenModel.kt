package com.app.sambeautyworld.ui.serviceSelectorTab.salonScreen

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import com.app.sambeautyworld.pojo.salonScreen.SalonScreenPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class SalonScreenModel : MyViewModel() {

    var response = MutableLiveData<ListYourBusinessPojo>()
    var responseAllServices = MutableLiveData<SalonScreenPojo>()

    fun addBookmark(user_id: String,
                    salon_id: String) {

        isLoading.value = true
        SalonScreenRepository.getData({
            response.value = it
            isLoading.value = false
        },
                {
            apiError.value = it
            isLoading.value = false
        }, user_id, salon_id)
    }


    fun getAllServices(owner_id: String, string: String, salon_id: String?) {

        isLoading.value = true
        SalonScreenRepository.getAllServices({
            responseAllServices.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, owner_id, string, salon_id)
    }
}