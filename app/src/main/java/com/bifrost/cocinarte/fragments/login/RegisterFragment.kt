package com.bifrost.cocinarte.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.RegisterViewModel

class RegisterFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtRegister: TextView
    lateinit var txtFullName: TextView
    lateinit var inputFullName: EditText
    lateinit var txtEmail: TextView
    lateinit var inputEmail: EditText
    lateinit var txtPassword: TextView
    lateinit var inputPassword: EditText
    lateinit var btnRegister: Button
    lateinit var txtLogIn: TextView

    // For snackbar use
    lateinit var rootLayout: ConstraintLayout

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.register_fragment, container, false)

        // Initialize variables
        txtRegister = v.findViewById(R.id.textRegister)
        txtFullName = v.findViewById(R.id.textFullName)
        inputFullName = v.findViewById(R.id.inputFullName)
        txtEmail = v.findViewById(R.id.textEmail)
        inputEmail = v.findViewById(R.id.inputEmail)
        txtPassword = v.findViewById(R.id.textPassword)
        inputPassword = v.findViewById(R.id.inputPassword)
        btnRegister = v.findViewById(R.id.buttonRegister)
        txtLogIn = v.findViewById(R.id.textLogin)
        // For snackbar use
        rootLayout = v.findViewById(R.id.LoginLayout)

        return v
    }

    override fun onStart() {
        super.onStart()

        // Initialize all text variables
        initializeText()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeText() {
        txtRegister.setText("REGISTER")
        txtFullName.setText("FULL NAME")
        inputFullName.setHint("Jhon Doe")
        txtEmail.setText("EMAIL")
        inputEmail.setHint("jhondoe@email.com")
        txtPassword.setText("PASSWORD")
        inputPassword.setHint("Password")
        btnRegister.setText("REGISTER")
        txtLogIn.setText("ALREADY HAVE AN ACCOUNT? LOGIN")
    }

    private fun initializeButtons() {
        // REGISTER button
        btnRegister.setOnClickListener() {
            // TODO Register
            val action = RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
            v.findNavController().navigate(action)
        }

        // LOGIN button
        txtLogIn.setOnClickListener() {
            val action = RegisterFragmentDirections.actionRegisterFragmentToLogInFragment()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }
}