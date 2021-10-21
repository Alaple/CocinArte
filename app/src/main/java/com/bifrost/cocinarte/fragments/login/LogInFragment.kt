package com.bifrost.cocinarte.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.LogInViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LogInFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtLogin: TextView
    lateinit var imageLogin: ImageView
    lateinit var txtEmail: TextView
    lateinit var inputEmail: EditText
    lateinit var txtPassword: TextView
    lateinit var inputPassword: EditText
    lateinit var txtForgotPassword: TextView
    lateinit var btnLogin: Button
    lateinit var txtRegister: TextView
    //FireBase
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    // For snackbar use
    lateinit var rootLayout: ConstraintLayout

    companion object {
        fun newInstance() = LogInFragment()
    }

    private lateinit var viewModel: LogInViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.log_in_fragment, container, false)

        // Initialize variables
        txtLogin = v.findViewById(R.id.textLogIn)
        imageLogin = v.findViewById(R.id.imageLogin)
        txtEmail = v.findViewById(R.id.textEmail)
        inputEmail = v.findViewById(R.id.inputEmail)
        txtPassword = v.findViewById(R.id.textPassword)
        inputPassword = v.findViewById(R.id.inputPassword)
        txtForgotPassword = v.findViewById(R.id.textForgotPassword)
        btnLogin = v.findViewById(R.id.buttonLogIn)
        txtRegister = v.findViewById(R.id.textRegister)
        // For snackbar use
        rootLayout = v.findViewById(R.id.LoginLayout)

        //Firebase
        database= FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()


        return v
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        // Initialize image variables
        imageLogin.setImageResource(R.drawable.logo_cocinarte)
        // Initialize all text variables
        initializeText()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeText() {
        txtLogin.setText("LOGIN")
        txtEmail.setText("EMAIL")
        inputEmail.setHint("johndoe@email.com")
        txtPassword.setText("PASSWORD")
        inputPassword.setHint("Password")
        txtForgotPassword.setText("FORGOT PASSWORD?")
        btnLogin.setText("LOGIN")
        txtRegister.setText("YOU DON`T HAVE AN ACCOUNT? REGISTER")
    }

    private fun initializeButtons() {
        // LOGIN button
        btnLogin.setOnClickListener() {
            // TODO Login

            if(inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) auth.signInWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val action = LogInFragmentDirections.actionLogInFragmentToMainActivity()
                    v.findNavController().navigate(action)
                    val user = auth.currentUser
                } else {
                    Snackbar.make(rootLayout, "WRONG DATA", Snackbar.LENGTH_SHORT).show()
                }
            }
        }


        // Forgot Password button
        txtForgotPassword.setOnClickListener() {
            val action = LogInFragmentDirections.actionLogInFragmentToResetPasswordFragment()
            v.findNavController().navigate(action)
        }

        // Register Password button
        txtRegister.setOnClickListener() {
            val action = LogInFragmentDirections.actionLogInFragmentToRegisterFragment()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LogInViewModel::class.java)
        // TODO: Use the ViewModel
    }
}