package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.RecipesListAdapter
import com.bifrost.cocinarte.entities.*
import com.bifrost.cocinarte.models.main.HomeViewModel
import com.bifrost.cocinarte.models.main.ListIngredientsViewModel
import com.bifrost.cocinarte.models.main.UserProfileViewModel
import kotlinx.coroutines.*
import java.util.*

class HomeFragment : Fragment() {

    private lateinit var v: View

    private lateinit var viewModel: HomeViewModel
    private lateinit var recRecipe: RecyclerView

    private var recipes: MutableList<RecipeHit> = mutableListOf()

    private lateinit var userViewModel : UserProfileViewModel
    private lateinit var recipesListAdapter: RecipesListAdapter
    private lateinit var listRecipesViewModel: ListIngredientsViewModel

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(requireActivity()).get(UserProfileViewModel::class.java)
        //Busco el usuario
        userViewModel.initializeProfile()
        //Inicializo el viewModel de recetas
        listRecipesViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)

        //Busco la receta
        listRecipesViewModel.searchRecipe("pan", true)

        v = inflater.inflate(R.layout.home_fragment, container, false)
        recRecipe = v.findViewById(R.id.recyclerRecommended)
        return v;
    }

    override fun onStart() {
        super.onStart()

        //Busco el user
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

        //Configuro el RecView
        recRecipe.setHasFixedSize(true)
        recRecipe.layoutManager = LinearLayoutManager(context)
        recipesListAdapter = RecipesListAdapter {  x -> onCardItemClick(x)}
        recRecipe.adapter = recipesListAdapter

        //Seteo el observer
        listRecipesViewModel.recipeListForHomeLiveData.observe(viewLifecycleOwner, Observer { result ->
            recipesListAdapter.setData(result)
            recRecipe.adapter = recipesListAdapter
        })

        userViewModel.userLiveData.observe(viewLifecycleOwner, Observer { result ->
            recipesListAdapter.setPrepared(result.preparedRecipe!! as List<RecipeHit>)
            recRecipe.adapter = recipesListAdapter
        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    fun onCardItemClick(pos: Int):Boolean {
        Log.d("RECIPE", recipes.toString())
        var action = HomeFragmentDirections.actionHomeFragmentToRecipeDetailFragment(listRecipesViewModel.recipeListForHome[pos]);

        v.findNavController().navigate(action)
        return true
    }

    fun randomElementFromGivenList() : String {
        val list = listOf(
            "meat",
            "coffee",
            "chicken",
            "carrot",
            "sal",
            "watermelon",
            "water",
            "bread",
            "strawberry",
            "pasta",
            "spicy"
        )
        return list[kotlin.random.Random.nextInt(list.size)]
    }
}