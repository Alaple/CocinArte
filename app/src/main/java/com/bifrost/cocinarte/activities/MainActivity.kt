package com.bifrost.cocinarte.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.bifrost.cocinarte.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment : NavHostFragment
    private lateinit var bottomNavView : BottomNavigationView

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Navigation
        navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContainerView) as NavHostFragment
        bottomNavView = findViewById(R.id.bottom_bar)
        NavigationUI.setupWithNavController(bottomNavView, navHostFragment.navController)

        // If user is logged in show buttons of the button bar
        showHideUserButtons()
    }

    private fun showHideUserButtons() {
        auth = FirebaseAuth.getInstance()
        Log.d("CURRENT USER", auth.currentUser?.email.toString())
        if (auth.currentUser != null) {
            bottomNavView.menu.findItem(R.id.userProfileFragment).isVisible = true
            bottomNavView.menu.findItem(R.id.favouritesFragment).isVisible = true
            bottomNavView.menu.findItem(R.id.preparedFragment).isVisible = true
        } else {
            bottomNavView.menu.findItem(R.id.userProfileFragment).isVisible = false
            bottomNavView.menu.findItem(R.id.favouritesFragment).isVisible = false
            bottomNavView.menu.findItem(R.id.preparedFragment).isVisible = false
        }
    }
}