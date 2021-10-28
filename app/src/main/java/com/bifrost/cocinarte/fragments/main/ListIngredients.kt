package com.bifrost.cocinarte.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.ButtonListAdapter
import com.bifrost.cocinarte.entities.ApiCaller
import com.bifrost.cocinarte.entities.RecipesDataCollectionItem
import com.bifrost.cocinarte.entities.RestEngine
import com.bifrost.cocinarte.models.main.ListIngredientsButtonsViewModel
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListIngredients : Fragment() {


    lateinit var searchBar : TextInputEditText
    lateinit var buttons: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var buttonListAdapter: ButtonListAdapter
    private lateinit var buttonsViewModel: ListIngredientsButtonsViewModel
    lateinit var v: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =inflater.inflate(R.layout.list_ingredients_fragment, container, false)
        searchBar = v.findViewById(R.id.searchBar)
        buttons = v.findViewById(R.id.buttonsRecView)

        buttonsViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsButtonsViewModel::class.java)

        buttonsViewModel.cargarTest()

        //Loadlist Test

        loadList()

        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()

        buttons.setHasFixedSize(true)
        //linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager = GridLayoutManager(context, 3)
        buttons.layoutManager = linearLayoutManager

        buttonListAdapter = ButtonListAdapter(buttonsViewModel.buttonsList)

        buttons.adapter = buttonListAdapter

    }

    private fun loadList(){

        val appId: String = "9f9ee2ec"
        val apiKey: String = "93ef30f07a4f979e4f5cf2fe6626bce7"
        val userService: ApiCaller = RestEngine.getRestEngine().create(ApiCaller::class.java)
        val result : Call<List<RecipesDataCollectionItem>> = userService.listRecipes("public","Chicken", appId, apiKey)

        result.enqueue(object: Callback<List<RecipesDataCollectionItem>> {
            override fun onFailure(call: Call<List<RecipesDataCollectionItem>>, t: Throwable) {
                Log.d("Response", "Error")
            }

            override fun onResponse(call: Call<List<RecipesDataCollectionItem>>, response: Response<List<RecipesDataCollectionItem>>) {
                Log.d("Response","OK - CODE: " + response.code() +"Message: "+ response.message())
                Log.d("response", response.body().toString())
            }
        })
    }


}