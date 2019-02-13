package com.app.sambeautyworld.pojo.searchsallonpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllSalonList {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("business_name")
    @Expose
    public String business_name;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("business_logo")
    @Expose
    public String business_logo;

    @SerializedName("owner_id")
    @Expose
    public String owner_id;
}
