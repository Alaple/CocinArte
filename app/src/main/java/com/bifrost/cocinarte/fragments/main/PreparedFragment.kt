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
import com.bifrost.cocinarte.adapter.RecipeAdapter
import com.bifrost.cocinarte.adapters.RecipesListAdapter
import com.bifrost.cocinarte.entities.*
import com.bifrost.cocinarte.models.main.PreparedViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PreparedFragment : Fragment() {

    private lateinit var v: View

    private lateinit var viewModel: PreparedViewModel
    private lateinit var recPrepared: RecyclerView
    private lateinit var recipesListAdapter: RecipesListAdapter

    private var recipes: MutableList<RecipeHit> = ArrayList<RecipeHit>()

    companion object {
        fun newInstance() = PreparedFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.prepared_fragment, container, false)
        recPrepared = v.findViewById(R.id.recyclerPrepared)
        viewModel = ViewModelProvider(requireActivity()).get(PreparedViewModel::class.java)

        return v
    }

    override fun onStart(){
        super.onStart()

        recPrepared.setHasFixedSize(true)
        recPrepared.layoutManager = LinearLayoutManager(context)

        recipesListAdapter = RecipesListAdapter{ x -> onCardItemClick(x) }
        recPrepared.adapter = recipesListAdapter
        viewModel.initializeProfile()

        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { result ->
            recipesListAdapter.setData(result)
            recipes = result
            recPrepared.adapter = recipesListAdapter
        })
    }

    fun onItemClick(pos: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun onCardItemClick(int: Int): Boolean {
        var action = PreparedFragmentDirections.actionPreparedFragmentToRecipeDetailFragment(recipes[int]);
        v.findNavController().navigate(action)
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PreparedViewModel::class.java)
        // TODO: Use the ViewModel
    }



}