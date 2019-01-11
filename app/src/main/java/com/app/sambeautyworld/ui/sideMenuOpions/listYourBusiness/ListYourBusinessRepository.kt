package com.app.sambeautyworld.ui.sideMenuOpions.listYourBusiness

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object ListYourBusinessRepository {
    private val webService = ApiHelper.createService()

    fun getData(successHandler: (ListYourBusinessPojo) -> Unit, failureHandler: (String) -> Unit,
                name: String,
                business_name: String,
                phone: String,
                email: String
    ) {
        webService.listYourBusiness(name, business_name, phone, email).enqueue(object : Callback<ListYourBusinessPojo> {
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