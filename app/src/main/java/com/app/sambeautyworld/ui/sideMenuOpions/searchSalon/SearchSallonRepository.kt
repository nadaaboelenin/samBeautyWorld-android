package com.app.sambeautyworld.ui.sideMenuOpions.searchSalon

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.searchsallonpojo.SearchSaloonListPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SearchSallonRepository {

    private val webService = ApiHelper.createService()

    fun getAllSalons(successHandler: (SearchSaloonListPojo) -> Unit, failureHandler: (String) -> Unit) {
        webService.getAllSalons().enqueue(object : Callback<SearchSaloonListPojo> {
            override fun onResponse(call: Call<SearchSaloonListPojo>?, response: Response<SearchSaloonListPojo>?) {
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

            override fun onFailure(call: Call<SearchSaloonListPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }

}