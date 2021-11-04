package com.bifrost.cocinarte.entities

class User {
    var name: String? = null
    var email: String? = null
    var password: String? = null
    var enabled: Boolean? = true
    var level: Int? = 0
    var reward: MutableList<Reward>? = mutableListOf()
    var userPrefence: Preference? = Preference()
    var favorite: MutableList<Favorite>? = mutableListOf()
    var preparedRecipe: MutableList<Prepared>? = mutableListOf()
    var profile: Category? = null

    constructor() {}

    constructor(name: String,
                email: String,
                password: String,
                enabled: Boolean,
                level: Int,
                reward: MutableList<Reward>,
                userPrefence: Preference,
                favorite: MutableList<Favorite>,
                preparedRecipe: MutableList<Prepared>,
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
/*
    fun deleteUser(user: User){
        //TODO
    }

    fun updateUser(user:User){
        //TODO
    }

    fun logout(){
        //TODO
    }

    fun login(email: String, password: String){
        //TODO
    }

    fun addToFavorite(favorite: Favorite){
        //TODO
    }

    fun removeFromFavotire(favorite: String){
        //TODO
    }

    fun addToPrep(prepared: Prepared){
        //TODO
    }

    fun removeFromPrep(prepared: String){
        //TODO
    }

    fun createAccount(email: String, password: String){
        //TODO
    }*/
}