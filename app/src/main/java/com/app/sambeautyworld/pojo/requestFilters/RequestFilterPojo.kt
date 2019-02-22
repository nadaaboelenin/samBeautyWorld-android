package com.app.sambeautyworld.pojo.requestFilters

import android.os.Parcel
import android.os.Parcelable

data class RequestFilterPojo(var user_id: String,
                             var min_price: String,
                             var max_price: String,
                             var at_salon: String,
                             var at_home: String,
                             var at_makeup: String,
                             var latitude: String,
                             var longitude: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(user_id)
        parcel.writeString(min_price)
        parcel.writeString(max_price)
        parcel.writeString(at_salon)
        parcel.writeString(at_home)
        parcel.writeString(at_makeup)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RequestFilterPojo> {
        override fun createFromParcel(parcel: Parcel): RequestFilterPojo {
            return RequestFilterPojo(parcel)
        }

        override fun newArray(size: Int): Array<RequestFilterPojo?> {
            return arrayOfNulls(size)
        }
    }
}