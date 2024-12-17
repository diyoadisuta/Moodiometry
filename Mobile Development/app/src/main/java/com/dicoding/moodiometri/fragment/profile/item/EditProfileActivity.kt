package com.dicoding.moodiometri.fragment.profile.item

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dicoding.moodiometri.R

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        // Set Toolbar sebagai ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Aktifkan tombol back di Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Tombol Save
        val saveButton = findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener {
            // Tutup Activity dan kembali ke fragment sebelumnya
            finish()
        }

        // Tombol Back di Toolbar
        toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Tangani navigasi saat tombol back di Toolbar ditekan
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
