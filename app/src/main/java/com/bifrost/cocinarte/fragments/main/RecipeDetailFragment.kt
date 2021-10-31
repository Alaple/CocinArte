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
import com.bifrost.cocinarte.entities.Recipe
import com.google.android.material.snackbar.Snackbar

class RecipeDetailFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtRecipe: TextView
    lateinit var imageRecipe: ImageView
    lateinit var txtTitle: TextView
    lateinit var txtDescription: TextView
    lateinit var btnPrepare: Button

    // For snackbar use
    lateinit var rootLayout: ConstraintLayout

    companion object {
        fun newInstance() = RecipeDetailFragment()
    }

    private lateinit var viewModel: RecipeDetailViewModel

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

        // For snackbar use
        rootLayout = v.findViewById(R.id.RecipeDetailLayout)

        return v
    }

    override fun onStart() {
        super.onStart()

        // PREPARE button
        btnPrepare.setOnClickListener() {
            // TODO Reeplace "recipeId" with the real id
            viewModel.prepare("recipeId")
            Snackbar.make(rootLayout, "MARK AS PREPARE", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
    }

}