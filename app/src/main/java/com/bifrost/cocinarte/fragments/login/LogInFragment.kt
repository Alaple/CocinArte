package com.bifrost.cocinarte.fragments.login

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.LogInViewModel
import com.google.firebase.auth.FirebaseAuth
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
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

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

        //Firebase
        database= FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()


        return v
    }

    override fun onStart() {
        super.onStart()

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
        coloredText()
    }

    private fun initializeButtons() {
        // LOGIN button
        btnLogin.setOnClickListener() {
            if(inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) {
                auth.signInWithEmailAndPassword(
                    inputEmail.text.toString(),
                    inputPassword.text.toString()
                ).addOnCompleteListener(requireActivity()) { task ->
                    if (task.isSuccessful) {
                        val action = LogInFragmentDirections.actionLogInFragmentToMainActivity()
                        v.findNavController().navigate(action)
                    } else {
                        Toast.makeText(this.context,"Wrong Data", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this.context,"Wrong Data", Toast.LENGTH_SHORT).show()
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

    private fun coloredText() {
        val spannableString = SpannableString("YOU DON'T HAVE AN ACCOUNT? REGISTER")

        val fColor = ForegroundColorSpan(Color.rgb(47, 219, 188))
        spannableString.setSpan(fColor, 27, 35, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        txtRegister.text = spannableString
    }
}

