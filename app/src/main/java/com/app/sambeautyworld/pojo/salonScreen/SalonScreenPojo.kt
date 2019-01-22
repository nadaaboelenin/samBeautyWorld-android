package com.app.sambeautyworld.pojo.salonScreen

import android.os.Parcel
import android.os.Parcelable
import com.app.sambeautyworld.pojo.mainHome.SpecialOffer

class SalonScreenPojo() : Parcelable {

    var status: Int? = null
    var media: List<String>? = null
    var rating: String? = null
    var hours: Hours? = null
    var latitude: String? = null
    var longitude: String? = null
    var about: About? = null
    var travel_fee: String? = null
    var min_order_price: String? = null
    var specialOffers: List<SpecialOffer>? = null
    var servicesList: List<ServicesList>? = null

    constructor(parcel: Parcel) : this() {
        status = parcel.readValue(Int::class.java.classLoader) as? Int
        media = parcel.createStringArrayList()
        rating = parcel.readString()
        latitude = parcel.readString()
        longitude = parcel.readString()
        travel_fee = parcel.readString()
        min_order_price = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(status)
        parcel.writeStringList(media)
        parcel.writeString(rating)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(travel_fee)
        parcel.writeString(min_order_price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SalonScreenPojo> {
        override fun createFromParcel(parcel: Parcel): SalonScreenPojo {
            return SalonScreenPojo(parcel)
        }

        override fun newArray(size: Int): Array<SalonScreenPojo?> {
            return arrayOfNulls(size)
        }
    }

}