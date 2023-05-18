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
    @SerializedName("description")
    @Expose
    var description: String? = null

    constructor(
        uri : String,
        label: String,
        image_url: String,
        url: String,
        dietLabel: MutableList<String>,
        description: String,
        ingredients: MutableList<String>,
        calories: Float,
        time: Int,
        yield: Int
    ){
        this.uri = uri

        this.label = label

        this.image_url = image_url

        this.url = url

        this.description = description

        this.dietLabel = dietLabel

        this.ingredients = ingredients

        this.calories = calories.toDouble()

        this.time = time

        this.yield = `yield`
    }

    constructor(){}

    constructor(source: Parcel) : this(
    )

    fun prepare(cocina:String){

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RecipeHit) return false
        return getId() == other.getId()
    }

    override fun hashCode(): Int {
        return getId().hashCode()
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