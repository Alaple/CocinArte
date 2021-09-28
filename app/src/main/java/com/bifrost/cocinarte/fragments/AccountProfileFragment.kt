package com.bifrost.cocinarte.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.AccountProfileViewModel

class AccountProfileFragment : Fragment() {

    lateinit var v: View

    // Component variables
    lateinit var txtAccount: TextView
    lateinit var txtFirstName: TextView
    lateinit var inputFirstName: EditText
    lateinit var txtLastName: TextView
    lateinit var inputLastName: EditText
    lateinit var txtEmail: TextView
    lateinit var inputEmail: EditText
    lateinit var imgDeleteAccount: ImageView
    lateinit var txtDeleteAccount: TextView
    lateinit var imgChangePassword: ImageView
    lateinit var txtChangePassword: TextView
    lateinit var btnUpdate: Button

    companion object {
        fun newInstance() = AccountProfileFragment()
    }

    private lateinit var viewModel: AccountProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        v = inflater.inflate(R.layout.account_profile_fragment, container, false)

        // Initialize variables
        txtAccount = v.findViewById(R.id.txtAccountProfile)
        txtFirstName = v.findViewById(R.id.txtFirstName)
        inputFirstName = v.findViewById(R.id.inputFirstName)
        txtLastName = v.findViewById(R.id.txtLastName)
        inputLastName = v.findViewById(R.id.inputLastName)
        txtEmail = v.findViewById(R.id.txtEmail)
        inputEmail = v.findViewById(R.id.inputAPEmail)
        imgDeleteAccount = v.findViewById(R.id.imgDeleteAccount)
        txtDeleteAccount = v.findViewById(R.id.txtDeleteAccount)
        imgChangePassword = v.findViewById(R.id.imgChangePassword)
        txtChangePassword = v.findViewById(R.id.txtChangePassword)
        btnUpdate = v.findViewById(R.id.btnUpdate)

        return v
    }

    override fun onStart() {
        super.onStart()

        // Initialize all text variables
        initializeText()
    }

    private fun initializeText() {
        txtAccount.setText("Account and profile")
        txtFirstName.setText("First name")
        txtLastName.setText("Last name")
        txtEmail.setText("Email")
        txtDeleteAccount.setText("Delete account")
        txtChangePassword.setText("Change Password")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AccountProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}