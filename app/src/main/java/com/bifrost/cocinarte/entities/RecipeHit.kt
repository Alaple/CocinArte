package com.bifrost.cocinarte.entities

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecipeHit : Parcelable{
    @SerializedName("uri")
    var uri : String? = null
    @SerializedName("label")
    @Expose
    var label: String? = null
    @SerializedName("image")
    @Expose
    var image_url: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("dietLabels")
    @Expose
    var dietLabel: MutableList<String>? = null
    @SerializedName("ingredientLines")
    @Expose
    var ingredients: MutableList<String>? = null
    @SerializedName("calories")
    @Expose
    var calories: Double = 0.0
    @SerializedName("totalTime")
    @Expose
    var time: Int? = 0
    @SerializedName("yield")
    @Expose
    var yield: Int? = 0
    constructor(
        uri : String,
        label: String,
        image_url: String,
        url: String,
        dietLabel: MutableList<String>,
        ingredients: MutableList<String>,
        calories: Float,
        time: Int,
        yield: Int
    ){
        var uri : String? = uri

        var label: String? = label

        var image_url: String? = image_url

        var url: String? = url

        var dietLabel: MutableList<String>? = dietLabel

        var ingredients: MutableList<String>? = ingredients

        var calories: Float = calories

        var time: Int? = time

        var yield: Int? = `yield`
    }

    constructor(){}

    constructor(source: Parcel) : this(
    )

    fun prepare(cocina:String){

    }

    fun getId(): String? {
        var idLength = uri!!.length

        return uri?.substring(idLength - 32)

    }


    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<RecipeHit> = object : Parcelable.Creator<RecipeHit> {
            override fun createFromParcel(source: Parcel): RecipeHit = RecipeHit(source)
            override fun newArray(size: Int): Array<RecipeHit?> = arrayOfNulls(size)
        }
    }

    override fun describeContents() = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

}