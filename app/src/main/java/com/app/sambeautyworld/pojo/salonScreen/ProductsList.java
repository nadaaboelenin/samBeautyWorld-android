package com.app.sambeautyworld.pojo.salonScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductsList {

    @SerializedName("service_name")
    @Expose
    public String service_name;
    @SerializedName("products")
    @Expose
    public List<Product> products = null;

}