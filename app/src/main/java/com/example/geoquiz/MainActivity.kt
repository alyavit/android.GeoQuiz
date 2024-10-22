package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

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

        btnNext.setOnClickListener {
            currentIndex = (currentIndex + 1) %
                    questionBank.size
            val questionTextResId = questionBank[currentIndex].textResId
            textQ.setText(questionTextResId)
        }

        btnNo.setOnClickListener {
            checkAnswer(false)
        }

        btnYes.setOnClickListener {
            checkAnswer(true)
        }

    }
}