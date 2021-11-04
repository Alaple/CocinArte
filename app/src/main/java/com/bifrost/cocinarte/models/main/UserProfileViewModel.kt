package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class UserProfileViewModel : ViewModel() {

    private lateinit var auth: FirebaseAuth

    //lateinit var user : User
    var userLiveData = MutableLiveData<User>()
    lateinit var user : User

    // Firebase
    val db = Firebase.firestore

    fun initializeProfile() {
        auth = FirebaseAuth.getInstance()
        val authUser = auth.currentUser
        if (authUser != null) {
            getUser(authUser.email)
        }
    }

    private fun getUser(email: String?) {
        val docRef = db.collection("users").document(email!!)
        var TAG = "UserProfileViewModel - getUser"
        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    //user = dataSnapshot.toObject<User>()!!
                    //user.value = userTest
                    //Log.d("TESTTTTTTTTTTTTTTTTTTTTT", user.value.toString())
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }
}