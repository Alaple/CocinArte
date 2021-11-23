package com.bifrost.cocinarte.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bifrost.cocinarte.R
import com.bifrost.cocinarte.models.PreferenceViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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

        // Component variables
        lateinit var txtPreferences: TextView
        lateinit var txtTheme: TextView
         lateinit var switchPush: Switch

        //FireBase
        private lateinit var dbReference: DatabaseReference
        private lateinit var database: FirebaseDatabase
        private lateinit var auth: FirebaseAuth

        val db = Firebase.firestore
        var authUser = FirebaseAuth.getInstance()

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
            txtTheme = v.findViewById(R.id.textTheme)
            switchPush = v.findViewById(R.id.switchPush)

            // For snackbar use
            rootLayout = v.findViewById(R.id.preferenceLayout)

            //Firebase
            database= FirebaseDatabase.getInstance()
            auth = FirebaseAuth.getInstance()

            return v
        }

        override fun onStart() {
            super.onStart()
            val currentUser = auth.currentUser

            // Initialize all text variables
            initializeText()

            //View model initialize
            viewModel.initializeProfile()

            // Initialize all buttons variables
            initializeButtons()
        }

        private fun initializeText() {
            txtPreferences.setText("SETTINGS")
            txtTheme.setText("APPLICATION THEME")
            switchPush.setText("DARK MODE")
        }

        private fun initializeButtons() {
            // SETTINGS

            // spinnerLanguage.onItemSelectedListener() { }
            // spinnerTheme.onItemSelectedListener() { }

            // DARK MODE
            switchPush.setOnCheckedChangeListener { _, isChecked ->

                if (isChecked) {
                    // The toggle is enabled
                    Log.d("CAMBIO","DARK MODE "+ isChecked)
                    viewModel.userLiveData.observe(viewLifecycleOwner, { result ->
                        Log.d("DARK MODE","CAMBIO EL USUARIO")
                        result.userPrefence!!.appTheme = true
                        result.email?.let {db.collection("users").document(it).set(result)}
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    })
                } else {
                    // The toggle is disabled
                    Log.d("CAMBIO","DARK MODE "+ isChecked)
                    viewModel.userLiveData.observe(viewLifecycleOwner, { result ->
                        Log.d("DARK MODE","CAMBIO EL USUARIO")
                        result.userPrefence!!.appTheme = false
                        result.email?.let {db.collection("users").document(it).set(result)}
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    })
                }
            }

        }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)
            viewModel = ViewModelProvider(this).get(PreferenceViewModel::class.java)
            // TODO: Use the ViewModel
        }
    }
}
