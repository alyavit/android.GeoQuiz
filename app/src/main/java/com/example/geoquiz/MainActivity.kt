package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this)[QuizViewModel::class.java]
    }
    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if(userAnswer==correctAnswer) {
            Toast.makeText(this, "OK",
                Toast.LENGTH_SHORT)
                .show()
            quizViewModel.correctAnswers++
        } else {
            Toast.makeText(this, "NET",
                Toast.LENGTH_SHORT)
                .show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val provider: ViewModelProvider = ViewModelProviders.of(this)
        val quizViewModel = provider[QuizViewModel::class.java]

        val textQ = findViewById<TextView>(R.id.textQuest)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnYes = findViewById<Button>(R.id.btnYes)
        val btnNo = findViewById<Button>(R.id.btnNo)
        val questionTextResId = quizViewModel.currentQuestionText
        textQ.setText(questionTextResId)
        val textCorrectNum = findViewById<TextView>(R.id.textCorrectNumber)

        btnNext.setOnClickListener {
            quizViewModel.moveToNext()
            val questionTextResId = quizViewModel.currentQuestionText
            textQ.setText(questionTextResId)

            //Выставление видмости кнопок с соханением состояний
            quizViewModel.checkVisYes = true
            btnYes.isVisible = quizViewModel.checkVisYes
            quizViewModel.checkVisNo = true
            btnNo.isVisible = quizViewModel.checkVisNo
            quizViewModel.checkVisNext = false
            btnNext.isVisible = quizViewModel.checkVisNext

            if (quizViewModel.currentIndex >= quizViewModel.questionBank.size-1){
                Toast.makeText(this, "Molodecs, ты угадал верно: $quizViewModel.correctAnswers",
                    Toast.LENGTH_SHORT)
                    .show()
                btnYes.isVisible = false
                btnNo.isVisible = false
            }
        }

        btnNo.setOnClickListener {
            checkAnswer(false)
            btnYes.isVisible = false
            btnNo.isVisible = false
            btnNext.isVisible = true
            textCorrectNum.setText(quizViewModel.correctAnswers.toString())
        }

        btnYes.setOnClickListener {
            checkAnswer(true)
            btnYes.isVisible = false
            btnNo.isVisible = false
            btnNext.isVisible = true
            textCorrectNum.setText(quizViewModel.correctAnswers.toString())
        }

    }
}