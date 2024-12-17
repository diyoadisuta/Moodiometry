package com.dicoding.moodiometri.fragment.survey

import android.content.Context
import android.content.SharedPreferences

class SurveyPreferences(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "MoodiometriPrefs"
        private const val KEY_SURVEY_COMPLETED = "survey_completed"
    }

    fun saveAnswer(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getAnswer(key: String): String? {
        return preferences.getString(key, null)
    }

    fun isSurveyCompleted(): Boolean {
        return preferences.getBoolean(KEY_SURVEY_COMPLETED, false)
    }

    fun setSurveyCompleted(completed: Boolean) {
        preferences.edit().putBoolean(KEY_SURVEY_COMPLETED, completed).apply()
    }
}
