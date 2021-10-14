package com.bifrost.cocinarte.entities

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

    var title : String = title
    var description: String = description
    var yield: Int = `yield`
    var image_url: String = image_url
    var calories: Float = calories
    var dietLabel: MutableList<DietLabel> = dietLabel
    var ingredients: MutableList<Ingredient> = ingredients
    var mealType: MutableList<MealType> = mealType
    var cuisineType: MutableList<CuisineType> = cuisineType
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