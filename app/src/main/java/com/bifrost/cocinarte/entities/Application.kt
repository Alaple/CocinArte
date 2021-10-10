package com.bifrost.cocinarte.entities

import android.os.Parcel
import android.os.Parcelable

class Application(
        name: String,
        recepies: Array<Recipe>,
        applicationIngredient: Array<Ingredient>,
        users: Array<User>
    )  {

    var name: String = name
    var recepies: Array<Recipe> = recepies
    var applicationIngredient: Array<Ingredient> = applicationIngredient
    var users: Array<User> = users

    fun addUser(user: User){
        //TODO
    }

    fun deleteUser(user: User){
        //TODO
    }

    fun updateUser(user: User){
        //TODO
    }

    fun search(recipe: String): Array<Recipe> {
        //TODO

        var dietLabel = Array<DietLabel>(1){
            DietLabel.BALANCED
        }

        var mealType = Array<MealType>(1){
            MealType.BREAKFAST
        }

        var cuisineTypes = Array<CuisineType>(1){
            CuisineType.ASIAN
        }

        var categories = Array<Category>(1){
            Category.CELIAC
        }
        return Array(20) { Recipe(
                "1",
                "1",
                "1",
                1,
                "1",
                0.1F,
                dietLabel,
                this.applicationIngredient,
                mealType,
                cuisineTypes,
                categories
        ) }
    }
}