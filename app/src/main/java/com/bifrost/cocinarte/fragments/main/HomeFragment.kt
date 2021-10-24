package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapter.RecipeAdapter
import com.bifrost.cocinarte.entities.*
import com.bifrost.cocinarte.models.main.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var v: View

    private lateinit var viewModel: com.bifrost.cocinarte.models.HomeViewModel
    private lateinit var recRecipe: RecyclerView

    private var recipes: MutableList<Recipe> = ArrayList<Recipe>()

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

        recipes.add(Recipe("lacinato_salad", "lacinato_salad", "", 1, "https://www.edamam.com/web-img/7a2/7a2f41a7891e8a8f8a087a96930c6463.jpg", 1.1f, ArrayList<DietLabel>(), ArrayList<Ingredient>(), ArrayList<MealType>(), ArrayList<CuisineType>(), ArrayList<Category>()))
        recipes.add(Recipe("shaved_salad", "shaved_salad", "", 1, "https://www.edamam.com/web-img/7a2/7a2f41a7891e8a8f8a087a96930c6463.jpg", 1.1f, ArrayList<DietLabel>(), ArrayList<Ingredient>(), ArrayList<MealType>(), ArrayList<CuisineType>(), ArrayList<Category>()))
        recipes.add(Recipe("shredded_salad", "shredded_salad", "", 1, "https://www.edamam.com/web-img/7a2/7a2f41a7891e8a8f8a087a96930c6463.jpg", 1.1f, ArrayList<DietLabel>(), ArrayList<Ingredient>(), ArrayList<MealType>(), ArrayList<CuisineType>(), ArrayList<Category>()))
        recipes.add(Recipe("thai_salad", "thai_salad", "", 1, "https://www.edamam.com/web-img/7a2/7a2f41a7891e8a8f8a087a96930c6463.jpg", 1.1f, ArrayList<DietLabel>(), ArrayList<Ingredient>(), ArrayList<MealType>(), ArrayList<CuisineType>(), ArrayList<Category>()))

        recRecipe.setHasFixedSize(true)
        recRecipe.layoutManager = LinearLayoutManager(context)
        recRecipe.adapter = RecipeAdapter(recipes) { index ->
            onItemClick(index)
        }
    }

    private fun initializeText() {

    }

    private fun initializeButtons() {

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(com.bifrost.cocinarte.models.HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun onItemClick(pos: Int) {

    }
}