package com.example.suitmediaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        supportActionBar?.hide()
        val checkPalindromButton = findViewById<Button>(R.id.checkPalindrom_button)
        checkPalindromButton.setOnClickListener {
            val palindromInput = findViewById<TextInputEditText>(R.id.palindrom_input)
            val result = isPalindrome(palindromInput.text.toString())
            var toastText = "not palindrome"
            if (result) toastText = "isPalindrome"
            Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show()
        }

        val pageTwoButton = findViewById<Button>(R.id.pageTwo_button)
        pageTwoButton.setOnClickListener {
            val nameInput = findViewById<TextInputEditText>(R.id.name_input)
            val intent = Intent(this, PageTwo::class.java)
            intent.putExtra("name", nameInput.text.toString())
            startActivity(intent)
        }
    }


    fun isPalindrome(s: String) : Boolean {
        Log.d("test",s)
        val filteredS = s.lowercase().filter { it.isLetter() || it.isDigit() }
        return filteredS == filteredS.reversed()

    }


}



