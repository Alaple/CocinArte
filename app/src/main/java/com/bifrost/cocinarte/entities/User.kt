package com.bifrost.cocinarte.entities

class User {
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var enabled: Boolean? = true
    var level: Int? = 0
    var reward: MutableList<Reward>? = mutableListOf()
    var userPrefence: Preference? = Preference()
    var favorite: MutableList<RecipeHit>? = mutableListOf()
    var preparedRecipe: MutableList<RecipeHit>? = mutableListOf()
    var profile: Category = Category.NONE

    constructor() {}

    constructor(name: String,
                email: String,
                password: String,
                enabled: Boolean,
                level: Int,
                reward: MutableList<Reward>,
                userPrefence: Preference,
                favorite: MutableList<RecipeHit>,
                preparedRecipe: MutableList<RecipeHit>,
                profile: Category) {
        this.name = name
        this.email = email
        this.password = password
        this.enabled = false
        this.level = 0
        this.reward = reward
        this.userPrefence = userPrefence
        this.favorite = favorite
        this.preparedRecipe = preparedRecipe
        this.profile = profile
    }

    constructor(
        name: String,
        email: String,
        password: String,
    ){
        this.name = name
        this.email = email
        this.password = password
        this.enabled = true
        this.level = 0
        this.reward = mutableListOf()
        this.userPrefence = Preference()
        this.favorite = mutableListOf()
        this.preparedRecipe = mutableListOf()
        this.profile = Category.NONE
    }
}