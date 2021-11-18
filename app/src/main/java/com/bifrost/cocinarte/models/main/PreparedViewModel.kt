package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.RecipeHit
import com.bifrost.cocinarte.entities.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class PreparedViewModel : ViewModel() {

    // Firebase Authentication
    private lateinit var auth: FirebaseAuth
    // Firebase Firestore
    val db = Firebase.firestore

    var userLiveData = MutableLiveData<MutableList<RecipeHit>>()

    fun initializeProfile() {
        getUserDB()
    }

    // === GET USER ===

    // Get User from Authentication
    private fun getUserDB() {
        auth = FirebaseAuth.getInstance()
        val authUser = auth.currentUser
        if (authUser != null) {
            // Get User from Firestore
            getUserFirestore(authUser.email)
        }
    }

    // Get User from Firestore
    private fun getUserFirestore(email: String?) {
        val docRef = db.collection("users").document(email!!)
        var TAG = "UserProfileViewModel - getUser"
        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    // DO SOMETHING
                    // Parse the data and observe it
                    var user = dataSnapshot.toObject<User>()
                    if (user != null) {
                        userLiveData.value = user.preparedRecipe
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }


}