package com.app.sambeautyworld.pojo.salonScreen;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("product_name")
    @Expose
    public String product_name;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("product_desc")
    @Expose
    public String product_desc;
    @SerializedName("product_image")
    @Expose
    public String product_image;

    @SerializedName("count")
    @Expose
    public int count = 0;

}