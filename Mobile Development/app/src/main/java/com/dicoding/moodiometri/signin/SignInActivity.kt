package com.dicoding.moodiometri.signin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.moodiometri.R
import com.dicoding.moodiometri.databinding.ActivitySignInBinding
import com.dicoding.moodiometri.fragment.survey.SurveyFragment
import com.dicoding.moodiometri.signup.SignUpActivity
import com.dicoding.moodiometri.splash.ScreenActivity

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize clickable text for sign-in link
        setupClickableText()

        // Set up action for sign-up button click
        setupAction()
    }

    private fun setupClickableText() {
        val text = "You have already account?  "
        val signUpText = "Sign In"
        val spannableString = SpannableString(text + signUpText)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(view: View) {
                // Replace SignUpActivity with SurveyFragment
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, SurveyFragment()) // Ensure fragment_container exists in your layout
                    .addToBackStack(null) // Optionally add to the back stack
                    .commit()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE // Change text color to blue
                ds.isUnderlineText = true // Add underline to the text
            }
        }

        // Set the span for the clickable text
        spannableString.setSpan(clickableSpan, text.length, text.length + signUpText.length, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        // Set the spannable string to the TextView and enable link movement
        binding.secTextView.text = spannableString
        binding.secTextView.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val username = binding.edLoginUsername.text.toString().trim()
            val password = binding.edLoginPassword.text.toString().trim()

            when {
                username.isEmpty() -> {
                    binding.edLoginUsername.error = getString(R.string.username)
                }
                password.isEmpty() -> {
                    binding.edLoginPassword.error = getString(R.string.password)
                }
                password.length < 8 -> {
                    binding.edLoginPassword.error = getString(R.string.password_minimum_length)
                }
                else -> {

                    // Navigate to SignInActivity after successful registration (or any other action)
                    val intent = Intent(this@SignInActivity, ScreenActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
