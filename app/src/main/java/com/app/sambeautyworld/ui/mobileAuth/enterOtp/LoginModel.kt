package com.app.sambeautyworld.ui.mobileAuth.enterOtp

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.existence.CheckUserExistence

/**
 * Created by android on 19/2/18.
 * model class for login
 */
class LoginModel : MyViewModel() {

    var response = MutableLiveData<CheckUserExistence>()

    fun authenticate(phone_number: String) {

        isLoading.value = true
        LoginRepository.getData({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, phone_number)
    }
}