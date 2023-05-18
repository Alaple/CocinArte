package com.bifrost.cocinarte.entities
import kotlin.random.Random
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class UserRecipe {
    var id: Number? = 0
    var calories: Number? = 0
    var description: String? = null
    var image_url: String? = null
    var ingredients: MutableList<String>? = null
    var label: String? = null
    var time: Number? = 0
    var uri: String? = null
    var url: String? = null
    var yield: Number? = 0

    constructor(calories: Number, description: String, image_url: String, label: String, time: Number, url: String) {
        val db = Firebase.firestore
        val collectionRef = db.collection("recipes")
        val idQuery = collectionRef.orderBy("id", Query.Direction.DESCENDING).limit(1)
        val yieldQuery = collectionRef.orderBy("yield", Query.Direction.DESCENDING).limit(1)

        idQuery.get().addOnSuccessListener { documents ->
            if (!documents.isEmpty) {
                val lastId = documents.documents[0].getLong("id")!!
                this.id = lastId + 1
            } else {
                this.id = 0
            }
        }

        yieldQuery.get().addOnSuccessListener { documents ->
            if (!documents.isEmpty) {
                val lastYield = documents.documents[0].getLong("yield")!!
                this.yield = lastYield + 1
            } else {
                this.yield = 0
            }
        }

        this.calories = calories
        this.description = description
        this.image_url = image_url
        this.ingredients = mutableListOf<String>("pan","queso")
        this.label = label
        this.time = time
        this.uri = this.getRandom()
        this.url = url
    }

        private fun getRandom(): String {
        val random = Random.Default
        val bytes = ByteArray(16) // Se necesitan 16 bytes para representar 32 caracteres hexadecimales
        random.nextBytes(bytes)
        val randomString = bytes.joinToString("") { "%02x".format(it) } // Convertir a cadena hexadecimal
        return randomString
    }
}
