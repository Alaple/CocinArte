package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapter.RecipeAdapter
import com.bifrost.cocinarte.entities.*
import com.bifrost.cocinarte.models.main.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var v: View

    private lateinit var viewModel: HomeViewModel
    private lateinit var recRecipe: RecyclerView

    private var recipes: MutableList<RecipeHit> = mutableListOf()

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.home_fragment, container, false)
        recRecipe = v.findViewById(R.id.recyclerRecommended)
        return v;
    }

    override fun onStart() {
        super.onStart()

        val appId: String = "9f9ee2ec"
        val apiKey: String = "93ef30f07a4f979e4f5cf2fe6626bce7"
        val type: String = "public"
        var listaRecetas: MutableList<RecipeHit>? = ArrayList<RecipeHit>()

        val apiCaller: ApiCaller = RestEngine.getRestEngine().create(ApiCaller::class.java)

        val result : Call<EdamamResponse> = apiCaller.listRecipes(type,"sal", appId, apiKey, ArrayList<String>())


        if (recipes.size==0){
            Log.d("IF","ENTRE AL IF")
            result.enqueue(object: Callback<EdamamResponse> {
                override fun onFailure(call: Call<EdamamResponse>, t: Throwable) {
                    Log.d("Response", "Error")
                    Log.d("Error: ", t.message.toString())
                }

                override fun onResponse(call: Call<EdamamResponse>, response: Response<EdamamResponse>) {
                    if(!response.isSuccessful){
                        Log.d("Error", "No response")
                        return
                    }
                    var apiResponse = response.body()
                    if (apiResponse != null) {
                        recipes = apiResponse.hitList
                    }

                    recRecipe.setHasFixedSize(true)
                    recRecipe.layoutManager = LinearLayoutManager(context)
                    recRecipe.adapter = RecipeAdapter(recipes) { index ->
                        onItemClick(index)
                    }
                }
            })
        }else{
            Log.d("IF","NO ENTRE AL IF")
            recRecipe.setHasFixedSize(true)
            recRecipe.layoutManager = LinearLayoutManager(context)
            recRecipe.adapter = RecipeAdapter(recipes) { index ->
                onItemClick(index)
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    fun onItemClick(pos: Int) {
        Log.d("RECIPE", recipes.toString())
        var action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(recipes[pos]);

        v.findNavController().navigate(action)
    }
}