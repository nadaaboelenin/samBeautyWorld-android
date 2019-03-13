package com.app.sambeautyworld.ui.chooseAgent

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.agents.AgentsPojo
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import com.app.sambeautyworld.pojo.requestPojo.GetAgentsRequest
import com.app.sambeautyworld.pojo.requestbooking.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object GetAgentsRepository {
    private val webService = ApiHelper.createService()

    fun getAgents(successHandler: (AgentsPojo) -> Unit, failureHandler: (String) -> Unit,
                  getAgentsRequest: GetAgentsRequest
    ) {
        webService.getAgents(getAgentsRequest).enqueue(object : Callback<AgentsPojo> {
            override fun onResponse(call: Call<AgentsPojo>?, response: Response<AgentsPojo>?) {
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

            override fun onFailure(call: Call<AgentsPojo>?, t: Throwable?) {
                t?.let {

                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }


    fun bookService(successHandler: (ListYourBusinessPojo) -> Unit, failureHandler: (String) -> Unit,
                    getAgentsRequest: Request
    ) {
        webService.bookAService(getAgentsRequest).enqueue(object : Callback<ListYourBusinessPojo> {
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
                    failureHandler(it.message!!)
                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}