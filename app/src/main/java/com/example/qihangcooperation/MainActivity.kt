package com.example.qihangcooperation

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.qihangcooperation.base.BaseActivity
import com.example.qihangcooperation.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun onCreate() {
        // init bottom navigation
        val navHostFragment = (supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment?)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navHostFragment?.navController?.let {
            setupWithNavController(bottomNavigationView, it)
        }
    }
}