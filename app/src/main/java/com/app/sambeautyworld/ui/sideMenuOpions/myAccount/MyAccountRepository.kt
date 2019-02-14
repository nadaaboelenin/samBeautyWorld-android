package com.app.sambeautyworld.ui.sideMenuOpions.myAccount

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.accountPojo.GetAccountPojo
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
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

    fun editProfile(successHandler: (ListYourBusinessPojo) -> Unit, failureHandler: (String) -> Unit,
                    user_id: String,
                    full_name: String,
                    address: String,
                    email: String,
                    dob: String
    ) {
        webService.updateProfile(user_id, full_name, address, email, dob).enqueue(object : Callback<ListYourBusinessPojo> {
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