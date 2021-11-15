package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.main.RecipeDetailViewModel
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.entities.RecipeHit
import com.bifrost.cocinarte.entities.User
import com.bifrost.cocinarte.models.main.ListIngredientsViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class RecipeDetailFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtRecipe: TextView
    lateinit var imageRecipe: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var btnPrepare: Button
    lateinit var txtMinutes: TextView



    // For snackbar use
    lateinit var rootLayout: ConstraintLayout

    private lateinit var viewModel: RecipeDetailViewModel

    lateinit var listIngredientsViewModel: ListIngredientsViewModel

    companion object {
        fun newInstance() = RecipeDetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.recipe_detail_fragment, container, false)

        // Initialize variables
        txtRecipe = v.findViewById(R.id.txtRecipe)
        imageRecipe = v.findViewById(R.id.imageRecipe)
        txtTitle = v.findViewById(R.id.txtTitle)
        txtDescription = v.findViewById(R.id.txtDescription)
        btnPrepare = v.findViewById(R.id.btnPrepare)
        txtMinutes = v.findViewById(R.id.txtMinutes)

        // For snackbar use
        rootLayout = v.findViewById(R.id.RecipeDetailLayout)

        listIngredientsViewModel = ViewModelProvider(requireActivity()).get(ListIngredientsViewModel::class.java)

        return v
    }

    override fun onStart() {
        super.onStart()

        val recipe = RecipeDetailFragmentArgs.fromBundle(requireArguments()).recipeHit

        initialize(recipe)
        viewModel.getUser()
    }

    private fun initialize(recipe: RecipeHit?) {
        if (recipe != null) {
            txtRecipe.setText(recipe.label)
            txtDescription.setText(recipe.ingredients?.joinToString("\n"))
            txtMinutes.setText(recipe.time.toString() + " MIN")
            Glide.with(this)
                .load(recipe.image_url)
                .into(imageRecipe)
        }

        // PREPARE button
        btnPrepare.setOnClickListener() {
            if (recipe != null && viewModel.user!= null) {
                viewModel.prepare(recipe)
            }else {
                Snackbar.make(
                    rootLayout,
                    "Something happened, please try again",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            Snackbar.make(rootLayout, "GOING TO RECIPE", Snackbar.LENGTH_LONG).show()

            // Navigation to RecipeUrlFragment
            var action = RecipeDetailFragmentDirections.actionRecipeDetailFragmentToRecipeUrlFragment(
                recipe?.url!!)
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
    }

}