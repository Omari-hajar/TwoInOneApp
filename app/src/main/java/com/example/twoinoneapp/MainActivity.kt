package com.example.twoinoneapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var clMain : ConstraintLayout
    private lateinit var btnHM: Button
    private lateinit var btnNG: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clMain =findViewById(R.id.clMain)

        btnHM = findViewById(R.id.btnHM)
        btnHM.setOnClickListener {
            val intent = Intent(this, Hangman::class.java)
            startActivity(intent)
        }

        btnNG = findViewById(R.id.btnNG)

        btnNG.setOnClickListener {
            val intent = Intent(this, GuessNumber::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mi_home ->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Snackbar.make(clMain, "Home Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_hangman ->{
                val intent = Intent(this, Hangman::class.java)
                startActivity(intent)
                Snackbar.make(clMain, "Hangman Click", Snackbar.LENGTH_LONG).show()
            }
            R.id.mi_num_ges ->{
                val intent = Intent(this, GuessNumber::class.java)
                startActivity(intent)
                Snackbar.make(clMain, "Number Guesser Click", Snackbar.LENGTH_LONG).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}