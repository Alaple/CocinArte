package com.bifrost.cocinarte.fragments.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.main.DeleteAccountViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class DeleteAccountFragment : Fragment() {

    lateinit var v: View

    lateinit var btnDeleteAccount: Button

    lateinit var rootLayout: ConstraintLayout

    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth

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

        rootLayout = v.findViewById(R.id.frameLayout)

        database= FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        return v
    }

    override fun onStart() {
        super.onStart()

        btnDeleteAccount.setOnClickListener() {
            val user = auth.currentUser!!

            user.delete()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val action = DeleteAccountFragmentDirections.actionDeleteAccountFragmentToLoginActivity()
                        v.findNavController().navigate(action)
                    } else {
                        Toast.makeText(this.context,"The user cannot be deleted", Toast.LENGTH_LONG).show()
                    }
                }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeleteAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }
}