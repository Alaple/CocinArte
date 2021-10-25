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
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.RegisterViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

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

    //FireBase
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

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
        rootLayout = v.findViewById(R.id.registerLayout)

        //Firebase
        database= FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        return v
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser

        // Initialize all text variables
        initializeText()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeText() {
        txtRegister.setText("REGISTER")
        txtFullName.setText("FULL NAME")
        inputFullName.setHint("John Doe")
        txtEmail.setText("EMAIL")
        inputEmail.setHint("johndoe@email.com")
        txtPassword.setText("PASSWORD")
        inputPassword.setHint("Password")
        btnRegister.setText("REGISTER")
        coloredText()
    }

    private fun initializeButtons() {
        // REGISTER button
        btnRegister.setOnClickListener() {
            // TODO Register
            if (inputEmail.text.isNotEmpty() && inputPassword.text.isNotEmpty()) auth.createUserWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val action = RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
                    v.findNavController().navigate(action)
                    val user = auth.currentUser
                } else {
                    Snackbar.make(rootLayout, "WRONG DATA", Snackbar.LENGTH_SHORT).show()
                }
            }
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

    private fun coloredText() {

        val spannableString = SpannableString("ALREADY HAVE AN ACCOUNT? LOGIN")

        val fColor = ForegroundColorSpan(Color.rgb(47, 219, 188))
        spannableString.setSpan(fColor, 25, 30, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        txtLogIn.text = spannableString
    }
}