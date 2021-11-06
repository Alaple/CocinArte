package com.bifrost.cocinarte.models.main

import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.bifrost.cocinarte.entities.RecipeHit
import kotlin.math.pow
import kotlin.math.round

class RecipeDetailViewModel : ViewModel() {

    lateinit var user: User

    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore

    fun prepare(recipeId : String?) {
        // TODO Get User Data
        //getUser()

        // TODO Add Recipe in PreparedRecipes
        // Example preparedRecipes.add(Prepared(recipeId))

        // TODO Delete mock User when getUser is implemented
        // Mock user
        var rewards : MutableList<Reward> = ArrayList<Reward>()
        var favourites : MutableList<Favorite> = ArrayList<Favorite>()
        var preparedRecipes : MutableList<Prepared> = ArrayList<Prepared>()
        recipeId?.let { Prepared(it) }?.let { preparedRecipes.add(it) }
        var nuevoUsuario = User("Manu",
            "manuel@test.com",
            "test",
            true,
            4,
            rewards,
            Preference(true, true, true),
            favourites,
            preparedRecipes,
            Category.CELIAC)

        // Update user in Firestore
        //db.collection("users").document("BORRAR-MOCK-USER").set(nuevoUsuario)
        this.userExperience(nuevoUsuario);
    }

    private fun getUser() {
        TODO("Not yet implemented")
    }

    private fun userExperience(user : User){
        var totalExperience = (user.preparedRecipe!!.size*3) + 3
        var newLevel = user.level!! + 1
        var forNextLevel = round(0.04 * (newLevel.toDouble().pow(3)) + 0.8 * (newLevel.toDouble().pow(2)) + 2 * newLevel)

        if(totalExperience>=forNextLevel){
            user.level = newLevel;
        }
        db.collection("users").document(user.email!!).set(user)
    }
}