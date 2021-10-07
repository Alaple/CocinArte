package com.bifrost.cocinarte.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v =inflater.inflate(R.layout.fragment_list_ingredients, container, false)
        searchBar = v.findViewById(R.id.searchBar)
        buttons = v.findViewById(R.id.buttonsRecView)

        buttonsViewModel.cargarTest()

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


}