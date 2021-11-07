package com.bifrost.cocinarte.entities

import android.os.Parcel
import android.os.Parcelable
import android.security.identity.AccessControlProfile

class User(
        name: String,
        email: String,
        password: String
)  {
    var name: String
    var email: String
    var password: String
    var enabled: Boolean
    var level: Int
    var reward: MutableList<Reward>
    //CHECK: Ponerlo en el UserRegister si es necesario
    //var userPrefence: Preference
    var favorite: MutableList<Favorite>
    var preparedRecipe: MutableList<Prepared>
    //CHECK: Ponerlo en el UserRegister si se va a utilizar
    //var profile: Category


    init {
        this.name = name
        this.email = email
        this.password = password
        this.enabled = false
        this.level = 0
        this.reward = mutableListOf()
        //this.userPrefence = userPrefence
        this.favorite = mutableListOf()
        this.preparedRecipe = mutableListOf()
        //this.profile = profile
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