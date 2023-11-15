package com.curiousapps.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.curiousapps.geoquiz.databinding.ActivityCheatBinding

private const val EXTRA_ANS_IS_TRUE = "com.curiousapps.geoquiz.answer_is_true"
const val EXTRA_ANS_SHOWN = "com.curiousapps.geoquiz.answer_shown"
class CheatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheatBinding
    private var answerIsTrue = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewsAndExtras()
    }

    private fun initViewsAndExtras(){
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANS_IS_TRUE, false)
        binding.apply {
            showAnswerButton.setOnClickListener {
                val answerText = when{
                       answerIsTrue -> R.string.true_button_text
                       else -> R.string.false_button_text
                    }
                answerTextView.setText(answerText)
                setAnswerShownResult(true)
            }
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent().apply {
        putExtra(EXTRA_ANS_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data)
    }

    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent{
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANS_IS_TRUE, answerIsTrue)
            }
        }
    }

    override fun finishActivity(requestCode: Int) {
        super.finishActivity(requestCode)
    }
}