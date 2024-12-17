package com.dicoding.moodiometri.signup

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
import com.dicoding.moodiometri.databinding.ActivitySignUpBinding
import com.dicoding.moodiometri.fragment.survey.SurveyFragment
import com.dicoding.moodiometri.signin.SignInActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
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
                // Navigate to SignInActivity
                val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
                startActivity(intent)
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
        binding.signUpButton.setOnClickListener {
            val username = binding.edRegisterUsername.text.toString().trim()
            val password = binding.edRegisterPassword.text.toString().trim()
            val password2 = binding.edRegisterRepassword.text.toString().trim()

            when {
                username.isEmpty() -> {
                    binding.edRegisterUsername.error = getString(R.string.username)
                }
                password.isEmpty() -> {
                    binding.edRegisterPassword.error = getString(R.string.password)
                }
                password.length < 8 -> {
                    binding.edRegisterPassword.error = getString(R.string.password_minimum_length)
                }
                password2.isEmpty() -> {
                    binding.edRegisterRepassword.error = getString(R.string.re_password)
                }
                password2.length < 8 -> {
                    binding.edRegisterRepassword.error = getString(R.string.password_minimum_length)
                }
                password != password2 -> { // Check if passwords match
                    binding.edRegisterRepassword.error = getString(R.string.not)
                }
                else -> {
                    // Replace SignUpActivity with SurveyFragment
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, SurveyFragment()) // Make sure fragment_container exists in your layout
                        .addToBackStack(null) // Optionally add the transaction to the back stack
                        .commit()
                }
            }
        }
    }
}
