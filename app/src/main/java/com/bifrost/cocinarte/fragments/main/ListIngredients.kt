package com.bifrost.cocinarte.fragments.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.ButtonListAdapter
import com.bifrost.cocinarte.adapters.RecipesListAdapter
import com.bifrost.cocinarte.entities.RecipeHit
import com.bifrost.cocinarte.entities.User
import com.bifrost.cocinarte.models.main.ListIngredientsViewModel
import com.bifrost.cocinarte.models.main.UserProfileViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.*

class ListIngredients : Fragment() {


    lateinit var searchBar : TextInputEditText
    lateinit var buttons: RecyclerView
    lateinit var recipesRecView : RecyclerView
    lateinit var searchButton: ImageButton
    lateinit var userViewModel : UserProfileViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var buttonListAdapter: ButtonListAdapter
    private lateinit var recipesListAdapter: RecipesListAdapter
    private lateinit var buttonsViewModel: ListIngredientsViewModel
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
        buttonsViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserProfileViewModel::class.java)
        filterArgList = ArrayList()


        //Busco el usuario
        userViewModel.initializeProfile()
        //Validate that buttons list only loads once
        if (buttonsViewModel.buttonsList.size == 0){
            buttonsViewModel.loadButtons()
        }

        searchButton.setOnClickListener(){
            buttonsViewModel.searchRecipe(searchBar.text.toString(), filterArgList as ArrayList<String>, false)
        }

        // Inflate the layout for this fragment
        return v
    }


    override fun onStart() {
        super.onStart()

        //Get User Recipes List (Coroutine)
        val job = Job()
        val scope = CoroutineScope(Dispatchers.Default + job)
        scope.launch {
            val getUser = async{
                try{
                    userViewModel.initializeProfile()
                }catch (e: Exception){
                    e.message?.let { Log.d("Error", it) }
                }
            }

            getUser.await()




        }

        //Load RecyclerView for filters
        buttons.setHasFixedSize(true)
        linearLayoutManager = GridLayoutManager(context, 3)
        buttons.layoutManager = linearLayoutManager
        buttonListAdapter = ButtonListAdapter(buttonsViewModel.buttonsList) { i -> onItemsClick(i) }
        buttons.adapter = buttonListAdapter

        //Load RecyclerView for Recipes
        recipesRecView.setHasFixedSize(true)
        recipesRecView.layoutManager = LinearLayoutManager(context)
        recipesListAdapter = RecipesListAdapter{ x -> onCardItemClick(x)}
        recipesRecView.adapter = recipesListAdapter

        //Observer for listaRecetas
        buttonsViewModel.listaRecetasLiveData.observe(viewLifecycleOwner, Observer { result ->
            recipesListAdapter.setData(result)
            recipesRecView.adapter = recipesListAdapter
    })

        userViewModel.userLiveData.observe(viewLifecycleOwner, Observer { result ->
            recipesListAdapter.setPrepared(result.preparedRecipe!! as List<RecipeHit>)
            recipesRecView.adapter = recipesListAdapter
        })



    }

    private fun onItemsClick(position: Int) {
        var myFilter = buttonsViewModel.buttonsList[position].filterName
        if(filterArgList.contains(myFilter)){
            var value = filterArgList.indexOf(myFilter)
            filterArgList.removeAt(value)
        }else{
            filterArgList.add(myFilter)
        }

    }

    private fun onCardItemClick(position: Int): Boolean{
        var action = ListIngredientsDirections.actionListIngredients3ToRecipeDetailFragment(buttonsViewModel.listaRecetas[position]);

        var navController = v.findNavController()
        navController.navigate(action)
        return true
    }


}