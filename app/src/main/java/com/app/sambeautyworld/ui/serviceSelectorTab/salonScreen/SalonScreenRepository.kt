package com.app.sambeautyworld.ui.serviceSelectorTab.salonScreen

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import com.app.sambeautyworld.pojo.salonScreen.SalonScreenPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object SalonScreenRepository {
    private val webService = ApiHelper.createService()


    fun getData(successHandler: (ListYourBusinessPojo) -> Unit, failureHandler: (String) -> Unit,
                user_id: String,
                salon_id: String
    ) {
        webService.addBookmark(user_id, salon_id).enqueue(object : Callback<ListYourBusinessPojo> {
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


    fun getAllServices(successHandler: (SalonScreenPojo) -> Unit, failureHandler: (String) -> Unit,
                       owner_id: String
    ) {
        webService.getAllServices(owner_id).enqueue(object : Callback<SalonScreenPojo> {
            override fun onResponse(call: Call<SalonScreenPojo>?, response: Response<SalonScreenPojo>?) {
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

            override fun onFailure(call: Call<SalonScreenPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }


}