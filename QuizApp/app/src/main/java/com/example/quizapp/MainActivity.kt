package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val questions = arrayOf(
        "What is the built-in database in Android Studio?",
        "What is the full form of APK in Android Development?",
        "In which year, first android was released by Google?"
    )
    private val options = arrayOf(
        arrayOf("MySQL", "SQLite", "Firebase"),
        arrayOf(
            "Application Programming Interface",
            "Android Programming Interface",
            "Android Package Information"
        ),
        arrayOf("2010", "2006", "2008")
    )
    private val correctAnswers = arrayOf(1, 0, 2)
    private var currentQuestionIndex = 0
    private var score = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayQuestions()
        binding.btnOne.setOnClickListener {
            checkAnswer(0)
        }
        binding.btnTwo.setOnClickListener {
            checkAnswer(1)
        }
        binding.btnThree.setOnClickListener {
            checkAnswer(2)
        }
        binding.btnRestart.setOnClickListener {
            restartQuiz()
        }
    }

    private fun correctButtonColors(buttenIndex: Int) {
        when (buttenIndex) {
            0 -> binding.btnOne.setBackgroundColor(Color.GREEN)
            1 -> binding.btnTwo.setBackgroundColor(Color.GREEN)
            2 -> binding.btnThree.setBackgroundColor(Color.GREEN)
        }
    }

    private fun wrongButtonColors(buttenIndex: Int) {
        when (buttenIndex) {
            0 -> binding.btnOne.setBackgroundColor(Color.RED)
            1 -> binding.btnTwo.setBackgroundColor(Color.RED)
            2 -> binding.btnThree.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColor() {
        binding.btnOne.setBackgroundColor(getColor(R.color.navy))
        binding.btnTwo.setBackgroundColor(getColor(R.color.navy))
        binding.btnThree.setBackgroundColor(getColor(R.color.navy))
    }

    private fun showResult() {
        Toast.makeText(this, "Your Score :$score out of ${questions.size}", Toast.LENGTH_LONG)
            .show()
        binding.btnRestart.isEnabled = true
    }

    private fun displayQuestions() {
        binding.tvQuestion.text = questions[currentQuestionIndex]
        binding.btnOne.text = options[currentQuestionIndex][0]
        binding.btnTwo.text = options[currentQuestionIndex][1]
        binding.btnThree.text = options[currentQuestionIndex][2]
        resetButtonColor()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex = correctAnswers[currentQuestionIndex]
        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColors(selectedAnswerIndex)
        } else {
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.tvQuestion.postDelayed({ displayQuestions() }, 1000)
        } else {
            showResult()
        }

    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestions()
        binding.btnRestart.isEnabled = false
    }
}