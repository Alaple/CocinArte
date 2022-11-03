package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class NewRecipeModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore

    fun createNewRecipe(name: String, description: String){
        auth.createUserWithEmailAndPassword(
            name,
            description
        ).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                Log.d("User Recipe: ","Auth User Recipe Created")
            } else {

            }
        }
    }

    fun createDbNewRecipe(name: String, description: String) {
        val userRecipe = UserRecipe(name,
            description
        )
        uploadRecipe(userRecipe)
    }

    fun uploadRecipe(userRecipe: UserRecipe){
        db.collection("userRecipe").document(userRecipe.name!!).set(userRecipe)
    }
}