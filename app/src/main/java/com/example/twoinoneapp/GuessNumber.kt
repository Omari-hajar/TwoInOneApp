package com.example.twoinoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class GuessNumber : AppCompatActivity() {
    private lateinit var clNG : ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clNG = findViewById(R.id.clNG)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_home ->{
                Snackbar.make(clNG, "Home Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_hangman ->{
                Snackbar.make(clNG, "Hangman Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_num_ges ->{
                Snackbar.make(clNG, "Number Guesser Click", Snackbar.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}