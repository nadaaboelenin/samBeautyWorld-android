package com.app.sambeautyworld.ui.sideMenuOpions.myAccount

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.accountPojo.GetAccountPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object MyAccountRepository {
    private val webService = ApiHelper.createService()

    fun getProfile(successHandler: (GetAccountPojo) -> Unit, failureHandler: (String) -> Unit,
                   owner_id: String
    ) {
        webService.getProfileInfo(owner_id).enqueue(object : Callback<GetAccountPojo> {
            override fun onResponse(call: Call<GetAccountPojo>?, response: Response<GetAccountPojo>?) {
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

            override fun onFailure(call: Call<GetAccountPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}