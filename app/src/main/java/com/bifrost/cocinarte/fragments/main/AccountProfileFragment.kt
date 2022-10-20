package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.dialogs.ResetPasswordDialogFragment
import com.bifrost.cocinarte.models.main.AccountProfileViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AccountProfileFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtAccount: TextView
    lateinit var txtFirstName: TextView
    lateinit var inputFirstName: EditText
    lateinit var txtLastName: TextView
    lateinit var inputLastName: EditText
    lateinit var txtEmail: TextView
    lateinit var inputEmail: EditText
    lateinit var imgDeleteAccount: ImageView
    lateinit var txtDeleteAccount: TextView
    lateinit var imgChangePassword: ImageView
    lateinit var txtChangePassword: TextView
    lateinit var btnUpdate: Button

    //Firebase
    val db = Firebase.firestore
    var auth = FirebaseAuth.getInstance()

    companion object {
        fun newInstance() = AccountProfileFragment()
    }

    private lateinit var viewModel: AccountProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.account_profile_fragment, container, false)

        // Initialize variables
        txtAccount = v.findViewById(R.id.txtAccountProfileTitle)
        txtFirstName = v.findViewById(R.id.txtRName)
        inputFirstName = v.findViewById(R.id.inputRName)
        txtEmail = v.findViewById(R.id.txtDescription)
        inputEmail = v.findViewById(R.id.inputREmail)
        imgDeleteAccount = v.findViewById(R.id.imgDeleteAccount)
        txtDeleteAccount = v.findViewById(R.id.txtDeleteAccount)
        imgChangePassword = v.findViewById(R.id.imgChangePassword)
        txtChangePassword = v.findViewById(R.id.txtChangePassword)
        btnUpdate = v.findViewById(R.id.btnCreate)

        return v
    }

    override fun onStart() {
        super.onStart()

        viewModel.initializeProfile()
        // Initialize all text variables
        initializeText()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeText() {
        viewModel.userLiveData.observe(viewLifecycleOwner, { result ->
            txtAccount.setText("ACCOUNT AND PROFILE")
            txtFirstName.setText("FULL NAME")
            txtEmail.setText("EMAIL")
            txtDeleteAccount.setText("Delete account")
            txtChangePassword.setText("Change Password")
            inputFirstName.setText(result.name.toString())
            inputEmail.setText(result.email.toString())
        })
    }

    private fun initializeButtons() {
        // Delete button
        txtDeleteAccount.setOnClickListener() {
            deleteAccountNavigation();
        }

        imgDeleteAccount.setOnClickListener() {
            deleteAccountNavigation();
        }

        // Change Password button
        txtChangePassword.setOnClickListener() {
            changePasswordNavigation();
        }

        imgChangePassword.setOnClickListener() {
            changePasswordNavigation();
        }

        // Update button
        btnUpdate.setOnClickListener() {
            viewModel.userLiveData.observe(viewLifecycleOwner, { result ->
                result.name = inputFirstName.text.toString()

                if(result.email != inputEmail.text.toString()){
                    result.email?.let {db.collection("users").document(it).delete()}
                    result.email = inputEmail.text.toString()
                    auth.currentUser!!.updateEmail(result.email.toString())
                }

                result.email?.let {db.collection("users").document(it).set(result)}
                Toast.makeText(this.context,"UPDATED",Toast.LENGTH_LONG).show()
            })

        }
    }

    private fun deleteAccountNavigation() {
        val action = AccountProfileFragmentDirections.actionAccountProfileFragmentToDeleteAccountFragment()
        v.findNavController().navigate(action)
    }

    private fun changePasswordNavigation() {
        ResetPasswordDialogFragment().show(
            childFragmentManager, ResetPasswordDialogFragment.TAG)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }
}