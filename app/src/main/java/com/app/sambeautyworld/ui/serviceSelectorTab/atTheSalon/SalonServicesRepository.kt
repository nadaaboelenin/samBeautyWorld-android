package com.app.sambeautyworld.ui.serviceSelectorTab.atTheSalon

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.salonListBasedOnService.SalonListBasedOnServicePojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object SalonServicesRepository {
    private val webService = ApiHelper.createService()

    fun getData(successHandler: (SalonListBasedOnServicePojo) -> Unit, failureHandler: (String) -> Unit,
                service_id: String
    ) {
        webService.salonListBasedOnService(service_id).enqueue(object : Callback<SalonListBasedOnServicePojo> {
            override fun onResponse(call: Call<SalonListBasedOnServicePojo>?, response: Response<SalonListBasedOnServicePojo>?) {
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

            override fun onFailure(call: Call<SalonListBasedOnServicePojo>?, t: Throwable?) {
                t?.let {
                    failureHandler("No Salons Were found in the requested area")
                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }


    fun homeBased(successHandler: (SalonListBasedOnServicePojo) -> Unit, failureHandler: (String) -> Unit,
                  user_id: String,
                  service_id: String,
                  latitude: String,
                  longitude: String
    ) {
        webService.homeSalons(user_id, service_id, latitude, longitude).enqueue(object : Callback<SalonListBasedOnServicePojo> {
            override fun onResponse(call: Call<SalonListBasedOnServicePojo>?, response: Response<SalonListBasedOnServicePojo>?) {
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

            override fun onFailure(call: Call<SalonListBasedOnServicePojo>?, t: Throwable?) {
                t?.let {
                    failureHandler(it.localizedMessage)
                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }

}