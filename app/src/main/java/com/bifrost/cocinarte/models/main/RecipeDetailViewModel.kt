package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.bifrost.cocinarte.entities.RecipeHit
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.toObject
import kotlin.math.pow
import kotlin.math.round

class RecipeDetailViewModel : ViewModel() {

    lateinit var user: User

    // Access a Cloud Firestore instance from your Activity
    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()
    fun prepare(recipe: RecipeHit) {

        if (user != null){
            user.preparedRecipe?.add(recipe)
            user.email?.let { db.collection("users").document(it).set(user) }
            this.userExperience(user)
        }else{
            Log.d("RecipeDetail","No user found")

        }

    }

    fun getUser() {
        val userEmail = auth.currentUser?.email
        getUserFirestore(userEmail)
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

    private fun getUserFirestore(email: String?) {
        val docRef = db.collection("users").document(email!!)
        var TAG = "UserProfileViewModel - getUser"
        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    // DO SOMETHING
                    // Parse the data and observe it
                    user = dataSnapshot.toObject<User>()!!
                    Log.d("GetUser", user.toString())
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}