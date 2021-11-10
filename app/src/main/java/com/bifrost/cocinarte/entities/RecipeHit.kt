package com.bifrost.cocinarte.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RecipeHit {
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



}