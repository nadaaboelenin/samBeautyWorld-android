package com.app.sambeautyworld.ui.businessType

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.businessType.GetBusinessTypePojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class GetBusinessModel : MyViewModel() {

    var response = MutableLiveData<GetBusinessTypePojo>()

    fun authenticate() {

        isLoading.value = true
        GetBusinessRepository.getData({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }
}