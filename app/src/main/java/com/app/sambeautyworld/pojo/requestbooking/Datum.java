package com.app.sambeautyworld.pojo.requestbooking;

public class Datum {

    public String service_request;
    public String service_price;
    public String quantity;
    public String agent_id;
    public String date;
    public String time;
    public String bussiness_id;
    public String service_time;

    public Datum(String service_request, String service_price, String quantity, String agent_id, String date, String time, String bussiness_id, String service_time) {
        this.service_request = service_request;
        this.service_price = service_price;
        this.quantity = quantity;
        this.agent_id = agent_id;
        this.date = date;
        this.time = time;
        this.bussiness_id = bussiness_id;
        this.service_time = service_time;
    }
}