package com.app.sambeautyworld.ui.homeFragment

import android.arch.lifecycle.MutableLiveData
import com.app.sambeautyworld.api.model.MyViewModel
import com.app.sambeautyworld.pojo.filteredsearch.FilteredSearchPojo
import com.app.sambeautyworld.pojo.getbookmark.GetBookmarksPojo
import com.app.sambeautyworld.pojo.mainHome.MainHomePojo

/**
 * Created by ${Shubham} on 09/15/18.
 */
class HomeFragmentModel : MyViewModel() {

    var response = MutableLiveData<MainHomePojo>()
    var response_bookmark = MutableLiveData<GetBookmarksPojo>()
    var response_filtered = MutableLiveData<FilteredSearchPojo>()

    fun getServices(user_id: String, id: String) {
        isLoading.value = true
        HomeFragmentRepository.getData({
            response.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id, id)
    }

    fun getBookmark(user_id: String) {
        isLoading.value = true
        HomeFragmentRepository.getBookmarks({
            response_bookmark.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id)
    }


    fun getFiltered(user_id: String,
                    min_price: String,
                    max_price: String,
                    at_salon: String,
                    at_home: String,
                    at_makeup: String,
                    latitude: String,
                    longitude: String) {
        isLoading.value = true
        HomeFragmentRepository.getFiltered({
            response_filtered.value = it
            isLoading.value = false
        }, {
            apiError.value = it
            isLoading.value = false
        }, user_id,
                min_price,
                max_price,
                at_salon,
                at_home,
                at_makeup,
                latitude,
                longitude)
    }
}