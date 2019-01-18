package com.app.sambeautyworld.ui.homeFragment

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.mainHome.MainHomePojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class HomeFragmentModel : MyViewModel() {

    var response = MutableLiveData<MainHomePojo>()

    fun getServices(user_id: String) {

        isLoading.value = true
        HomeFragmentRepository.getData({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id)
    }
}