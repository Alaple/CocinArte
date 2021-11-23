package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListIngredientsViewModel: ViewModel() {

    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()

    var buttonsList : MutableList<Filter> = ArrayList()
    var listaRecetas: MutableList<RecipeHit> = mutableListOf()
    var listaRecetasLiveData: MutableLiveData<MutableList<RecipeHit>> = MutableLiveData()

    fun loadButtons() {

        val email = auth.currentUser?.email
        val user = getUser(email)

        var category = user.profile
        Log.d("Category", "user.profile :: " + user.profile + (Category.VEGGIE == category))

        // Category.IRON_RICH
        // Category.CELIAC
        // Category.KOSHER == category
        // Category.VEGGIE == category
        // Category.LOW_CALORIES

        var button1 = Filter("Vegetarian", "vegetarian", Category.VEGGIE == category)
        var button2 = Filter("Gluten-Free", "gluten-free")
        var button3 = Filter("Keto", "keto-friendly")
        var button4 = Filter("Low Sugar", "low-sugar")
        var button5 = Filter("Kosher", "kosher", Category.KOSHER == category)
        var button6 = Filter("Vegan", "vegan")

        buttonsList.add(button1)
        buttonsList.add(button2)
        buttonsList.add(button3)
        buttonsList.add(button4)
        buttonsList.add(button5)
        buttonsList.add(button6)
    }

    fun searchRecipe(ingredient: String, filterList: ArrayList<String>) {
        val appId: String = "9f9ee2ec"
        val apiKey: String = "93ef30f07a4f979e4f5cf2fe6626bce7"
        val type: String = "public"

        val apiCaller: ApiCaller = RestEngine.getRestEngine().create(ApiCaller::class.java)
        val result : Call<EdamamResponse> = apiCaller.listRecipes(type,ingredient, appId, apiKey, filterList)

       result.enqueue(object: Callback<EdamamResponse> {
            override fun onFailure(call: Call<EdamamResponse>, t: Throwable) {
                Log.d("Response", "Error")
                Log.d("Error: ", t.message.toString())
            }

            override fun onResponse(call: Call<EdamamResponse>, response: Response<EdamamResponse>) {
                Log.d("Response","OK - CODE: " + response.code() +" Message: "+ response.message())
                if(!response.isSuccessful){
                    Log.d("Error", "No response")
                    return
                }
                var apiResponse = response.body()
                if (apiResponse != null) {
                    listaRecetas = apiResponse.getHits()
                    listaRecetasLiveData.value = listaRecetas
                }

            }
        })

       // return listaRecetas
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