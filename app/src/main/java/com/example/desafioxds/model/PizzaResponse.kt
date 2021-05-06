package com.example.desafioxds.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class PizzaResponse() :Parcelable{
    @SerializedName("id")
    var id:String?=""

    @SerializedName("name")
    var name:String?=""

    @SerializedName("imageUrl")
    var imageUrl:String?=""

    @SerializedName("rating")
    var rating:Float?=null

    @SerializedName("priceP")
    var priceP:Float?=null

    @SerializedName("priceM")
    var priceM:Float?=null

    @SerializedName("priceG")
    var priceG:Float?=null

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        imageUrl = parcel.readString()
        rating = parcel.readValue(Float::class.java.classLoader) as? Float
        priceP = parcel.readValue(Float::class.java.classLoader) as? Float
        priceM = parcel.readValue(Float::class.java.classLoader) as? Float
        priceG = parcel.readValue(Float::class.java.classLoader) as? Float
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeValue(rating)
        parcel.writeValue(priceP)
        parcel.writeValue(priceM)
        parcel.writeValue(priceG)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PizzaResponse> {
        override fun createFromParcel(parcel: Parcel): PizzaResponse {
            return PizzaResponse(parcel)
        }

        override fun newArray(size: Int): Array<PizzaResponse?> {
            return arrayOfNulls(size)
        }
    }
}