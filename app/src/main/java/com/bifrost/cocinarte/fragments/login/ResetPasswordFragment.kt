package com.bifrost.cocinarte.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.ResetPasswordViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ResetPasswordFragment : Fragment() {

    lateinit var v: View

    lateinit var rootLayout: ConstraintLayout

    lateinit var inputResetEmail: TextView
    lateinit var btnResetPassword: Button

     private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

    companion object {
        fun newInstance() = ResetPasswordFragment()
    }

    private lateinit var viewModel: ResetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.reset_password_fragment, container, false)

        rootLayout = v.findViewById(R.id.ResetPasswordConstraintLayout)

        inputResetEmail = v.findViewById(R.id.inputResetEmail)
        btnResetPassword = v.findViewById(R.id.btnResetPassword)

        database= FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        return v
    }

    override fun onStart() {
        super.onStart()

        btnResetPassword.setOnClickListener() {
            val email: String = inputResetEmail.text.toString()

            if(email.isNotEmpty()) {
                auth.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToResetEmailSentFragment2()
                            v.findNavController().navigate(action)
                        } else {
                            Toast.makeText(this.context,"The e-mail is incorrect", Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this.context,"The e-mail cannot be empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResetPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}