package com.example.suitmediaapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class PageTwo : AppCompatActivity() {

    var username = "Selected User Name"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_two)
        supportActionBar?.hide()
        var name = ""

        val pref = this.getSharedPreferences("myPrefs", MODE_PRIVATE)
        if(intent.extras != null){
            name = intent.extras!!.getString("name")!!
        }

        if (pref.getString("name", "")?.isEmpty()!!) {
            pref.edit().putString("name", name).apply()
        }else{
            name = pref.getString("name", "")!!
        }
        if(!pref.getString("username", "")?.isEmpty()!!) {
            username = pref.getString("username", "")!!
        }

        val pageOneName = findViewById<TextView>(R.id.pageOneName_text)
        pageOneName.text = name

        val pageTreeButton = findViewById<Button>(R.id.chooseUser_button)
        pageTreeButton.setOnClickListener {
            startActivity(Intent(this, PageThree::class.java))
        }

        val userNameText = findViewById<TextView>(R.id.selectedUser_text)
        userNameText.text = username
//        supportActionBar?.title = "Second Screen"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        val pref = this.getSharedPreferences("myPrefs", MODE_PRIVATE)
        if(!pref.getString("username", "")?.isEmpty()!!) {
            username = pref.getString("username", "")!!
            val userNameText = findViewById<TextView>(R.id.selectedUser_text)
            userNameText.text = username
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.getSharedPreferences("myPrefs", MODE_PRIVATE).edit().clear().commit()
    }
//    override fun onContextItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//        }
//        return super.onContextItemSelected(item)
//    }
}