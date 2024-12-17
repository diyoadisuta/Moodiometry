package com.dicoding.moodiometri

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dicoding.moodiometri.databinding.ActivityMainBinding
import com.dicoding.moodiometri.fragment.history.HistoryFragment
import com.dicoding.moodiometri.fragment.home.HomeFragment
import com.dicoding.moodiometri.fragment.profile.ProfileFragment
import com.dicoding.moodiometri.fragment.survey.SurveyFragment
import com.dicoding.moodiometri.fragment.survey.SurveyPreferences

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var surveyPreferences: SurveyPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set default fragment
        surveyPreferences = SurveyPreferences(this)

        if (savedInstanceState == null) {
            if (surveyPreferences.isSurveyCompleted()) {
                replaceFragment(HomeFragment())
            } else {
                replaceFragment(SurveyFragment())
            }
        }

        // Handle navigation item selection
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.navigation_history -> {
                    replaceFragment(HistoryFragment())
                    true
                }
                R.id.navigation_profile -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun hideBottomNavigation() {
        binding.bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigation() {
        binding.bottomNavigation.visibility = View.VISIBLE
    }
}
