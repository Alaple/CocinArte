package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class RecipeDetailViewModel : ViewModel() {

    lateinit var user: User

    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore

    fun prepare(recipeId : String) {
        // TODO Get User Data
        //getUser()

        // TODO Add Recipe in PreparedRecipes
        // Example preparedRecipes.add(Prepared(recipeId))

        // TODO Delete mock User when getUser is implemented
        // Mock user
        var rewards : MutableList<Reward> = ArrayList<Reward>()
        var favourites : MutableList<Favorite> = ArrayList<Favorite>()
        var preparedRecipes : MutableList<Prepared> = ArrayList<Prepared>()
        preparedRecipes.add(Prepared(recipeId))
        var nuevoUsuario = User("Manu",
            "manuel@test.com",
            "test",
            rewards,
            Preference(true, true, true),
            favourites,
            preparedRecipes,
            Category.CELIAC)

        // Update user in Firestore
        db.collection("users").document("BORRAR-MOCK-USER").set(nuevoUsuario)
        // TODO replace above instruction with the following with the corresponding userId
        //db.collection("users").document("userId").set(user)
    }

    private fun getUser() {
        TODO("Not yet implemented")
    }
}