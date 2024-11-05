package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
    val questionBank = listOf(
    Question(R.string.question_first,true),
    Question(R.string.question_second,false),
    Question(R.string.question_third,false),
    Question(R.string.question_fourth,false),
    Question(R.string.question_fivth,true),
    Question(R.string.question_sixth,true))
    var currentIndex = 0
    var correctAnswers: Int = 0
    val currentQuestionAnswer: Boolean get()=questionBank[currentIndex].answer
    val currentQuestionText: Int get()= questionBank[currentIndex].textResId

    var checkVisYes = true
    var checkVisNo = true
    var checkVisNext = true
    var isCheater = false
    var timesCheats = 0

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}