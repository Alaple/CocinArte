package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
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
import com.bifrost.cocinarte.entities.RecipeHit
import com.bifrost.cocinarte.entities.UserRecipe
import com.bifrost.cocinarte.models.main.MyRecipesViewModel

class MyRecipesFragment : Fragment() {

    private lateinit var v: View
    private lateinit var viewModel: MyRecipesViewModel

    private lateinit var recMyRecipes: RecyclerView
    private lateinit var recipesListAdapter: RecipesListAdapter

    private var recipes: MutableList<RecipeHit> = ArrayList()

    companion object {
        fun newInstance() = MyRecipesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.my_recipe_fragment, container, false)
        recMyRecipes = v.findViewById(R.id.recyclerMyRecipes)
        viewModel = ViewModelProvider(requireActivity()).get(MyRecipesViewModel::class.java)

        return v
    }

    override fun onStart() {
        super.onStart()

        recMyRecipes.setHasFixedSize(true)
        recMyRecipes.layoutManager = LinearLayoutManager(context)

        recipesListAdapter = RecipesListAdapter { x -> onCardItemClick(x) }
        recMyRecipes.adapter = recipesListAdapter
        viewModel.initializeProfile()

        viewModel.myRecipesLiveData.observe(viewLifecycleOwner, Observer { result ->
            if (result != null) {
                recipes = result
                recipesListAdapter.setData(result)
                recMyRecipes.adapter = recipesListAdapter
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyRecipesViewModel::class.java)
    }

    fun onCardItemClick(int: Int): Boolean {
       var action = MyRecipesFragmentDirections.actionMyRecipesFragmentToRecipeDetailFragment(recipes[int]);
        v.findNavController().navigate(action)
        return true
    }
}