package com.bifrost.cocinarte.models.main

import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModel
import com.bifrost.cocinarte.entities.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListIngredientsButtonsViewModel: ViewModel() {
    var buttonsList : MutableList<Filter> = ArrayList()
    var listaRecetas: List<RecipeHit>? = ArrayList<RecipeHit>()

    //TEST
    lateinit var button1 : Filter
    lateinit var button2: Filter
    lateinit var button3: Filter
    lateinit var button4: Filter
    lateinit var button5: Filter
    lateinit var button6: Filter


    fun cargarTest(){

        button1 = Filter("Vegetarian", "vegetarian")

        button2 = Filter("Celiac", "celiac")

        button3 = Filter("Amigo", "amigo")

        button4 = Filter("Carnivoro", "carnivoro")

        button5 = Filter("Kosher", "kosher")

        button6 = Filter("Otro", "otro")


        buttonsList.add(button1)
        buttonsList.add(button2)
        buttonsList.add(button3)
        buttonsList.add(button4)
        buttonsList.add(button5)
        buttonsList.add(button6)


    }

    fun searchRecipe(ingredient: String) {
        val appId: String = "9f9ee2ec"
        val apiKey: String = "93ef30f07a4f979e4f5cf2fe6626bce7"
        val type: String = "public"

        val apiCaller: ApiCaller = RestEngine.getRestEngine().create(ApiCaller::class.java)
        val result : Call<EdamamResponse> = apiCaller.listRecipes(type,ingredient, appId, apiKey)

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
                }

            }
        })

       // return listaRecetas
    }




}