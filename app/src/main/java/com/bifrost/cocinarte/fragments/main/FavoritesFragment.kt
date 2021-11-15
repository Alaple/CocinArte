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
import com.bifrost.cocinarte.models.main.FavoritesViewModel
import com.bifrost.cocinarte.models.main.PreparedViewModel

class FavoritesFragment : Fragment() {

    private lateinit var v: View
    private lateinit var viewModel: FavoritesViewModel

    private lateinit var recFavorites: RecyclerView
    private lateinit var recipesListAdapter: RecipesListAdapter

    private var recipes: MutableList<RecipeHit> = ArrayList()

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.favorites_fragment, container, false)
        recFavorites = v.findViewById(R.id.recyclerFavorites)
        viewModel = ViewModelProvider(requireActivity()).get(FavoritesViewModel::class.java)

        return v
    }

    override fun onStart() {
        super.onStart()

        recFavorites.setHasFixedSize(true)
        recFavorites.layoutManager = LinearLayoutManager(context)

        recipesListAdapter = RecipesListAdapter { x -> onCardItemClick(x) }
        recFavorites.adapter = recipesListAdapter
        viewModel.initializeProfile()

        viewModel.favoritesLiveData.observe(viewLifecycleOwner, Observer { result ->
            recipes = result
            recipesListAdapter.setData(result)
            recFavorites.adapter = recipesListAdapter
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
    }

    fun onItemClick(pos: Int) {

    }

    fun onCardItemClick(int: Int): Boolean {
        var action = FavoritesFragmentDirections.actionFavoritesFragmentToRecipeDetailFragment(recipes[int]);
        v.findNavController().navigate(action)
        return true
    }
}