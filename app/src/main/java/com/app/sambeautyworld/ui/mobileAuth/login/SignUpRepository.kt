package com.app.sambeautyworld.ui.mobileAuth.login

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.register.RegisterUserPojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by android on 19/2/18.
 *
 * Data Provider for the application
 */
object SignUpRepository {
    private val webService = ApiHelper.createService()

    fun registerUser(successHandler: (RegisterUserPojo)
    -> Unit, failureHandler:
                     (String) -> Unit,
                     phone: String,
                     name: String,
                     address: String,
                     email: String,
                     dob: String,
                     app_type: String,
                     login_type: String,
                     social_id: String,
                     device_token: String) {
        webService.registerUser(phone, name, address, email, dob, app_type, login_type, social_id, device_token)
                .enqueue(object : Callback<RegisterUserPojo> {
                    override fun onResponse(call: Call<RegisterUserPojo>?, response: Response<RegisterUserPojo>?) {
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

                    override fun onFailure(call: Call<RegisterUserPojo>?, t: Throwable?) {
                        t?.let {
                            //failureHandler(ApiFailureTypes().getFailureMessage(it))
                        }
                    }
                })
    }


}