package com.bifrost.cocinarte.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.ButtonListAdapter
import com.bifrost.cocinarte.adapters.RecipesListAdapter
import com.bifrost.cocinarte.models.main.ListIngredientsViewModel
import com.google.android.material.textfield.TextInputEditText

class ListIngredients : Fragment() {

    lateinit var searchBar : TextInputEditText
    lateinit var buttons: RecyclerView
    lateinit var recipesRecView : RecyclerView
    lateinit var searchButton: ImageButton
    private lateinit var recipesListAdapter: RecipesListAdapter
    private lateinit var buttonsViewModel: ListIngredientsViewModel
    private lateinit var filterArgList: MutableList<String>
    lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.list_ingredients_fragment, container, false)
        searchBar = v.findViewById(R.id.searchBar)
        buttons = v.findViewById(R.id.buttonsRecView)
        recipesRecView = v.findViewById(R.id.recipesRecView)
        searchButton = v.findViewById(R.id.searchBarButton)
        buttonsViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)
        filterArgList = ArrayList()

        buttonsViewModel.loadUserProfile()

        searchButton.setOnClickListener() {
            buttonsViewModel.searchRecipe(searchBar.text.toString())
        }

        return v
    }


    override fun onStart() {
        super.onStart()

        var buttonListAdapter = ButtonListAdapter(buttonsViewModel.filters, { i -> onItemsClick(i) })

        //Load RecyclerView for filters
        buttons.setHasFixedSize(true)
        buttons.layoutManager = GridLayoutManager(context, 3)
        buttons.adapter = buttonListAdapter

        //Load RecyclerView for Recipes
        recipesRecView.setHasFixedSize(true)
        recipesRecView.layoutManager = LinearLayoutManager(context)

        //recipesRecView.layoutManager = GridLayoutManager(context, 2) --> GRID
        recipesListAdapter = RecipesListAdapter { x -> onCardItemClick(x) }
        recipesRecView.adapter = recipesListAdapter

        //Observer for listaRecetas
        buttonsViewModel.listaRecetasLiveData.observe(viewLifecycleOwner, Observer { result ->
            recipesListAdapter.setData(result)
            recipesRecView.adapter = recipesListAdapter
        })
    }

    private fun onItemsClick(position: Int) {
        buttonsViewModel.selectFilter(position)
    }

    private fun onCardItemClick(position: Int): Boolean{
        var action = ListIngredientsDirections.actionListIngredients3ToRecipeDetailFragment(buttonsViewModel.listaRecetas[position]);

        var navController = v.findNavController()
        navController.navigate(action)
        return true
    }
}