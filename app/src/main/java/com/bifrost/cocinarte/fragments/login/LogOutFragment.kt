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
import com.bifrost.cocinarte.models.login.LogOutViewModel

class LogOutFragment : Fragment() {

    lateinit var v: View

    lateinit var btnLogOut: Button

    companion object {
        fun newInstance() = LogOutFragment()
    }

    private lateinit var viewModel: LogOutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.log_out_fragment, container, false)

        btnLogOut = v.findViewById(R.id.btnLogOut)

        return v
    }

    override fun onStart() {
        super.onStart()

        btnLogOut.setOnClickListener() {
            // TODO Logout
            val action = LogOutFragmentDirections.actionLogOutFragmentToStartedFragment()
            v.findNavController().navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LogOutViewModel::class.java)
        // TODO: Use the ViewModel
    }

}