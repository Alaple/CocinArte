package com.bifrost.cocinarte.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Recipe (
    id : String,
    title : String,
    description: String,
    yield: Int,
    image_url: String,
    calories: Float,
    dietLabel: MutableList<DietLabel>,
    ingredients: MutableList<Ingredient>,
    mealType: MutableList<MealType>,
    cuisineType: MutableList<CuisineType>,
    category: MutableList<Category>

) {

    @SerializedName("")
    @Expose
    var title : String = title
    @SerializedName("")
    @Expose
    var description: String = description
    @SerializedName("")
    @Expose
    var yield: Int = `yield`
    @SerializedName("")
    @Expose
    var image_url: String = image_url
    @SerializedName("")
    @Expose
    var calories: Float = calories
    @SerializedName("")
    @Expose
    var dietLabel: MutableList<DietLabel> = dietLabel
    @SerializedName("")
    @Expose
    var ingredients: MutableList<Ingredient> = ingredients
    @SerializedName("")
    @Expose
    var mealType: MutableList<MealType> = mealType
    @SerializedName("")
    @Expose
    var cuisineType: MutableList<CuisineType> = cuisineType
    @SerializedName("")
    @Expose
    var category: MutableList<Category> = category

    init {
        this.title = title
        this.description = description
        this.yield = `yield`
        this.image_url = image_url
        this.calories = calories
        this.dietLabel = dietLabel
        this.ingredients = ingredients
        this.mealType = mealType
        this.cuisineType = cuisineType
        this.category = category
    }

    fun prepare(cocina:String){

    }


}