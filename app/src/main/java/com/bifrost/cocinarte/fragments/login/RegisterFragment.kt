package com.bifrost.cocinarte.fragments.login

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.RegisterViewModel
import kotlinx.coroutines.*

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
        rootLayout = v.findViewById(R.id.registerLayout)

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

            val userEmail = inputEmail.text.toString()
            val userName = inputFullName.text.toString()
            val userPassword = inputPassword.text.toString()
            val job1 = Job()
            val job2 = Job()
            val scope = CoroutineScope(Dispatchers.Default + job1 + job2)

            scope.launch{
                val createUserJob = async{
                    //Add user to Auth
                    try{
                        viewModel.createAuthUser(
                            userEmail,
                            userPassword
                        )
                        //Add user to DB
                        viewModel.createDbUser(
                            userName,
                            userEmail,
                            userPassword
                        )

                    }catch (e: Exception){
                        Log.d("Coroutine: ", e.message.toString())
                    }

                }
                val navigate = async{
                    val action = RegisterFragmentDirections.actionRegisterFragmentToMainActivity()
                    v.findNavController().navigate(action)
                }
                createUserJob.await()
                navigate.await()
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