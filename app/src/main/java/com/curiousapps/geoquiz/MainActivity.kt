package com.curiousapps.geoquiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.curiousapps.geoquiz.databinding.ActivityMainBinding

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        //Handle result
        if (result.resultCode == Activity.RESULT_OK){
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANS_SHOWN, false) ?: false
        }

    }

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

            cheatButton.setOnClickListener{
                //Start Cheat Activity
                //val cheatIntent = Intent(this@MainActivity, CheatActivity::class.java)
                val answerIsTrue = quizViewModel.currentQuestionAnswer
                val cheatIntent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
                //startActivity(cheatIntent)
                cheatLauncher.launch(cheatIntent)
            }
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
//        val messageId = if (userAnswer == correctAnswer) {
//            R.string.correct_toast
//        } else {
//            R.string.incorrect_toast
//        }
        val messageId = when{
            quizViewModel.isCheater -> R.string.judgement_toast
            userAnswer == correctAnswer -> R.string.correct_toast
            else -> R.string.incorrect_toast
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