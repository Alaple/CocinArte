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

class ListIngredientsViewModel: ViewModel {
    private val db = Firebase.firestore
    private val auth = FirebaseAuth.getInstance()

    var listaRecetas: MutableList<RecipeHit> = mutableListOf()
    var listaRecetasLiveData: MutableLiveData<MutableList<RecipeHit>> = MutableLiveData()
    var recipeListForHome: MutableList<RecipeHit> = mutableListOf()
    var recipeListForHomeLiveData: MutableLiveData<MutableList<RecipeHit>> = MutableLiveData()

    val filters: ArrayList<Filter> = ArrayList()
    val selectedFilters: ArrayList<String> = ArrayList()

    var userLiveData = MutableLiveData<User>()
    var defaultProfile = Category.NONE

    constructor() {
        filters.add(Filter("Vegetarian", "vegetarian", Category.VEGETARIAN))
        filters.add(Filter("Gluten-Free", "gluten-free", Category.GLUTEN_FREE))
        filters.add(Filter("Keto", "keto-friendly", Category.KETO))
        filters.add(Filter("Low Sugar", "low-sugar", Category.LOW_SUGAR))
        filters.add(Filter("Kosher", "kosher", Category.KOSHER))
        filters.add(Filter("Vegan", "vegan", Category.VEGAN))
    }

    fun selectFilter(i: Int) {
        val filter = filters[i].filterName
        if (selectedFilters.contains(filter)) {
            selectedFilters.remove(filter)
        } else {
            selectedFilters.add(filter)
        }
    }

    fun searchRecipe(ingredient: String, fromHome: Boolean?) {
        val appId = "9f9ee2ec"
        val apiKey = "93ef30f07a4f979e4f5cf2fe6626bce7"
        val type = "public"
        var sfilter = selectedFilters

        if (fromHome == true) {
            sfilter = arrayListOf()
        }

        val apiCaller: ApiCaller = RestEngine.getRestEngine().create(ApiCaller::class.java)
        val result : Call<EdamamResponse> = apiCaller.listRecipes(type, ingredient, appId, apiKey, sfilter)

        result.enqueue(object: Callback<EdamamResponse> {
            override fun onFailure(call: Call<EdamamResponse>, t: Throwable) {
                Log.d("Response", "Error")
                Log.d("Error: ", t.message.toString())
            }

            override fun onResponse(call: Call<EdamamResponse>, response: Response<EdamamResponse>) {
                Log.d("Response","OK - CODE: " + response.code() + " Message: " + response.message())
                if(!response.isSuccessful){
                    Log.d("Error", "No response")
                    return
                }

                val apiResponse = response.body()
                if (apiResponse != null) {
                    if (fromHome == true){
                        recipeListForHome = apiResponse.getHits()
                        recipeListForHomeLiveData.value = recipeListForHome
                    } else {
                        listaRecetas = apiResponse.getHits()
                        listaRecetasLiveData.value = listaRecetas
                    }
                }
            }
        })
    }

    fun loadUserProfile() {
        val email = auth.currentUser?.email
        val docRef = db.collection("users").document(email!!)

        cleanFilters()

        docRef.get()
            .addOnSuccessListener { dataSnapshot ->
                if (dataSnapshot != null) {
                    val user = dataSnapshot.toObject<User>()!!
                    if (user.profile != null) {
                        userLiveData.value = user
                        defaultProfile = user.profile
                        loadDefaultFilters()
                    }
                } else {
                    Log.d("PreferenceViewModel", "No such document.")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("PreferenceViewModel", "Failure.", exception)
            }
    }

    private fun cleanFilters() {
        selectedFilters.clear()
        filters.forEach { it.filterDefault = false }
    }

    private fun loadDefaultFilters() {
        if (defaultProfile != Category.NONE) {
            filters.forEach {
                if (it.category == defaultProfile) {
                    it.filterDefault = true
                    selectedFilters.add(it.filterName)
                }
            }
        }
    }
}