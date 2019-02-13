package com.app.sambeautyworld.pojo.mainHome;

import android.os.Parcel;
import android.os.Parcelable;

public class SpecialOffer implements Parcelable {

    public String salon_id;
    public String salon_name;
    public String salon_location;
    public String salon_logo;
    public String salon_for;
    public String actual_price;
    public String discount_price;
    public String service_at;

    public static final Creator<SpecialOffer> CREATOR = new Creator<SpecialOffer>() {
        @Override
        public SpecialOffer createFromParcel(Parcel in) {
            return new SpecialOffer(in);
        }

        @Override
        public SpecialOffer[] newArray(int size) {
            return new SpecialOffer[size];
        }
    };

    protected SpecialOffer(Parcel in) {
        salon_id = in.readString();
        salon_name = in.readString();
        salon_location = in.readString();
        salon_logo = in.readString();
        salon_for = in.readString();
        actual_price = in.readString();
        discount_price = in.readString();
        service_at = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(salon_id);
        dest.writeString(salon_name);
        dest.writeString(salon_location);
        dest.writeString(salon_logo);
        dest.writeString(salon_for);
        dest.writeString(actual_price);
        dest.writeString(discount_price);
        dest.writeString(service_at);
    }
}