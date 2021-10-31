package com.bifrost.cocinarte.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.ButtonListAdapter
import com.bifrost.cocinarte.adapters.RecipesListAdapter
import com.bifrost.cocinarte.entities.RecipesDataCollectionItem
import com.bifrost.cocinarte.models.main.ListIngredientsButtonsViewModel
import com.google.android.material.textfield.TextInputEditText

class ListIngredients : Fragment() {


    lateinit var searchBar : TextInputEditText
    lateinit var buttons: RecyclerView
    lateinit var recipesRecView : RecyclerView
    lateinit var searchButton: ImageButton
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var buttonListAdapter: ButtonListAdapter
    private lateinit var recipesListAdapter: RecipesListAdapter
    private lateinit var buttonsViewModel: ListIngredientsButtonsViewModel
    private lateinit var filterArgList: MutableList<String>
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
        recipesRecView = v.findViewById(R.id.recipesRecView)
        searchButton = v.findViewById(R.id.searchBarButton)
        buttonsViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsButtonsViewModel::class.java)
        filterArgList = ArrayList()
        buttonsViewModel.cargarTest()



        searchButton.setOnClickListener(){
            Log.d("Filters:", filterArgList.toString())
            buttonsViewModel.searchRecipe(searchBar.text.toString())
        }

        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()

        buttons.setHasFixedSize(true)
        //linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager = GridLayoutManager(context, 3)
        buttons.layoutManager = linearLayoutManager

        buttonListAdapter = ButtonListAdapter(buttonsViewModel.buttonsList){ x,y -> onItemsClick(x,y) }

        buttons.adapter = buttonListAdapter

        recipesRecView.setHasFixedSize(true)
        recipesRecView.layoutManager = LinearLayoutManager(context)
        recipesListAdapter = RecipesListAdapter(buttonsViewModel.listaRecetas as MutableList<RecipesDataCollectionItem>)
        recipesRecView.adapter = recipesListAdapter

    }

    private fun onItemsClick(position: Int, y: String) {
        filterArgList.add(buttonsViewModel.buttonsList[position].filterName)
    }


}