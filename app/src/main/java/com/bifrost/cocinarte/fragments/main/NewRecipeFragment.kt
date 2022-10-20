package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.main.NewRecipeModel
import com.google.firebase.auth.FirebaseAuth

class NewRecipeFragment : Fragment() {

    lateinit var v: View
    lateinit var btnCreate: Button
    private lateinit var auth: FirebaseAuth

    companion object {
        fun newInstance() = NewRecipeFragment()
    }

    private lateinit var viewModel: NewRecipeModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.new_recepie_fragment, container, false)

        btnCreate = v.findViewById(R.id.btnCreate)

        auth = FirebaseAuth.getInstance()

        return v
    }

    override fun onStart() {
        super.onStart()

        btnCreate.setOnClickListener() {
            // auth.signOut()
            // val action = LogOutFragmentDirections.actionLogOutFragmentToLoginActivity()
            // v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewRecipeModel::class.java)
        // TODO: Use the ViewModel
    }

}