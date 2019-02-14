package com.app.sambeautyworld.ui.filters

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.filter.GetFilterPojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class GetFilterModel : MyViewModel() {

    var response = MutableLiveData<GetFilterPojo>()

    fun getFilters(user_id: String) {

        isLoading.value = true
        GetFilterRepository.getFilters({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id)
    }
}