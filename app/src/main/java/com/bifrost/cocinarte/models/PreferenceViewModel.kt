package com.bifrost.cocinarte.models

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class PreferenceViewModel : ViewModel() {

    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()

    fun updateUserProfile(category: Category) {
        // var profile: Category = Category.NONE

        val email = auth.currentUser?.email

        val user = getUser(email)
        user.profile = category

        db.collection("users").document(email!!).set(user)
    }

    private fun getUser(email: String?): User {
        val docRef = db.collection("users").document(email!!)
        var user = User()

        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    user = dataSnapshot.toObject<User>()!!
                } else {
                    Log.d("PreferenceViewModel", "No such document.")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("PreferenceViewModel", "Failure.", exception)
            }

        return user;
    }
}