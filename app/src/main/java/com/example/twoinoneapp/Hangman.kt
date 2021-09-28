package com.example.twoinoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class Hangman : AppCompatActivity() {
    private lateinit var clHM : ConstraintLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clHM = findViewById(R.id.clHM)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_home ->{
                Snackbar.make(clHM, "Home Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_hangman ->{
                Snackbar.make(clHM, "Hangman Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_num_ges ->{
                Snackbar.make(clHM, "Number Guesser Click", Snackbar.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}