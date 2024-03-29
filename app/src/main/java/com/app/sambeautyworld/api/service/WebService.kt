package com.app.sambeautyworld.api.service

import com.app.sambeautyworld.pojo.accountPojo.GetAccountPojo
import com.app.sambeautyworld.pojo.agents.AgentsPojo
import com.app.sambeautyworld.pojo.businessType.GetBusinessTypePojo
import com.app.sambeautyworld.pojo.existence.CheckUserExistence
import com.app.sambeautyworld.pojo.filter.GetFilterPojo
import com.app.sambeautyworld.pojo.filteredsearch.FilteredSearchPojo
import com.app.sambeautyworld.pojo.getbookmark.GetBookmarksPojo
import com.app.sambeautyworld.pojo.listYourBusiness.ListYourBusinessPojo
import com.app.sambeautyworld.pojo.mainHome.MainHomePojo
import com.app.sambeautyworld.pojo.register.RegisterUserPojo
import com.app.sambeautyworld.pojo.requestPojo.GetAgentsRequest
import com.app.sambeautyworld.pojo.requestbooking.Request
import com.app.sambeautyworld.pojo.salonListBasedOnService.SalonListBasedOnServicePojo
import com.app.sambeautyworld.pojo.salonLocations.SalonLocationsPojo
import com.app.sambeautyworld.pojo.salonScreen.SalonScreenPojo
import com.app.sambeautyworld.pojo.searchsallonpojo.SearchSaloonListPojo
import com.app.sambeautyworld.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *  Created by shubham on 3/11/17.
 *  All web services are declared here
 */
interface WebService {

    @POST(Constants.LOGIN)
    @FormUrlEncoded
    fun loginUser(@Field("phone") phone: String): Call<CheckUserExistence>


    @POST(Constants.REGISTER)
    @FormUrlEncoded
    fun registerUser(@Field("phone") phone: String,
                     @Field("name") name: String,
                     @Field("address") address: String,
                     @Field("email") email: String,
                     @Field("dob") dob: String,
                     @Field("app_type") app_type: String,
                     @Field("login_type") login_type: String,
                     @Field("social_id") social_id: String,
                     @Field("device_token") device_token: String

    ): Call<RegisterUserPojo>

    @POST(Constants.FEATURED_SERVICES)
    @FormUrlEncoded
    fun featuredServices(@Field("user_id") user_id: String,
                         @Field("business_type") business_type: String
    ): Call<MainHomePojo>


    @POST(Constants.LIST_YOUR_BUSINESS)
    @FormUrlEncoded
    fun listYourBusiness(
            @Field("name") name: String,
            @Field("business_name") business_name: String,
            @Field("phone") phone: String,
            @Field("email") email: String
    ): Call<ListYourBusinessPojo>


    @POST(Constants.UPDATE_PROFILE)
    @FormUrlEncoded
    fun updateProfile(
            @Field("user_id") user_id: String,
            @Field("full_name") full_name: String,
            @Field("address") address: String,
            @Field("email") email: String,
            @Field("dob") dob: String
    ): Call<ListYourBusinessPojo>

    @POST(Constants.SEND_FEEDBACK)
    @FormUrlEncoded
    fun submitFeedback(
            @Field("user_id") user_id: String,
            @Field("message") message: String
    ): Call<ListYourBusinessPojo>

    @POST(Constants.ADD_BOOKMARK)
    @FormUrlEncoded
    fun addBookmark(
            @Field("user_id") user_id: String,
            @Field("salon_id") salon_id: String
    ): Call<ListYourBusinessPojo>


    @POST(Constants.SALON_LIST_BASED_ON_SERVICE)
    @FormUrlEncoded
    fun salonListBasedOnService(
            @Field("service_id") service_id: String
    ): Call<SalonListBasedOnServicePojo>


    @POST(Constants.ALL_SALON_SERVICES)
    @FormUrlEncoded
    fun getAllServices(
            @Field("owner_id") owner_id: String,
            @Field("user_id") user_id: String,
            @Field("salon_id") salon_id: String
    ): Call<SalonScreenPojo>


    @POST(Constants.GET_ALL_ACCOUNT_INFO)
    @FormUrlEncoded
    fun getProfileInfo(
            @Field("user_id") owner_id: String
    ): Call<GetAccountPojo>


    @POST(Constants.ALL_SALON_LIST)
    fun getAllSalons(): Call<SearchSaloonListPojo>

    @POST(Constants.BUSINESS_TYPE)
    fun getAllBusinessType(): Call<GetBusinessTypePojo>

