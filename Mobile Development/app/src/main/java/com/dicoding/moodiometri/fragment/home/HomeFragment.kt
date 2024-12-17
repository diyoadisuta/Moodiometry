package com.dicoding.moodiometri.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.dicoding.moodiometri.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class HomeFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // URL API
        val apiUrl = "https://modelapi-841486537196.asia-southeast2.run.app/predict"

        // Data input dalam bentuk JSONArray
        val inputData = JSONArray()
        inputData.put(JSONArray(listOf(1.0, 2.0, 3.0)))

        // Membuat JSON body
        val jsonBody = JSONObject()
        try {
            jsonBody.put("data", inputData)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        // Membuat RequestQueue Volley
        val requestQueue: RequestQueue = Volley.newRequestQueue(requireContext())

        // Membuat request menggunakan JsonObjectRequest
        val request = JsonObjectRequest(
            Request.Method.POST,
            apiUrl,
            jsonBody,
            { response ->
                try {
                    val predictions: JSONArray = response.getJSONArray("predictions")
                    // Handle and display predictions
                    // Example: Update a TextView or other UI elements
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            { error ->
                error.printStackTrace()
            }
        )

        // Tambahkan request ke RequestQueue
        requestQueue.add(request)

        return view
    }
}
