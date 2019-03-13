package com.app.sambeautyworld.pojo.mainHome;

import android.os.Parcel;
import android.os.Parcelable;

public class Past implements Parcelable {

    public static final Creator<Past> CREATOR = new Creator<Past>() {
        @Override
        public Past createFromParcel(Parcel in) {
            return new Past(in);
        }

        @Override
        public Past[] newArray(int size) {
            return new Past[size];
        }
    };
    public String icon;
    public String business_name;
    public String date;
    public String schedule;
    public String service_request;
    public String service_price;
    public String time;
    public String mobile_number;
    public String latitude;
    public String longitude;

    protected Past(Parcel in) {
        icon = in.readString();
        business_name = in.readString();
        date = in.readString();
        schedule = in.readString();
        service_request = in.readString();
        service_price = in.readString();
        time = in.readString();
        mobile_number = in.readString();
        latitude = in.readString();
        longitude = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(icon);
        dest.writeString(business_name);
        dest.writeString(date);
        dest.writeString(schedule);
        dest.writeString(service_request);
        dest.writeString(service_price);
        dest.writeString(time);
        dest.writeString(mobile_number);
        dest.writeString(latitude);
        dest.writeString(longitude);
    }
}