    @POST(Constants.SALON_HOME_SERVICES)
    @FormUrlEncoded
    fun availableAtTheHome(
            @Field("user_id") user_id: String,
            @Field("service_id") service_id: String
    ): Call<SalonLocationsPojo>


    @POST(Constants.HOME_SALONS)
    @FormUrlEncoded
    fun homeSalons(
            @Field("user_id") user_id: String,
            @Field("service_id") service_id: String,
            @Field("latitude") latitude: String,
            @Field("longitude") longitude: String
    ): Call<SalonListBasedOnServicePojo>

    @POST(Constants.GET_FILTER)
    @FormUrlEncoded
    fun getFilters(
            @Field("user_id") user_id: String
    ): Call<GetFilterPojo>

    @POST(Constants.GET_BOOKMARK)
    @FormUrlEncoded
    fun getBookmark(
            @Field("user_id") user_id: String
    ): Call<GetBookmarksPojo>


    @POST(Constants.CHOOSE_AGENT)
    fun getAgents(
            @Body request: GetAgentsRequest
    ): Call<AgentsPojo>


    @POST(Constants.SEARCH_FILTERED)
    @FormUrlEncoded
    fun serachFiltered(@Field("user_id") user_id: String,
                       @Field("min_price") min_price: String,
                       @Field("max_price") max_price: String,
                       @Field("at_salon") at_salon: String,
                       @Field("at_home") at_home: String,
                       @Field("at_makeup") at_makeup: String,
                       @Field("latitude") latitude: String,
                       @Field("longitude") longitude: String
    ): Call<FilteredSearchPojo>


    @POST(Constants.BOOK_A_SERVICE)
    fun bookAService(@Body request: Request): Call<ListYourBusinessPojo>


    @POST(Constants.CANCEL_REQUEST)
    @FormUrlEncoded
    fun cancelService(@Field("booking_id") booking_id: String,
                      @Field("user_id") user_id: String
    ): Call<ListYourBusinessPojo>

