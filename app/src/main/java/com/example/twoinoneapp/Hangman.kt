package com.example.twoinoneapp


import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

import kotlin.collections.ArrayList

class Hangman : AppCompatActivity() {
    private lateinit var rvMain: RecyclerView
    private lateinit var list: ArrayList<String>
    private lateinit var input: EditText
    private lateinit var tvPhrase: TextView
    private lateinit var tvLetter: TextView
    private lateinit var btn: Button
    private lateinit var tvHigh: TextView
    private lateinit var tvScore: TextView

    private val answer = "word"
    private var mapAnswer = mutableMapOf<Int, Char>()
    private var myAnswer = ""
    private var letterGuessed = ""
    private var count = 10
    private var guessPhrase = true

    private var score = 0
    private var highScore = 0



    //Todo: try to fix the char check its not working properly

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hang_main)

        rvMain = findViewById(R.id.rvMain)
        input = findViewById(R.id.etInput)
        tvPhrase = findViewById(R.id.tvphrase)
        tvLetter = findViewById(R.id.tvLetter)
        btn = findViewById(R.id.btnGuess)
        tvHigh = findViewById(R.id.tvHighScore)
        tvScore = findViewById(R.id.tvScore)

        list = arrayListOf()

        rvMain.adapter = WordAdapter(list)
        rvMain.layoutManager = LinearLayoutManager(this)


        loadData()


        btn.setOnClickListener {
            addMsg()
        }

        //turn phrase to ***
        for (i in answer.indices) {
            if (answer[i] == ' ') {
                mapAnswer[i] = ' '
                myAnswer += ' '
            } else {
                mapAnswer[i] = '*'
                myAnswer += '*'

            }
        }
        updateText()
        highScore += score

        tvHigh.text = "HighScore: $highScore"
        tvScore.text ="Score: $score"


    }

    private fun addMsg() {
        val userGuess = input.text.toString()

        if (guessPhrase) {
            if (userGuess == answer) {
                Toast.makeText(this, "you Win", Toast.LENGTH_LONG).show()
                updateText()
                alert("You Win! Congrats")
                score ++
                highScore += score
                tvScore.text ="Score: $score"
                tvHigh.text ="HighScore: $highScore"
                saveData()

            } else {
                list.add("Wrong guess: $userGuess")
                count --
                guessPhrase = false
                updateText()
            }
        } else {
            if (userGuess.isNotEmpty() && userGuess.length == 1) {
                myAnswer = ""
                guessPhrase = true
                checkLetters(userGuess[0])

                //check letter fun
            } else {
                Toast.makeText(this, "Please, Enter letters Only", Toast.LENGTH_LONG).show()
            }
        }
        input.text.clear()
        input.clearFocus()
        rvMain.adapter!!.notifyDataSetChanged()
    }

    private fun updateText() {
        tvPhrase.text = "Phrase: $myAnswer"
        tvLetter.text = "Guessed Letters: $letterGuessed"

        if (guessPhrase) {
            input.hint = " guess full phrase"
        } else {
            input.hint = " guess letters of the  phrase"
        }
    }

    private fun checkLetters(guessLetter: Char) {
        var found = 0

        for (i in answer.indices) {
            if (answer[i] == guessLetter) {
                mapAnswer[i] = guessLetter


                found++
            }
        }
        for (i in mapAnswer) {
            myAnswer += mapAnswer[i.key]
        }
        if (myAnswer == answer) {
            Toast.makeText(this, "you Win", Toast.LENGTH_LONG).show()
            score ++
            updateScore()

        }
        if (letterGuessed.isEmpty()) {
            letterGuessed += guessLetter

        } else{
            letterGuessed+= ", $guessLetter"
        }
        if (found>0){
            list.add("Found $found letters :  $letterGuessed")
        }else{
            list.add("No, Letters were found yet!")
        }
        count --
        rvMain.scrollToPosition(list.size-1)

    }


    ///score
    private fun updateScore(){
        if(score >= highScore){
            highScore = score
            saveData()
        }
        tvHigh.text = "HighScore: $highScore"
        tvScore.text = "Score: $score"
    }


    private fun alert(title: String){
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage(title)

            .setCancelable(false)

            .setPositiveButton("Yes", DialogInterface.OnClickListener{ dialog , id -> this.recreate()
            })

            .setNegativeButton("No", DialogInterface.OnClickListener{
                    dialog, id -> dialog.cancel()
            })

        val alertTest = dialogBuilder.create()
        alertTest.setTitle("Game Over")
        alertTest.show()

    }


    private fun saveData(){

        val sharedPreferences: SharedPreferences = getSharedPreferences("highScore", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.apply {
            putInt("INT_KEY", highScore)
        }.apply()
    }

    private fun loadData(){
        val sharedPreferences: SharedPreferences = getSharedPreferences("highScore", Context.MODE_PRIVATE)
        val savedScore = sharedPreferences.getInt("INT_KEY", 0)
        if (savedScore != null) {
            highScore = savedScore
            tvHigh.text = "High Score: $savedScore"
        }else{
            highScore = 0
            tvScore.text = "HighScore: 0"
        }

    }

    /////main menu created/////////

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.mi_home -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Snackbar.make(rvMain, "Home Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_other -> {
                val intent = Intent(this, Hangman::class.java)
                startActivity(intent)
                Snackbar.make(rvMain, "Hangman Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_again -> {
                val intent = Intent(this, GuessNumber::class.java)
                startActivity(intent)
                Snackbar.make(rvMain, "Number Guesser Click", Snackbar.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /////main menu end/////////

}


