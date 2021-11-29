package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.main.RecipeDetailViewModel
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.entities.RecipeHit
import com.bifrost.cocinarte.models.main.ListIngredientsViewModel
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class RecipeDetailFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtRecipe: TextView
    lateinit var imageRecipe: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var txtMinutes: TextView
    lateinit var btnPrepare: Button
    lateinit var btnFavorite: ImageButton

    //Firebase
    val auth = FirebaseAuth.getInstance()

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
        // Make Text to be Scrolling
        txtDescription.movementMethod = ScrollingMovementMethod()
        txtMinutes = v.findViewById(R.id.txtMinutes)
        btnPrepare = v.findViewById(R.id.btnPrepare)
        btnFavorite = v.findViewById(R.id.btnFavorite)

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
            if (recipe.time != 0) {
                txtMinutes.setText(recipe.time.toString() + " MIN")
            } else {
                txtMinutes.setText("Time not estimated")
            }
            Glide.with(this)
                .load(recipe.image_url)
                .into(imageRecipe)
        }

        // FAVORITE button
        btnFavorite.setOnClickListener() {
            if (recipe != null && auth.currentUser!=null) {
                viewModel.favorite(recipe)
            }else {
                Toast.makeText(this.context, "Something happened, please try again", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this.context, "MY FAVORITE TOO!", Toast.LENGTH_SHORT).show()
        }

        // PREPARE button
        btnPrepare.setOnClickListener() {
            if (recipe != null && auth.currentUser!=null) {
                viewModel.prepare(recipe)
            }else {
                Toast.makeText(this.context, "Something happened, please try again", Toast.LENGTH_LONG).show()
            }
            Toast.makeText(this.context, "GOING TO RECIPE", Toast.LENGTH_LONG).show()

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