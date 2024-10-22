package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val questionBank = listOf(
        Question(R.string.question_first,true),
        Question(R.string.question_second,false),
        Question(R.string.question_third,false),
        Question(R.string.question_fourth,false),
        Question(R.string.question_fivth,true),
        Question(R.string.question_sixth,true))
    private var currentIndex = 0
    private var correctAnswers: Int = 0


    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        if(userAnswer==correctAnswer) {
            Toast.makeText(this, "OK",
                Toast.LENGTH_SHORT)
                .show()
            correctAnswers++
        } else {
            Toast.makeText(this, "NET",
                Toast.LENGTH_SHORT)
                .show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textQ = findViewById<TextView>(R.id.textQuest)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnYes = findViewById<Button>(R.id.btnYes)
        val btnNo = findViewById<Button>(R.id.btnNo)
        val questionTextResId = questionBank[currentIndex].textResId
        textQ.setText(questionTextResId)
        val textCorrectNum = findViewById<TextView>(R.id.textCorrectNumber)

        btnNext.setOnClickListener {
            currentIndex = (currentIndex + 1)
            val questionTextResId = questionBank[currentIndex].textResId
            textQ.setText(questionTextResId)
            btnYes.isVisible = true
            btnNo.isVisible = true
            btnNext.isVisible = false
            if (currentIndex >= questionBank.size-1){
                Toast.makeText(this, "Molodecs, ты угадал верно: $correctAnswers",
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
            textCorrectNum.setText(correctAnswers.toString())
        }

        btnYes.setOnClickListener {
            checkAnswer(true)
            btnYes.isVisible = false
            btnNo.isVisible = false
            btnNext.isVisible = true
            textCorrectNum.setText(correctAnswers.toString())
        }

    }
}