package com.example.appinsight.core

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.appinsight.R
import com.example.appinsight.utils.isEdgeToEdgeNeedsToBeHandled

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (isEdgeToEdgeNeedsToBeHandled()) {
            enableEdgeToEdge()
            window.isNavigationBarContrastEnforced = false
        }

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.appsListFragment)
        }
    }
}