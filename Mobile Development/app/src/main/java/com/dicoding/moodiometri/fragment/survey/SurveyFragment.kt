package com.dicoding.moodiometri.fragment.survey

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dicoding.moodiometri.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class SurveyFragment : Fragment() {

    private lateinit var progressBar: ProgressBar
    private lateinit var questionText: TextView
    private lateinit var timePicker: TimePicker
    private lateinit var radioGroup: RadioGroup
    private lateinit var continueButton: Button

    private lateinit var surveyPreferences: SurveyPreferences

    private var currentQuestionIndex = 0
    private val questions = listOf(
        Question("sleep_time", "What time do you usually go to sleep?", QuestionType.TIME_PICKER),
        Question("wake_time", "What time do you usually wake up?", QuestionType.TIME_PICKER),
        Question(
            "sleep_duration",
            "How many hours do you sleep on average each day?",
            QuestionType.RADIO,
            listOf("4-5 h", "6-7 h", "8-9 h", "10 h or more")
        ),
        Question(
            "call_duration",
            "How much time do you spend on phone calls each day?",
            QuestionType.RADIO,
            listOf("Less than 10 minutes", "10-30 m", "30-60 m", "More than 1 h")
        ),
        Question(
            "screen_on_time",
            "How much screen time do you have on your phone each day?",
            QuestionType.RADIO,
            listOf("Less than 3 h", "3-5 h", "5-8 h", "More than 8 h")
        )
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_survey, container, false)

        progressBar = view.findViewById(R.id.progressBar)
        questionText = view.findViewById(R.id.questionText)
        timePicker = view.findViewById(R.id.timePicker)
        radioGroup = view.findViewById(R.id.radioGroup)
        continueButton = view.findViewById(R.id.btnContinue)

        surveyPreferences = SurveyPreferences(requireContext())

        setupQuestion()

        continueButton.setOnClickListener {
            saveAnswer()
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                setupQuestion()
            } else {
                completeSurvey()
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.GONE
    }

    override fun onPause() {
        super.onPause()
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.VISIBLE
    }

    private fun setupQuestion() {
        val question = questions[currentQuestionIndex]

        progressBar.progress = ((currentQuestionIndex + 1) * 100) / questions.size
        questionText.text = question.text

        timePicker.visibility = View.GONE
        radioGroup.visibility = View.GONE
        radioGroup.removeAllViews()

        when (question.type) {
            QuestionType.TIME_PICKER -> {
                timePicker.visibility = View.VISIBLE
            }
            QuestionType.RADIO -> {
                radioGroup.visibility = View.VISIBLE
                question.options?.forEach { option ->
                    val radioButton = RadioButton(requireContext())
                    radioButton.text = option
                    radioGroup.addView(radioButton)
                }
            }
        }
    }

    private fun saveAnswer() {
        val question = questions[currentQuestionIndex]
        when (question.type) {
            QuestionType.TIME_PICKER -> {
                val hour = timePicker.hour
                val minute = timePicker.minute
                val answer = "$hour:$minute"
                surveyPreferences.saveAnswer(question.key, answer)
            }
            QuestionType.RADIO -> {
                val selectedRadioButtonId = radioGroup.checkedRadioButtonId
                if (selectedRadioButtonId != -1) {
                    val selectedRadioButton = radioGroup.findViewById<RadioButton>(selectedRadioButtonId)
                    val answer = selectedRadioButton.text.toString()
                    surveyPreferences.saveAnswer(question.key, answer)
                }
            }
        }
    }

    private fun completeSurvey() {
        surveyPreferences.setSurveyCompleted(true)
        Toast.makeText(requireContext(), "Survey Completed!", Toast.LENGTH_SHORT).show()
        // Tambahkan navigasi atau aksi lainnya setelah survei selesai
    }

    data class Question(val key: String, val text: String, val type: QuestionType, val options: List<String>? = null)
    enum class QuestionType { TIME_PICKER, RADIO }
}
