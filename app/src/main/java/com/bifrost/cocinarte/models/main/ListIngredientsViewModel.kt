package com.bifrost.cocinarte.models.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListIngredientsViewModel: ViewModel() {
    var buttonsList : MutableList<Filter> = ArrayList()
    var listaRecetas: MutableList<RecipeHit> = mutableListOf()
    var listaRecetasLiveData: MutableLiveData<MutableList<RecipeHit>> = MutableLiveData()
    var recipeListForHome: MutableList<RecipeHit> = mutableListOf()
    var recipeListForHomeLiveData: MutableLiveData<MutableList<RecipeHit>> = MutableLiveData()




    fun loadButtons(){

        var button1 = Filter("Vegetarian", "vegetarian")
        var button2 = Filter("Gluten-Free", "gluten-free")
        var button3 = Filter("Keto", "keto-friendly")
        var button4 = Filter("Low Sugar", "low-sugar")
        var button5 = Filter("Kosher", "kosher")
        var button6 = Filter("Vegan", "vegan")

        buttonsList.add(button1)
        buttonsList.add(button2)
        buttonsList.add(button3)
        buttonsList.add(button4)
        buttonsList.add(button5)
        buttonsList.add(button6)


    }

    fun searchRecipe(ingredient: String, filterList: ArrayList<String>, fromHome: Boolean?) {
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
                    if (fromHome == true){
                        recipeListForHome = apiResponse.getHits()
                        recipeListForHomeLiveData.value = recipeListForHome
                    }else{
                        listaRecetas = apiResponse.getHits()
                        listaRecetasLiveData.value = listaRecetas
                    }

                }

            }
        })

    }




}