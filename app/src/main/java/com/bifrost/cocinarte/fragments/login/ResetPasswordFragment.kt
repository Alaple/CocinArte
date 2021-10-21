package com.bifrost.cocinarte.fragments.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.login.ResetPasswordViewModel

class ResetPasswordFragment : Fragment() {

    lateinit var v: View

    lateinit var btnResetPassword: Button

    companion object {
        fun newInstance() = ResetPasswordFragment()
    }

    private lateinit var viewModel: ResetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.reset_password_fragment, container, false)

        btnResetPassword = v.findViewById(R.id.btnResetPassword)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnResetPassword.setOnClickListener() {
            // TODO Reset password
            val action = ResetPasswordFragmentDirections.actionResetPasswordFragmentToResetEmailSentFragment2()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResetPasswordViewModel::class.java)
        // TODO: Use the ViewModel
    }

}