package com.bifrost.cocinarte.entities

class Recipe (
    id : String,
    title : String,
    description: String,
    yield: Int,
    image_url: String,
    calories: Float,
    dietLabel: Array<DietLabel>,
    ingredients: Array<Ingredient>,
    mealType: Array<MealType>,
    cuisineType: Array<CuisineType>,
    category: Array<Category>

) {

    var title : String = title
    var description: String = description
    var yield: Int = `yield`
    var image_url: String = image_url
    var calories: Float = calories
    var dietLabel: Array<DietLabel> = dietLabel
    var ingredients: Array<Ingredient> = ingredients
    var mealType: Array<MealType> = mealType
    var cuisineType: Array<CuisineType> = cuisineType
    var category: Array<Category> = category

    fun prepare(cocina:String){

    }
}