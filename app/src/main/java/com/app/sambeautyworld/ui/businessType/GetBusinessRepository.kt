package com.app.sambeautyworld.ui.businessType

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.businessType.GetBusinessTypePojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object GetBusinessRepository {
    private val webService = ApiHelper.createService()

    fun getData(successHandler: (GetBusinessTypePojo) -> Unit, failureHandler: (String) -> Unit
    ) {
        webService.getAllBusinessType().enqueue(object : Callback<GetBusinessTypePojo> {
            override fun onResponse(call: Call<GetBusinessTypePojo>?, response: Response<GetBusinessTypePojo>?) {
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

            override fun onFailure(call: Call<GetBusinessTypePojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}