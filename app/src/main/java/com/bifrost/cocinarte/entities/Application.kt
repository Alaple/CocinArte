package com.bifrost.cocinarte.entities

import android.os.Parcel
import android.os.Parcelable

class Application(
        name: String,
        recepies: MutableList<Recipe>,
        applicationIngredient: MutableList<Ingredient>,
        users: MutableList<User>
    )  {

    var name: String = name
    var recepies: MutableList<Recipe> = recepies
    var applicationIngredient: MutableList<Ingredient> = applicationIngredient
    var users: MutableList<User> = users

    init {
        this.name = name
        this.recepies = recepies
        this.applicationIngredient = applicationIngredient
        this.users = users
    }

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

        var dietLabel = MutableList<DietLabel>(1){
            DietLabel.BALANCED
        }

        var mealType = MutableList<MealType>(1){
            MealType.BREAKFAST
        }

        var cuisineTypes = MutableList<CuisineType>(1){
            CuisineType.ASIAN
        }

        var categories = MutableList<Category>(1){
            Category.NONE
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