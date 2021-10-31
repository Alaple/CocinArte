package com.bifrost.cocinarte.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecipeHit(
    uri : String,
    label: String,
    image_url: String,
    url: String,
    dietLabel: MutableList<String>,
    ingredients: MutableList<String>,
    calories: Float,
    time: Int,
    yield: Int
) {
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

   /*init {
        this.uri = uri
        this.label = label
        this.yield = `yield`
        this.image_url = image_url
        this.url = url
        this.calories = calories
        this.dietLabel = dietLabel
        this.ingredients = ingredients
        this.time = time
    }*/

    /*override fun toString(): String {
        return label
    }*/



}