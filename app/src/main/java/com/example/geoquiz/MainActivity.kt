package com.example.geoquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val REQUEST_CODE_CHEAT = 0

class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this)[QuizViewModel::class.java]
    }
    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        if(userAnswer==correctAnswer) {
            /*Toast.makeText(this, "OK",
                Toast.LENGTH_SHORT)
                .show()*/
            quizViewModel.correctAnswers++
        } /*else {
            Toast.makeText(this, "NET",
                Toast.LENGTH_SHORT)
                .show()
        }*/
        val messageResId = when{
            quizViewModel.isCheater -> getString(R.string.judgment_toast)
            userAnswer == correctAnswer -> "Good"
            else -> "Not good"
        }

        Toast.makeText(this, messageResId,Toast.LENGTH_SHORT).show()

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
        val btnCht = findViewById<ImageButton>(R.id.btnImgCheat)
        val questionTextResId = quizViewModel.currentQuestionText
        textQ.setText(questionTextResId)
        val textCorrectNum = findViewById<TextView>(R.id.textCorrectNumber)
        textCorrectNum.setText(quizViewModel.correctAnswers.toString())

        btnYes.isVisible = quizViewModel.checkVisYes
        btnNo.isVisible = quizViewModel.checkVisNo
        btnNext.isVisible = quizViewModel.checkVisNext

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
                Toast.makeText(this, "Угадал верно: ${quizViewModel.correctAnswers}\nСчитерил: ${quizViewModel.timesCheats}",
                    Toast.LENGTH_SHORT)
                    .show()
                quizViewModel.checkVisYes = false
                btnYes.isVisible = quizViewModel.checkVisYes
                quizViewModel.checkVisNo = false
                btnNo.isVisible = quizViewModel.checkVisNo
            }
        }

        btnNo.setOnClickListener {
            checkAnswer(false)
            quizViewModel.checkVisYes  = false
            quizViewModel.checkVisNo = false
            quizViewModel.checkVisNext = true
            btnYes.isVisible = quizViewModel.checkVisYes
            btnNo.isVisible = quizViewModel.checkVisNo
            btnNext.isVisible = quizViewModel.checkVisNext
            textCorrectNum.setText(quizViewModel.correctAnswers.toString())
        }

        btnYes.setOnClickListener {
            checkAnswer(true)
            quizViewModel.checkVisYes = false
            quizViewModel.checkVisNo = false
            quizViewModel.checkVisNext = true
            btnYes.isVisible = quizViewModel.checkVisYes
            btnNo.isVisible = quizViewModel.checkVisNo
            btnNext.isVisible = quizViewModel.checkVisNext
            textCorrectNum.setText(quizViewModel.correctAnswers.toString())
        }

        btnCht.setOnClickListener {
            if (quizViewModel.timesCheats == 3) {
                Toast.makeText(this, "Достаточно жульничества!", Toast.LENGTH_SHORT).show()
            }
            else {
                val answerIsTrue = quizViewModel.currentQuestionAnswer
                val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
                startActivityForResult(intent, REQUEST_CODE_CHEAT)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK){
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT){
                quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN,false) ?: false
            quizViewModel.timesCheats++
        }
    }
}

