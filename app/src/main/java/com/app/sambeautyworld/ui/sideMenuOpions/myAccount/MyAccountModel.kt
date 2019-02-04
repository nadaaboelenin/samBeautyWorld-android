package com.app.sambeautyworld.ui.sideMenuOpions.myAccount

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.accountPojo.GetAccountPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class MyAccountModel : MyViewModel() {

    var response = MutableLiveData<GetAccountPojo>()

    fun authenticate(owner_id: String) {

        isLoading.value = true
        MyAccountRepository.getProfile({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, owner_id)
    }
}