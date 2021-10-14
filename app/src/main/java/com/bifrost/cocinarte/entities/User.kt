package com.bifrost.cocinarte.entities

import android.os.Parcel
import android.os.Parcelable
import android.security.identity.AccessControlProfile

class User(
        name: String,
        email: String,
        password: String,
        reward: MutableList<Reward>,
        userPrefence: Preference,
        token: AccessToken,
        favorite: MutableList<Favorite>,
        preparedRecipe: MutableList<Prepared>,
        profile: Category
)  {
    var name: String
    var email: String
    var password: String
    var enabled: Boolean
    var level: Int
    var reward: MutableList<Reward>
    var userPrefence: Preference
    var token: AccessToken
    var favorite: MutableList<Favorite>
    var preparedRecipe: MutableList<Prepared>
    var profile: Category


    init {
        this.name = name
        this.email = email
        this.password = password
        this.enabled = false
        this.level = 0
        this.reward = reward
        this.userPrefence = userPrefence
        this.token = token
        this.favorite = favorite
        this.preparedRecipe = preparedRecipe
        this.profile = profile
    }


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
    }
}