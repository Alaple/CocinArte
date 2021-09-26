package com.bifrost.cocinarte.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.AccountProfileViewModel

class AccountProfileFragment : Fragment() {

    lateinit var v: View

    companion object {
        fun newInstance() = AccountProfileFragment()
    }

    private lateinit var viewModel: AccountProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.account_profile_fragment, container, false)

        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}