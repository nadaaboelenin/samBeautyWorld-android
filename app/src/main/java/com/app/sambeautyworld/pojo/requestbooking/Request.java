package com.app.sambeautyworld.pojo.requestbooking;

import java.util.List;

public class Request {

    public String owner_id;
    public String user_id;
    public List<Datum> bookingData = null;

    public Request(String owner_id, String user_id, List<Datum> bookingData) {
        this.owner_id = owner_id;
        this.user_id = user_id;
        this.bookingData = bookingData;
    }
}