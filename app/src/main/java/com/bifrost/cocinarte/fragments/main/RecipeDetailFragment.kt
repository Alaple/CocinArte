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
        rootLayout = v.findViewById(R.id.LoginLayout)

        return v
    }

    private fun initializeText() {
        txtRecipe.setText("SALMON")
        txtTitle.setText("DESCRIPTION")
        txtDescription.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        btnPrepare.setText("PREPARE")
    }

    private fun initializeButtons() {
        // PREPARE button
        btnPrepare.setOnClickListener() {
            // TODO Prepare
            Snackbar.make(rootLayout, "TODO PREPARE", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RecipeDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}