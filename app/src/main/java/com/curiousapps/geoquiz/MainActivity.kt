package com.curiousapps.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.curiousapps.geoquiz.databinding.ActivityMainBinding
import com.curiousapps.geoquiz.model.Question

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.e(TAG, "onCreate(Bundle?)")
        val rootView = binding.root
        setContentView(rootView)

        initViewsAndClicks()
    }

    private fun initViewsAndClicks() {
        Log.w(TAG, "Got a ViewModel: $quizViewModel")
        binding.apply {

            trueButton.setOnClickListener {
                checkAnswer(true)
            }
            falseButton.setOnClickListener {
                checkAnswer(false)
            }
            nextButton.setOnClickListener {
                quizViewModel.moveToNext()
                updateQuestion()
            }
            previousButton.setOnClickListener {
                quizViewModel.moveToPrevious()
                updateQuestion()
            }
            updateQuestion()
        }
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        binding.infoText.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this@MainActivity, messageId, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart()")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop()")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause()")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy()")
    }
}