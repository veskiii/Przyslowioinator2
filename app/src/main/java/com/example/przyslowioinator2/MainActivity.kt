package com.example.przyslowioinator2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavOptions
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val options = NavOptions.Builder()
            .setLaunchSingleTop(true)
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.slide_in_left)
            .setPopExitAnim(R.anim.slide_out_right)
            .setPopUpTo(navController.graph.startDestinationId, false)
            .build()

        bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.randomPrzyslowieFragment -> {
                    navController.navigate(R.id.randomPrzyslowieFragment,null, options)
                }
                R.id.listContainerFragment -> {
                    navController.navigate(R.id.listContainerFragment,null, options)
                }
            }
            true
        }

        bottomNavigationView.setOnItemReselectedListener { _ ->
            return@setOnItemReselectedListener
        }
    }
}