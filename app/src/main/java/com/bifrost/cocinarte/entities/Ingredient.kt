package com.bifrost.cocinarte.entities

class Ingredient(id: String, quantity: Float, description: String, foodName: String, food:Food) {
    var quantity: Float = quantity
    var description: String = description
    var foodName: String = foodName
    var food: Food = food
}