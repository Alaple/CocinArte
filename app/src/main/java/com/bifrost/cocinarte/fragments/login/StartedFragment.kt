package com.bifrost.cocinarte.fragments.login

import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.StartedViewModel
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import com.google.firebase.auth.FirebaseAuth

class StartedFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtStarted: TextView
    lateinit var msgStarted: TextView
    lateinit var btnLogin: Button
    lateinit var btnRegister: Button
    lateinit var txtSkip: TextView

    // For snackbar use
    lateinit var rootLayout: ConstraintLayout

    // Firebase
    lateinit var auth: FirebaseAuth

    companion object {
        fun newInstance() = StartedFragment()
    }

    private lateinit var viewModel: StartedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.started_fragment, container, false)

        // Initialize variables
        txtStarted = v.findViewById(R.id.textStarted)
        msgStarted = v.findViewById(R.id.messageStarted)
        btnLogin = v.findViewById(R.id.buttonLogIn)
        btnRegister = v.findViewById(R.id.buttonRegister)
        txtSkip = v.findViewById(R.id.textSkip)
        // For snackbar use
        rootLayout = v.findViewById(R.id.getsLayout)
        // Firebase
        auth = FirebaseAuth.getInstance()

        return v
    }

    override fun onStart() {
        super.onStart()

        // If there is a Login user redirect to Main Activity.
        val authUser = auth.currentUser
        if (authUser != null) {
            val action = StartedFragmentDirections.actionStartedFragmentToMainActivity()
            v.findNavController().navigate(action)
        }

        // Initialize all text variables
        initializeText()

        // Initialize all buttons variables
        initializeButtons()
    }

    private fun initializeText() {
        msgStarted.setText("Get started and enjoy the awesome food recepies that we have for you!")
        btnLogin.setText("LOGIN")
        btnRegister.setText("REGISTER")
        txtSkip.setText("SKIP THIS PART")
        coloredText()
    }

    private fun initializeButtons() {
        // LOGIN button
        btnLogin.setOnClickListener() {
            val action = StartedFragmentDirections.actionStartedFragmentToLogInFragment()
            v.findNavController().navigate(action)
        }

        // REGISTER button
        btnRegister.setOnClickListener() {
            val action = StartedFragmentDirections.actionStartedFragmentToRegisterFragment()
            v.findNavController().navigate(action)
        }

        // Forgot Password text button
        txtSkip.setOnClickListener() {
            val action = StartedFragmentDirections.actionStartedFragmentToMainActivity()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StartedViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun coloredText() {
        val spannableString = SpannableString("GET STARTED")

        val fColor = ForegroundColorSpan(Color.rgb(47, 219, 188))
        spannableString.setSpan(fColor, 4, 11, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)

        txtStarted.text = spannableString
    }
}