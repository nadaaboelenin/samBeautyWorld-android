package com.app.sambeautyworld.ui.homeFragment

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.listServices.ListAllServicesPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class HomeFragmentModel : MyViewModel() {

    var response = MutableLiveData<ListAllServicesPojo>()

    fun getServices() {

        isLoading.value = true
        HomeFragmentRepository.getData({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }
}