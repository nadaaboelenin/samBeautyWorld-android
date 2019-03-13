package com.app.sambeautyworld.pojo.requestPojo

public class GetAgentsRequest(var owner_id: String? = null,
                              var sub_services: ArrayList<Sub_services> = ArrayList(), var day: String) {

    public class Sub_services(var id: String?)
}
