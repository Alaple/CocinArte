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
import com.bifrost.cocinarte.models.login.EmailSentViewModel

class EmailSentFragment : Fragment() {

    lateinit var v: View

    lateinit var btnEmailSent: Button

    companion object {
        fun newInstance() = EmailSentFragment()
    }

    private lateinit var viewModel: EmailSentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.email_sent_fragment, container, false)

        btnEmailSent = v.findViewById(R.id.btnEmailSent)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnEmailSent.setOnClickListener() {
            val action = EmailSentFragmentDirections.actionEmailSentFragmentToLogInFragment()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmailSentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}