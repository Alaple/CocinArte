package com.bifrost.cocinarte.models.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterViewModel : ViewModel() {
    private var auth: FirebaseAuth = FirebaseAuth.getInstance()
    val db = Firebase.firestore

    fun createAuthUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(
            email,
            password
        ).addOnCompleteListener() { task ->
            if (task.isSuccessful) {
                Log.d("User: ","Auth User Created")
            } else {

            }
        }
    }

    fun createDbUser(name: String, email: String, password: String) {
        var reward : MutableList<Reward> = mutableListOf()
        var preference : Preference = Preference()
        var favorite: MutableList<RecipeHit> = mutableListOf()
        var prepared: MutableList<RecipeHit> = mutableListOf()
        var profile: Category = Category.NONE
        val user = User(name,
            email,
            password,
            true,
            0,
            reward,
            preference,
            favorite,
            prepared,
            profile
        )
        uploadUser(user)
    }

    fun uploadUser(user: User){
        db.collection("users").document(user.email!!).set(user)
    }
}