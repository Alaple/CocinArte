package com.bifrost.cocinarte.activities

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.entities.Category
import com.bifrost.cocinarte.models.PreferenceViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.*

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preferenceLayout, PreferenceFragment())
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    class PreferenceFragment : Fragment() {

        lateinit var v: View

        var categories = Category.values()

        lateinit var txtPreferences: TextView
        lateinit var txtProfile: TextView
        lateinit var spinnerProfile: Spinner

        // For snackbar use
        lateinit var rootLayout: ConstraintLayout

        companion object {
            fun newInstance() = PreferenceFragment()
        }

        private lateinit var viewModel: PreferenceViewModel

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {

            v = inflater.inflate(R.layout.activity_preference, container, false)

            // Initialize variables
            txtPreferences = v.findViewById(R.id.textPreferences)
            txtProfile = v.findViewById(R.id.textProfile)
            spinnerProfile = v.findViewById(R.id.spinnerProfile)

            // For snackbar use
            rootLayout = v.findViewById(R.id.preferenceLayout)

            return v
        }

        override fun onStart() {
            super.onStart()

            // LOAD USER (Coroutine)
            val job = Job()
            val scope = CoroutineScope(Dispatchers.Default + job)
            scope.launch {
                val getUser = async{
                    try {
                        viewModel.loadUserProfile()
                    } catch (e: Exception) {
                        e.message?.let { Log.d("Error", it) }
                    }
                }

                getUser.await()
            }

            txtPreferences.setText("PREFERENCES")
            txtProfile.setText("PROFILE")

            initializeSpinners()

            viewModel.userLiveData.observe(viewLifecycleOwner, { result ->
                if (result.profile == null) {
                    spinnerProfile.setSelection(0, false)
                } else {
                    categories.forEachIndexed { i, c ->
                        if (c == result.profile) {
                            spinnerProfile.setSelection(i, false)
                        }
                    }
                }
            })
        }

        private fun initializeSpinners() {

            populateSpinner(spinnerProfile, categories, requireContext())

            spinnerProfile.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    viewModel.updateUserProfile(categories[position])
                    // Snackbar.make(v, categories[position], Snackbar.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            })
        }

        private fun populateSpinner(spinner: Spinner, list : Array<Category>, context : Context) {
            val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, list)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(this).get(PreferenceViewModel::class.java)
            // TODO: Use the ViewModel
        }
    }
}
