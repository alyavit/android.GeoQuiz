package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textQ = findViewById<TextView>(R.id.textQuest)
        val btnNext = findViewById<Button>(R.id.btnNext)
        val btnYes = findViewById<Button>(R.id.btnYes)
        val btnNo = findViewById<Button>(R.id.btnNo)

        var correctAnswers: Int = 0
            //TODO: дочитать главы книги. дописать логику и работу приложения
    }

    fun correctToast() {
        Toast.makeText(
            this,
            "Верно!",
            Toast.LENGTH_SHORT)
            .show()
    }
    fun incorrectToast() {
        Toast.makeText(
            this,
            "Не верно!",
            Toast.LENGTH_SHORT)
            .show()
    }
}