package com.example.twoinoneapp


import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.twoinoneapp.databinding.NumberMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class GuessNumber : AppCompatActivity() {


    private lateinit var rvMain: RecyclerView

    private lateinit var list: ArrayList<String>

    private lateinit var answer: String
    var guesses = 3


    private lateinit var userInput: EditText
    private lateinit var btnGes: Button
    private var secretNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContentView(R.layout.number_main)

        userInput = findViewById(R.id.etInput)
        btnGes = findViewById(R.id.BtnGuess)
        rvMain = findViewById(R.id.rvMain)

        list = arrayListOf()

        secretNumber = Random.nextInt(11)

        rvMain.adapter = NumAdapter(list)
        rvMain.layoutManager = LinearLayoutManager(this)

        //binding.tvGame.text = " New Text"

        btnGes.setOnClickListener {
            guessNum()
            rvMain.adapter!!.notifyDataSetChanged()
            //val guess = userInput.text.toString()
            //list.add("secret number is $secretNumber and user input $guess")
        }

        list.add("secret number is $secretNumber")


    }

    private fun guessNum() {
        val guess = userInput.text.toString()
        list.add("secret number is $secretNumber")

        userInput.text.clear()

        if (guess.isNotEmpty()) {
            if (guesses > 0) {
                if (guess.toInt() == secretNumber) {
                    answer = "you Win! $guess"
                    list.add(answer)
                    disableInput()
                    alertDialog("play again")
                } else {
                    guesses--
                    answer = "Incorrect! you have $guesses left, try again"
                    list.add(answer)
                }

            } else {
                answer = "Game Over! you have $guesses left"

                list.add(answer)
                disableInput()
                alertDialog("play again")
            }
        }
    }
    /// fun to take input and compare


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


    ///fun to play again

    private fun alertDialog(title: String) {
        val dialogAlert = AlertDialog.Builder(this)
        dialogAlert.setMessage(title)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { _, _ ->
                this.recreate()
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
        val alert = dialogAlert.create()
        alert.setTitle("Game Over")
        alert.show()
    }

    private fun disableInput() {
        userInput.isClickable = false
        userInput.isEnabled = false
        btnGes.isEnabled = false
        btnGes.isClickable = false
    }
}

