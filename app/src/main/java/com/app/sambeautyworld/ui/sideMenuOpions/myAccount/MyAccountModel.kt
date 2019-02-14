package com.app.sambeautyworld.ui.sideMenuOpions.myAccount

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.accountPojo.GetAccountPojo
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class MyAccountModel : MyViewModel() {

    var response = MutableLiveData<GetAccountPojo>()
    var response_edit = MutableLiveData<ListYourBusinessPojo>()


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

    fun update(user_id: String,
               full_name: String,
               address: String,
               email: String,
               dob: String) {
        isLoading.value = true
        MyAccountRepository.editProfile({
            response_edit.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id, full_name, address, email, dob)
    }
}