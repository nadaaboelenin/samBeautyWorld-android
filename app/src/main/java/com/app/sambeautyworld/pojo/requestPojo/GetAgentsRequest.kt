package com.app.sambeautyworld.pojo.requestPojo

public class GetAgentsRequest(var owner_id: String? = null,
                              var services: ArrayList<Services> = ArrayList()) {

    public class Services(var id: String?)
}
