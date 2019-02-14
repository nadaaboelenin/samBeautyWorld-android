package com.app.sambeautyworld.ui.filters

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.filter.GetFilterPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object GetFilterRepository {
    private val webService = ApiHelper.createService()
    fun getFilters(successHandler: (GetFilterPojo) -> Unit, failureHandler: (String) -> Unit,
                   user_id: String
    ) {
        webService.getFilters(user_id).enqueue(object : Callback<GetFilterPojo> {
            override fun onResponse(call: Call<GetFilterPojo>?, response: Response<GetFilterPojo>?) {
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

            override fun onFailure(call: Call<GetFilterPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}