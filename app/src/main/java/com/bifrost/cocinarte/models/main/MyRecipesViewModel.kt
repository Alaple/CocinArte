package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.RecipeHit
import com.bifrost.cocinarte.entities.User
import com.bifrost.cocinarte.entities.UserRecipe
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MyRecipesViewModel : ViewModel() {

    private val db = Firebase.firestore
    private lateinit var auth: FirebaseAuth

    var myRecipesLiveData: MutableLiveData<MutableList<RecipeHit>> = MutableLiveData()

    fun initializeProfile() {
        getUserDB()
    }

    private fun getUserDB() {
        auth = FirebaseAuth.getInstance()
        val authUser = auth.currentUser
        if (authUser != null) {
            getUserFirestore(authUser.email)
        }
    }

    private fun getUserFirestore(email: String?) {
        val tag = "MyRecipesViewModel - getUser"
        val docRef = db.collection("users").document(email!!)
        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    var user = dataSnapshot.toObject<User>()
                    if(user != null) {
                        myRecipesLiveData.value = user.myRecipes
                    }
                } else {
                    Log.d(tag, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(tag, "get failed with ", exception)
            }
    }
}