package com.app.sambeautyworld.ui.sideMenuOpions.sendFeedback

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class SendFeedbackModel : MyViewModel() {

    var response = MutableLiveData<ListYourBusinessPojo>()

    fun sendFeedback(user_id: String,
                     message: String) {

        isLoading.value = true
        SendFeedbackRepository.postFeedback({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id, message)
    }
}