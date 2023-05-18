package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlin.math.pow
import kotlin.math.round
import kotlin.random.Random

class NewRecipeModel : ViewModel() {
    lateinit var user: User
    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()

    fun createDbNewRecipe(calories: Number, description: String, image_url: String, label: String, time: Number, url: String) {
        var userRecipe = RecipeHit(getRandomUri(),
            label,
            image_url,
            url,
            mutableListOf("KOSHER"),
            description,
            mutableListOf("pan", "queso"),
            calories.toFloat(),
            time.toInt(),
            getRandom()
        )
        uploadRecipe(userRecipe)
    }

    private fun getRandom(): Int {
        val random = Random.Default
        val randomNumber = random.nextInt(10000) // Rango numérico, ajusta el valor según tus necesidades
        return randomNumber
    }

    private fun getRandomUri(): String {
        val random = Random.Default
        val bytes = ByteArray(16) // Se necesitan 16 bytes para representar 32 caracteres hexadecimales
        random.nextBytes(bytes)
        val randomString = bytes.joinToString("") { "%02x".format(it) } // Convertir a cadena hexadecimal
        return randomString
    }


    fun uploadRecipe(userRecipe: RecipeHit) {
        // Subir la receta al documento correspondiente
            db.collection("userRecipe").document(userRecipe.label!!).set(userRecipe)

        // Verifico que exista el usuario en caso de no obtenerlo
        if (user == null){
            getUser()
        }

        // Guardo la receta creada en el usuario que la creo
        user.myRecipes?.add(userRecipe)
        user.email?.let { db.collection("users").document(it).set(user) }
    }

    private fun getUser() {
        val userEmail = auth.currentUser?.email
        if(userEmail!=null){
            getUserFirestore(userEmail)
        }
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
