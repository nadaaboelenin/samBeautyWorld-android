package com.app.sambeautyworld.ui.serviceSelectorTab.atHome

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.salonLocations.SalonLocationsPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object AvailableAtHomeRepository {
    private val webService = ApiHelper.createService()

    fun getSalons(successHandler: (SalonLocationsPojo) -> Unit, failureHandler: (String) -> Unit,
                  user_id: String,
                  service_id: String
    ) {
        webService.availableAtTheHome(user_id, service_id).enqueue(object : Callback<SalonLocationsPojo> {
            override fun onResponse(call: Call<SalonLocationsPojo>?, response: Response<SalonLocationsPojo>?) {
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

            override fun onFailure(call: Call<SalonLocationsPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}