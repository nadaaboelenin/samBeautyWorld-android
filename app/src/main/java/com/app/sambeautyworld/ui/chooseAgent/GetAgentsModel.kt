package com.app.sambeautyworld.ui.chooseAgent

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.agents.AgentsPojo
import com.app.sambeautyworld.pojo.requestPojo.GetAgentsRequest

/**
 * Created by ${Shubham} on 09/15/18.
 */
class GetAgentsModel : MyViewModel() {

    var response = MutableLiveData<AgentsPojo>()

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
}