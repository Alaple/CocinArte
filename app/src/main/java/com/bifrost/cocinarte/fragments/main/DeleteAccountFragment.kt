package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.main.DeleteAccountViewModel

class DeleteAccountFragment : Fragment() {

    lateinit var v: View

    lateinit var btnDeleteAccount: Button

    companion object {
        fun newInstance() = DeleteAccountFragment()
    }

    private lateinit var viewModel: DeleteAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.delete_account_fragment, container, false)

        btnDeleteAccount = v.findViewById(R.id.btnDeleteAccount)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnDeleteAccount.setOnClickListener() {
            // TODO Delete account
            val action = DeleteAccountFragmentDirections.actionDeleteAccountFragmentToLoginActivity()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeleteAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}