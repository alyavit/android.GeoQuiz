package com.example.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

    private const val EXTRA_ANSWER_IS_TRUE = "com.geoquiz.answerIsTrue"

    class CheatActivity : AppCompatActivity() {

        private lateinit var answerTextView: TextView
        private lateinit var showAnswerButton: Button

        private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cheat)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)

        showAnswerButton.setOnClickListener{
            val answerText = when {
                answerIsTrue -> "Ответ ДА"
                else -> "Ответ НЕТ"
            }
            answerTextView.setText(answerText)
        }
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue)
            }
        }
    }
}