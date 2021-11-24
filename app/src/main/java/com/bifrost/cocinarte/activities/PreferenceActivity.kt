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

        // var categories = listOf("Vegetarian", "Gluten-Free", "Keto", "Low Sugar", "Kosher", "Vegan")
        var categories = Category.values()

        // Component variables
        lateinit var txtPreferences: TextView
        lateinit var txtLanguage: TextView
        lateinit var txtProfile: TextView
        lateinit var spinnerLanguage: Spinner
        lateinit var spinnerProfile: Spinner
        lateinit var txtTheme: TextView
        lateinit var spinnerTheme: Spinner
        lateinit var txtNotifications: TextView
        lateinit var switchPush: Switch
        lateinit var switchEmail: Switch

        //FireBase
        private lateinit var dbReference: DatabaseReference
        private lateinit var database: FirebaseDatabase
        private lateinit var auth: FirebaseAuth

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
            txtLanguage = v.findViewById(R.id.textLanguage)
            spinnerLanguage = v.findViewById(R.id.spinnerLanguage)
            txtTheme = v.findViewById(R.id.textTheme)
            spinnerTheme = v.findViewById(R.id.spinnerTheme)
            txtProfile = v.findViewById(R.id.textProfile)
            spinnerProfile = v.findViewById(R.id.spinnerProfile)
            txtNotifications = v.findViewById(R.id.textNotifications)
            switchPush = v.findViewById(R.id.switchPush)
            switchEmail = v.findViewById(R.id.switchEmail)

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

            // Initialize all text variables
            initializeText()

            // Initialize all spinners variables
            initializeSpinners()

            // Initialize all buttons variables
            initializeButtons()

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

        private fun initializeText() {
            txtPreferences.setText("PREFERENCES")
            txtLanguage.setText("LANGUAGE")
            txtProfile.setText("PROFILE")
            txtTheme.setText("APPLICATION THEME")
            txtNotifications.setText("NOTIFICATIONS")
            switchPush.setText("PUSH NOTIFICATIONS")
            switchEmail.setText("EMAIL NOTIFICATIONS")
        }

        private fun initializeSpinners() {

            // spinnerLanguage.onItemSelectedListener() { }
            // spinnerTheme.onItemSelectedListener() { }

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

        private fun initializeButtons() {

            switchPush.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                }
            }

            switchEmail.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                }
            }
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
