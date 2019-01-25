package com.app.sambeautyworld.ui.sideMenuOpions.searchSalon

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.searchsallonpojo.SearchSaloonListPojo
import com.app.sambeautyworld.ui.sideMenuOpions.sendFeedback.SendFeedbackRepository

class SearchSaloonModel : MyViewModel() {

    var response = MutableLiveData<SearchSaloonListPojo>()

    fun getAllSalons() {
        isLoading.value = true
        SearchSallonRepository.getAllSalons({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        })
    }


}