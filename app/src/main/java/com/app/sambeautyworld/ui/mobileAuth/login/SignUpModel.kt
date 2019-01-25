package com.app.sambeautyworld.ui.mobileAuth.login


import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.register.RegisterUserPojo

/**
 * Created by android on 19/2/18.
 * model class for login
 */
class SignUpModel : MyViewModel() {

    var response = MutableLiveData<RegisterUserPojo>()

    fun registerUser(phone: String,
                     name: String,
                     address: String,
                     email: String,
                     dob: String,
                     app_type: String,
                     login_type: String,
                     social_id: String,
                     device_token: String?
    ) {

        isLoading.value = true
        SignUpRepository.registerUser({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, phone, name, address, email, dob, app_type, login_type, social_id, device_token!!)

    }

}

