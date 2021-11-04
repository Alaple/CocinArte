package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapter.RecipeAdapter
import com.bifrost.cocinarte.entities.*
import com.bifrost.cocinarte.models.main.PreparedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreparedFragment : Fragment() {

    private lateinit var v: View

    private lateinit var viewModel: PreparedViewModel
    private lateinit var recPrepared: RecyclerView

    private var recipes: ArrayList<Recipe> = ArrayList<Recipe>()

    companion object {
        fun newInstance() = PreparedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.prepared_fragment, container, false)
        recPrepared = v.findViewById(R.id.recyclerPrepared)
        return v
    }

    override fun onStart(){
        super.onStart()

        val appId: String = "9f9ee2ec"
        val apiKey: String = "93ef30f07a4f979e4f5cf2fe6626bce7"
        val type: String = "public"
        var listaPreparados: MutableList<RecipeHit>? = ArrayList<RecipeHit>()

        val apiCaller: ApiCaller = RestEngine.getRestEngine().create(ApiCaller::class.java)
        val result : Call<EdamamResponse> = apiCaller.listRecipes(type,"pepper", appId, apiKey, ArrayList<String>())

        result.enqueue(object: Callback<EdamamResponse> {
            override fun onFailure(call: Call<EdamamResponse>, t: Throwable) {
                Log.d("Response", "Error")
                Log.d("Error: ", t.message.toString())
            }

            override fun onResponse(call: Call<EdamamResponse>, response: Response<EdamamResponse>) {
                Log.d("Response","OK - CODE: " + response.code() +" Message: "+ response.message())
                Log.d("Response",response.body().toString())
                if(!response.isSuccessful){
                    Log.d("Error", "No response")
                    return
                }
                var apiResponse = response.body()
                if (apiResponse != null) {
                    listaPreparados = apiResponse.hitList
                }

                for (i in listaPreparados!!){
                    Log.d("Response Nombre", i.label!!)
                    Log.d("Response Imagen", i.image_url!!)
                    recipes.add(Recipe(i.uri!!, i.label!!, "", 1, i.image_url!!, 1.1F, ArrayList<DietLabel>(), ArrayList<Ingredient>(), ArrayList<MealType>(), ArrayList<CuisineType>(), ArrayList<Category>()))
                }

                recPrepared.setHasFixedSize(true)
                recPrepared.layoutManager = LinearLayoutManager(context)
                recPrepared.adapter = RecipeAdapter(recipes) { index ->
                    onItemClick(index)
                }
            }
        })


    }

    fun onItemClick(pos: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PreparedViewModel::class.java)
        // TODO: Use the ViewModel
    }

}