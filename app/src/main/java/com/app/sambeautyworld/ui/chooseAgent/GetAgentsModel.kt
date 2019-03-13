package com.app.sambeautyworld.ui.chooseAgent

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.agents.AgentsPojo
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import com.app.sambeautyworld.pojo.requestPojo.GetAgentsRequest
import com.app.sambeautyworld.pojo.requestbooking.Request

/**
 * Created by ${Shubham} on 09/15/18.
 */
class GetAgentsModel : MyViewModel() {

    var response = MutableLiveData<AgentsPojo>()
    var response_book = MutableLiveData<ListYourBusinessPojo>()

    fun getAgents(getAgentsRequest: GetAgentsRequest) {
        isLoading.value = true
        GetAgentsRepository.getAgents({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, getAgentsRequest)
    }


    fun bookService(getAgentsRequest: Request) {
        isLoading.value = true
        GetAgentsRepository.bookService({
            response_book.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, getAgentsRequest)
    }
}