    /**
    owner_id:12
    agent_id:5
    user_id:5
    quantity:2
    service_request:Hair Blow
    date:2019-02-04
    time:1:00 PMe:50

    service_pric
     */


//    @POST(Constants.UPDATE_INFO)
//    fun updateInformation(@Body request: ResetPasswordRequest): Call<UpdateInfoPojo>

//
//    @Headers("Accept: " + "application/json")
//    @POST("forgot_password")
//    fun resetPassword(@Body request: ResetPasswordRequest): Call<Status>
//

//    @Headers("Accept: " + "application/json")
//    @POST("signup")
//    fun register(@Body request: RegisterationRequest): Call<Status>


//
//    @Headers("Accept: " + "application/json")
//    @POST("mr-register")
//    fun registeration(@Body request: RegisterationRequest): Call<RegisterationResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("change-password")
//    fun changePassword(@Body request: ChangePasswordRequest): Call<ChangePasswordResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("contact-us")
//    fun contactUs(@Body request: ContactUsRequest): Call<ContactUsResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("master-data/products-list?name=Sa")
//    fun productsPromotedList(@Query("name") value: String,
//                             @Query("start") start: Int,
//                             @Query("length") length: Int): Call<ProductListResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("master-data/register-fields")
//    fun getIndusteryList(): Call<RegisterFeildResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("view-doctor")
//    fun doctorDetails(@Query("id") doctorId:Int): Call<ViewDoctorDetailsResponse>
//
//    @Headers("Accept: " + "application/json")
//    @GET("contact")
//    fun unverifiedDoctorDetails(@Query("id") doctorId:Int): Call<UnverifiedDoctorResponse>
//    fun doctorDetails(@Header("Authorization") token: String?, @Query("id") doctorId: Int?): Call<ViewDoctorDetailsResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("master-data/specialty-list")
//    fun getSpecializationList(): Call<SpecializationListResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("search-doctor")
//    fun searchDoctor(@Body request: SearchDoctorRequeest): Call<SearchDoctorResponse>
//
//    @Headers("Accept: " + "application/json")
//    @POST("appointment")
//    fun bookSlot(@Body request: BookingRequest): Call<BookingResponse>
//

//
//    @Headers("Accept: " + "application/json")
//    @POST("appointment")
//    fun bookSlotUnverifiedDoctor(@Body request: UnverifiedDoctorBookingRequest): Call<BookingResponse>
//
//    @Headers("Accept: " + "application/json")
//    @GET("doctor-schedule")
//    fun getDoctorSchedule(@Query("id") value: Int?,
//                          @Query("address_id") addressId: Int?,
//                          @Query("date") date: String?): Call<DoctorScheduleReponse>
//
//    @Headers("Accept: " + "application/json")
//    @GET("contact-schedule")
//    fun getUnverifiedDoctorSchedule(@Query("id") value: Int?,
//
//                          @Query("date") date: String?): Call<DoctorScheduleReponse>
//
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("last-visits")
//    fun appointmentsList(@Body request: ViewAnalyticsRequest): Call<ViewAnalyticsResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("appointments-list")
//    fun viewScheduleList(@Body request: ViewScheduleRequest): Call<ViewScheduleResponse>
//
//    @Headers("Accept: " + "application/json")
//    @POST("contact")
//    fun createContacts( @Body request: ContactRequest): Call<ContactResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("-invitation")
//    fun sendInvitation(@Body request: InviteDoctorRequest): Call<InviteDoctorResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @HTTP(method = "DELETE", path = "contact", hasBody = true)
//    fun deleteContact( @Body request: DeleteUnverifiedDoctorRequest ): Call<DeleteUnverifiedDoctorResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("contact-sugesstion")
//    fun getViewSuggestionList(@Query("id") id: Int?
//                              ,@Query("start") start: Int?
//                              ,@Query("length") length: Int?): Call<SuggestionResponse>
//
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("notifications")
//    fun getNotificationList(@Query("page") page: Int?
//                              ): Call<NotificationResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("contact-sugesstion?id=1465")
//    fun getViewSuggestionList(@Header("Authorization") token: String?): Call<ViewSuggestionResponse>
//
//    @Headers("Accept: " + "application/json")
//    @GET("view-contact")
//    fun getUserDetails(@Query("id") id: Int?): Call<GetUserDetailResponse>
//
//    @Headers("Accept: " + "application/json")
//    @POST("todo")
//    fun getTodoList(@Body request: TodoRequest): Call<TodoResponse>
//
//    @Headers("Accept: " + "application/json")
//    @POST("confirm-sugesstion")
//    fun addToContact(@Body request: AddToContactRequest): Call<AddToContactResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("confirm-doctor")
//    fun addToContactWithPriority(@Body request: AddToPriorityContactRequest): Call<AddToContactResponse>
//
//
//
//
//    @Headers("Accept: " + "application/json")
//    @PUT("contact")
//    fun updateToContact(@Body request: ContactRequest): Call<ContactResponse>
//
//    @Headers("Accept: " + "application/json")
//    @PUT("profile")
//    fun updateProfile(@Body request: RegisterationRequest): Call<UpdateProfileSignUpResponse>
//
//    @Headers("Accept: " + "application/json")
//    @HTTP(method = "DELETE", path = "appointment", hasBody = true)
//    fun deleteAppointment( @Body request: DeleteAppointmentRequest ): Call<DeleteAppointmentResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @HTTP(method = "DELETE", path = "todo/{id}")
//    fun deleteTodo(@Path("id")  id: String): Call<DeleteAppointmentResponse>
//
//
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("push-notification")
//    fun pushNotification(@Body request: PushNotificationRequest): Call<PushNotificationResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @DELETE("delete-account")
//    fun deleteAccount(): Call<DeleteAccountResponse>
//
//
//
//    @Headers("Accept: " + "application/json")
//    @GET("sample-requests")
//    fun getSampleList(@Query("page") page: Int? ): Call<SampleRequestResponse>
//
//
//
//    @Headers("Accept: " + "application/json")
//    @PUT("sample-request")
//    fun acceptRejectSampleCall(@Body request: SampleAcceptRejectRequest): Call<SampleAcceptRejectResponse>
//
//
//
//    @Headers("Accept: " + "application/json")
//    @POST("appointment-sugesstions")
//    fun suggestionList(@Body request: SuggestionRequest): Call<ViewScheduleResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @PUT("token")
//    fun updateFirebaseDeviceToken(@Body request: UpdateTokenRequest): Call<UpdateTokenResponse>
//
//    @Headers("Accept: " + "application/json")
//    @HTTP(method = "DELETE", path = "sample-request", hasBody = true)
//    fun deleteSample( @Body request: DeleteSampleRequest ): Call<DeleteSampleResponse>
//
//
//    @Headers("Accept: " + "application/json")
//    @HTTP(method = "DELETE", path = "notification", hasBody = true)
//    fun deleteNotification( @Body request: NotificationRequest): Call<NotificationOperationResponse>
//
//    @Headers("Accept: " + "application/json")
//    @PUT("notification")
//    fun updateNotificationStatus(@Body request: NotificationRequest): Call<NotificationOperationResponse>
//
//    @Headers("Accept: " + "application/json")
//    @GET("notification-count")
//    fun getNotificationCount(): Call<NotificationCountResponse>


}