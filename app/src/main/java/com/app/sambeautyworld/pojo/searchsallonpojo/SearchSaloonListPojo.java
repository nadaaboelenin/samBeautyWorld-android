package com.app.sambeautyworld.pojo.searchsallonpojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchSaloonListPojo {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("allSalonList")
    @Expose
    public List<AllSalonList> allSalonList = null;

}
