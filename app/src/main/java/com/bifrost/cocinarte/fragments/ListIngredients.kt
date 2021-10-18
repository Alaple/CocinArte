package com.bifrost.cocinarte.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
<<<<<<< HEAD
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.ButtonListAdapter
import com.bifrost.cocinarte.models.ButtonsViewModel
import com.google.android.material.textfield.TextInputEditText

class ListIngredients : Fragment() {


    lateinit var searchBar : TextInputEditText
    lateinit var buttons: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var buttonListAdapter: ButtonListAdapter
    private lateinit var buttonsViewModel: ButtonsViewModel
    lateinit var v: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

=======
=======
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.adapters.ButtonListAdapter
import com.bifrost.cocinarte.models.ButtonsViewModel
import com.google.android.material.textfield.TextInputEditText

class ListIngredients : Fragment() {


    lateinit var searchBar : TextInputEditText
    lateinit var buttons: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var buttonListAdapter: ButtonListAdapter
    private lateinit var buttonsViewModel: ButtonsViewModel
    lateinit var v: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
<<<<<<< HEAD
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
>>>>>>> 0cb277c (COC-24 Create Fragment+xml file)
=======

>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
        v =inflater.inflate(R.layout.fragment_list_ingredients, container, false)
        searchBar = v.findViewById(R.id.searchBar)
        buttons = v.findViewById(R.id.buttonsRecView)

<<<<<<< HEAD
        buttonsViewModel = ViewModelProvider(requireActivity()).get(ButtonsViewModel::class.java)

        buttonsViewModel.cargarTest()

        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()

        buttons.setHasFixedSize(true)
        //linearLayoutManager = LinearLayoutManager(context)
        linearLayoutManager = GridLayoutManager(context, 3)
        buttons.layoutManager = linearLayoutManager

        buttonListAdapter = ButtonListAdapter(buttonsViewModel.buttonsList)

        buttons.adapter = buttonListAdapter

    }


=======
=======
        buttonsViewModel.cargarTest()

>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
        // Inflate the layout for this fragment
        return v
    }

    override fun onStart() {
        super.onStart()

        buttons.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        buttons.layoutManager = linearLayoutManager

        buttonListAdapter = ButtonListAdapter(buttonsViewModel.buttonsList)

    }
<<<<<<< HEAD
>>>>>>> 0cb277c (COC-24 Create Fragment+xml file)
=======


>>>>>>> 097be13 (COC-24-Recycler View implementation WIP)
}