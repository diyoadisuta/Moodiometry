package com.dicoding.moodiometri.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.dicoding.moodiometri.R
import com.dicoding.moodiometri.fragment.profile.item.EditPasswordActivity
import com.dicoding.moodiometri.fragment.profile.item.EditProfileActivity
import com.dicoding.moodiometri.fragment.profile.item.SubscriptionActivity

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editProfileImageView = view.findViewById<ImageView>(R.id.edit_profile)

        editProfileImageView.setOnClickListener {
            val intent = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}