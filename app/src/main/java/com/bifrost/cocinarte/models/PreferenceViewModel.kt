package com.bifrost.cocinarte.models

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class PreferenceViewModel : ViewModel() {

    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()

    var userLiveData = MutableLiveData<User>()

    fun updateUserProfile(category: Category) {
        val email = auth.currentUser?.email
        val docRef = db.collection("users").document(email!!)

        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    var user = dataSnapshot.toObject<User>()!!
                    user.profile = category
                    db.collection("users").document(email!!).set(user)
                } else {
                    Log.d("PreferenceViewModel", "No such document.")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("PreferenceViewModel", "Failure.", exception)
            }
    }

    fun loadUserProfile() {
        val email = auth.currentUser?.email
        val docRef = db.collection("users").document(email!!)

        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    val user = dataSnapshot.toObject<User>()!!
                    if (user.profile != null) {
                        userLiveData.value = user
                    }
                } else {
                    Log.d("PreferenceViewModel", "No such document.")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("PreferenceViewModel", "Failure.", exception)
            }
    }
}