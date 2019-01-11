package com.app.sambeautyworld.ui.mobileAuth.enterOtp

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.existence.CheckUserExistence
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by android on 19/2/18.
 *
 * Data Provider for the application
 */
object LoginRepository {
    private val webService = ApiHelper.createService()

    fun getData(successHandler: (CheckUserExistence) -> Unit, failureHandler: (String) -> Unit,
                phone_number: String) {
        webService.loginUser(phone_number).enqueue(object : Callback<CheckUserExistence> {
            override fun onResponse(call: Call<CheckUserExistence>?, response: Response<CheckUserExistence>?) {
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

            override fun onFailure(call: Call<CheckUserExistence>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}