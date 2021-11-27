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
import com.bifrost.cocinarte.models.login.ResetEmailSentViewModel

class ResetEmailSentFragment : Fragment() {

    lateinit var v: View

    lateinit var btnBackToLogin: Button

    companion object {
        fun newInstance() = ResetEmailSentFragment()
    }

    private lateinit var viewModel: ResetEmailSentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.reset_email_sent_fragment, container, false)

        btnBackToLogin = v.findViewById(R.id.btnBackToLogin)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnBackToLogin.setOnClickListener() {
            val action = ResetEmailSentFragmentDirections.actionResetEmailSentFragment2ToLogInFragment();
            v.findNavController().navigate(action)
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ResetEmailSentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}