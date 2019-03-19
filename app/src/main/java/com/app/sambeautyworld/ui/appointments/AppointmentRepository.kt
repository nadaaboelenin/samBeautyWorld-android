package com.app.sambeautyworld.ui.appointments

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object AppointmentRepository {
    private val webService = ApiHelper.createService()

    fun cancelService(successHandler: (ListYourBusinessPojo) -> Unit, failureHandler: (String) -> Unit,
                      booking_id: String,
                      user_id: String
    ) {
        webService.cancelService(booking_id, user_id).enqueue(object : Callback<ListYourBusinessPojo> {
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

                    failureHandler(it.localizedMessage)
                }
            }
        })
    }
}