package com.conaguhee.binlist.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.conaughee.binlist.R
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = (supportFragmentManager.findFragmentById(R.id.nav_main_fragment) as NavHostFragment).navController
        findViewById<TabLayout>(R.id.toolbarTabs).addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    navController.navigate(
                        when (tab.position) {
                            0 -> R.id.navigation_details
                            1 -> R.id.navigation_history
                            else -> throw IllegalArgumentException("Unknown tab destination!")
                        }
                    )
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
                override fun onTabReselected(tab: TabLayout.Tab?) = Unit
            }
        )
    }
}