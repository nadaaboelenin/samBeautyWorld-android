package com.app.sambeautyworld.utils

/**
 * Created by android on 2/11/17.
 */
object Constants {
    const val IS_LOGGED_IN = "is_logged_in"
    const val BASE_URL = "http://webappsitesdemo.com/salon-app/api/"
    const val LOGIN = "login"
    const val TOKEN = "token"
    const val LOGIN_RESPONSE = "login_response"
    const val PAGE_URL = "page_url"
    const val UPDATE_INFO = "update-personal-info"
    const val IS_REGISTERED = "registered"
    const val PHONE_NUMBER = "phone_number"
    const val REQUEST_CAMERA = 1
    const val REQUEST_GALLERY = 2
    const val REQUEST_GPS_ENABLE_PERMISSION = 100
    const val LAT = "Lat"
    const val LNG = "lng"
    const val ISEDIT = "IsEditProfile"
    const val DIALOG_SHOWN = "dialog_shown"
    const val PERMISSION_READ_EXTERNAL_STORAGE = 123
    const val PERMISSION_REQUEST_CODE = 98
    const val LOCATION_PERMISSION_REQUEST_CODE = 101
    const val GOT_IT ="got_it"
    const val ID = "user_id"
    const val REGISTER = "register"
    const val FEATURED_SERVICES = "featuredServices"
    const val LIST_YOUR_BUSINESS = "listYourBusiness"
    const val SEND_FEEDBACK = "sendFeedBack"
    const val ADD_BOOKMARK = "addBookMarks"
    const val SALON_LIST_BASED_ON_SERVICE = "getSalonsBasedOnService"
    const val ALL_SALON_SERVICES = "getSalonAllServices"
    const val ALL_SALON_LIST = "getAllSalons"
    const val GET_ALL_ACCOUNT_INFO = "getProfileInfo"
    const val UPDATE_PROFILE = "updateProfileInfo"
    const val BUSINESS_TYPE = "allBusinessTypes"
    const val SALON_HOME_SERVICES = "salonHomeservices"
    const val HOME_SALONS = "getSalonNearBy"
    const val GET_FILTER = "getFilterData"
    const val SPLASH_TIME_OUT: Long = 3000
    const val HANDLER_DELAY_TIME:Long=2000
    const val NOTI_COUNT = "noti_count"
    const val APP_HIDDEN_FOLDER="/.besttyme"
    const val GET_BOOKMARK = "getAllBookmnarkData"
    const val CHOOSE_AGENT = "chooseAgent"
    const val SEARCH_FILTERED = "searchSalons"
    const val BOOK_A_SERVICE = "BookRequest"
    const val CANCEL_REQUEST = "cancelRequest"



    internal interface httpcodes {
        companion object {
            val STATUS_OK = 200
            val STATUS_BAD_REQUEST = 400
            val STATUS_SESSION_EXPIRED = 401
            val STATUS_PLAN_EXPIRED = 403
            val STATUS_VALIDATION_ERROR = 404
            val STATUS_SERVER_ERROR = 500
            val STATUS_UNKNOWN_ERROR = 503
            val STATUS_API_VALIDATION_ERROR = 422
        }
    }
    const val SNACK_BAR_DURATION = 2500
    const val IS_LOGOUT = "is_logout"
    const val IMAGE_UPLOAD_FAILURE_MESSAGE = "Uploaded failure please try again!"
    const val IMAGE_UPLOADED_MESSAGE = "Successfully uploaded"
    //FAILURE MESSAGES
    const val SOMETHING_WENT_WRONG = "Something went wrong please try again later!"
    const val FAILURE_TIME_OUT_ERROR = "Request time out!"
    const val FAILURE_SOMETHING_WENT_WRONG = "Something went wrong please try again later!"
    const val FAILURE_SERVER_NOT_RESPONDING = "Oops! looks like we are having internal problems. Please try again later."
    const val FAILURE_INTERNET_CONNECTION = "Internet connection appears to be offline. Please check your network settings."
    const val SESSION_EXPIRED = "Sorry, looks like you are logged in another device with the same user."
    const val NOTI_TYPE = "noti_type"
    const val IS_NUMBER_VERIFIED: String = "number_verified"
    const val SERVICE_ID: String = "service_id"
    const val BUSINES_OWNER: String = "business_owner_id"
    const val SERVICE_NAME: String = "service_name"
    const val FROM_SEARCH: String = "from_seacrch"
    const val SPECIAL_OFFERS: String = "special_offers"
    const val SALON_ID: String = "salon_id"

    //doctor appointment type enum
    enum class TYPE(val type: Int) {
        SIMPLE(0), BREAKFAST(1), LUNCH(2), TASK(3), UNVERIFIEDOCTOR(4)
    }
    enum class WEEK(val type: Int) {
        MONDAY(0), TUESDAY(1), WEDNESDAY(2), THURSDAY(3), FRIDAY(4), SATURDAY(5), SUNDAY(6)
    }



}