package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.main.UserProfileViewModel

class UserProfileFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtAccountProfile: TextView
    lateinit var txtPreferences: TextView
    lateinit var txtFavourites: TextView
    lateinit var txtPreparedMeals: TextView
    lateinit var txtNewRecipe: TextView
    lateinit var txtMyRecipe: TextView
    lateinit var txtLogout: TextView

    lateinit var txtUserName: TextView
    lateinit var txtLevel: TextView

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.user_profile_fragment, container, false)

        // Buttons
        txtAccountProfile = v.findViewById(R.id.txtAccountProfile)
        txtPreferences = v.findViewById(R.id.txtPreferences)
        txtFavourites = v.findViewById(R.id.txtFavourites)
        txtPreparedMeals = v.findViewById(R.id.txtPreparedMeals)
        txtNewRecipe = v.findViewById(R.id.txtNewRecipe)
        txtMyRecipe = v.findViewById(R.id.txtMyRecipe)
        txtLogout = v.findViewById(R.id.txtLogOut)
        // Texts
        txtUserName = v.findViewById(R.id.txtUserName)
        txtLevel = v.findViewById(R.id.txtLevel)

        return v
    }

    override fun onStart() {
        super.onStart()

        // Initialize profile
        viewModel.initializeProfile()
        // Initialize buttons
        initializeButtons()
        // Initialize texts
        initializeTextViews()
    }

    private fun initializeTextViews() {
        viewModel.userLiveData.observe(viewLifecycleOwner, Observer { result ->
            txtUserName.setText(result.name)
            txtLevel.setText("Level " + result.level)
        })
    }

    private fun initializeButtons() {
        txtAccountProfile.setOnClickListener() {
            val action = UserProfileFragmentDirections
                .actionUserProfileFragmentToAccountProfileFragment()
            v.findNavController().navigate(action)
        }

        txtPreferences.setOnClickListener() {
            val action = UserProfileFragmentDirections
                .actionUserProfileFragmentToPreferenceActivity()
            v.findNavController().navigate(action)
        }

        txtFavourites.setOnClickListener() {
            val action = UserProfileFragmentDirections
                .actionUserProfileFragmentToFavouritesFragment()
            v.findNavController().navigate(action)
        }

        txtPreparedMeals.setOnClickListener() {
            val action = UserProfileFragmentDirections
                .actionUserProfileFragmentToPreparedFragment()
            v.findNavController().navigate(action)
        }

        txtNewRecipe.setOnClickListener() {
            val action = UserProfileFragmentDirections
                .actionUserProfileFragmentToNewRecipeFragment3()
            v.findNavController().navigate(action)
        }

        txtMyRecipe.setOnClickListener() {
            val action = UserProfileFragmentDirections
                .actionUserProfileFragmentToMyRecipesFragment()
            v.findNavController().navigate(action)
        }


        txtLogout.setOnClickListener() {
            val action = UserProfileFragmentDirections
                .actionUserProfileFragmentToLogOutFragment()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
    }

}