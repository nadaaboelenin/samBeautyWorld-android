package com.app.sambeautyworld.ui.sideMenuOpions.sendFeedback

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object SendFeedbackRepository {
    private val webService = ApiHelper.createService()

    fun postFeedback(successHandler: (ListYourBusinessPojo) -> Unit, failureHandler: (String) -> Unit,

                     user_id: String,
                     message: String
    ) {
        webService.submitFeedback(user_id, message).enqueue(object : Callback<ListYourBusinessPojo> {
            override fun onResponse(call: Call<ListYourBusinessPojo>?, response: Response<ListYourBusinessPojo>?) {
                response?.body()?.let {
                    successHandler(it)

                }
                if (response?.code() == 422) {
                    response.errorBody()?.let {
                        val error = ApiHelper.handleAuthenticationError(response.errorBody()!!)
                        failureHandler(error)
                    }

                } else {
                    response?.errorBody()?.let {
                        val error = ApiHelper.handleApiError(response.errorBody()!!)
                        failureHandler(error)
                    }
                }
            }

            override fun onFailure(call: Call<ListYourBusinessPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}