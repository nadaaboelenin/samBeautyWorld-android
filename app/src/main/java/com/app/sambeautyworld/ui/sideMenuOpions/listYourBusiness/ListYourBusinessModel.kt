package com.app.sambeautyworld.ui.sideMenuOpions.listYourBusiness

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class ListYourBusinessModel : MyViewModel() {

    var response = MutableLiveData<ListYourBusinessPojo>()
    fun listBusiness(name: String,
                     business_name: String,
                     phone: String,
                     email: String) {

        isLoading.value = true
        ListYourBusinessRepository.getData({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, name, business_name, phone, email)
    }
}