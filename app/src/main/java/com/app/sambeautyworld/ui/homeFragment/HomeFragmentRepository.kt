package com.app.sambeautyworld.ui.homeFragment

import com.app.sambeautyworld.api.service.ApiHelper
import com.app.sambeautyworld.pojo.getbookmark.GetBookmarksPojo
import com.app.sambeautyworld.pojo.mainHome.MainHomePojo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by ${Shubham} on 09/15/18.
 */
object HomeFragmentRepository {
    private val webService = ApiHelper.createService()

    fun getData(successHandler: (MainHomePojo) -> Unit, failureHandler: (String) -> Unit,
                user_id: String, id: String
    ) {
        webService.featuredServices(user_id, id).enqueue(object : Callback<MainHomePojo> {
            override fun onResponse(call: Call<MainHomePojo>?, response: Response<MainHomePojo>?) {
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
            override fun onFailure(call: Call<MainHomePojo>?, t: Throwable?) {
                t?.let {
                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }


    fun getBookmarks(successHandler: (GetBookmarksPojo) -> Unit, failureHandler: (String) -> Unit,
                     user_id: String
    ) {
        webService.getBookmark(user_id).enqueue(object : Callback<GetBookmarksPojo> {
            override fun onResponse(call: Call<GetBookmarksPojo>?, response: Response<GetBookmarksPojo>?) {
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

            override fun onFailure(call: Call<GetBookmarksPojo>?, t: Throwable?) {
                t?.let {
                    //failureHandler(ApiFailureTypes.getFailureMessage(it))
                }
            }
        })
    }
}