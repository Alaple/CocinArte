package com.bifrost.cocinarte.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.bifrost.cocinarte.R

class LoginActivity : AppCompatActivity() {

    private lateinit var navHostFragment : NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Navigation
        navHostFragment = supportFragmentManager.findFragmentById(R.id.logInContainerView) as NavHostFragment
    }
}