package com.app.sambeautyworld.ui.homeFragment

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.listServices.ListAllServicesPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object HomeFragmentRepository {
    private val webService = ApiHelper.createService()

    fun getData(successHandler: (ListAllServicesPojo) -> Unit, failureHandler: (String) -> Unit
    ) {
        webService.featuredServices().enqueue(object : Callback<ListAllServicesPojo> {
            override fun onResponse(call: Call<ListAllServicesPojo>?, response: Response<ListAllServicesPojo>?) {
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

            override fun onFailure(call: Call<ListAllServicesPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}