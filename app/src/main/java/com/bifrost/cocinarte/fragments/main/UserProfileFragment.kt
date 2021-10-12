package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.fragments.login.StartedFragmentDirections
import com.bifrost.cocinarte.models.main.UserProfileViewModel

class UserProfileFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtAccountProfile: TextView
    lateinit var txtRewards: TextView
    lateinit var txtPreferences: TextView
    lateinit var txtFavourites: TextView
    lateinit var txtPreparedMeals: TextView
    lateinit var txtLogout: TextView

    companion object {
        fun newInstance() = UserProfileFragment()
    }

    private lateinit var viewModel: UserProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.user_profile_fragment, container, false)

        txtAccountProfile = v.findViewById(R.id.txtAccountProfile)
        txtRewards = v.findViewById(R.id.txtRewards)
        txtPreferences = v.findViewById(R.id.txtPreferences)
        txtFavourites = v.findViewById(R.id.txtFavourites)
        txtPreparedMeals = v.findViewById(R.id.txtPreparedMeals)
        txtLogout = v.findViewById(R.id.txtLogOut)

        return v
    }

    override fun onStart() {
        super.onStart()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeButtons() {
        txtAccountProfile.setOnClickListener() {
            // TODO Navigation to Account and Profile
        }

        txtRewards.setOnClickListener() {
            val action = UserProfileFragmentDirections.actionUserProfileFragmentToRewardsFragment()
            v.findNavController().navigate(action)
        }

        txtPreferences.setOnClickListener() {
            val action = UserProfileFragmentDirections.actionUserProfileFragmentToPreferenceActivity()
            v.findNavController().navigate(action)
        }

        txtFavourites.setOnClickListener() {
            val action = UserProfileFragmentDirections.actionUserProfileFragmentToFavouritesFragment()
            v.findNavController().navigate(action)
        }

        txtPreparedMeals.setOnClickListener() {
            val action = UserProfileFragmentDirections.actionUserProfileFragmentToPreparedFragment()
            v.findNavController().navigate(action)
        }

        txtLogout.setOnClickListener() {
            // TODO Navigation to Logout
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}