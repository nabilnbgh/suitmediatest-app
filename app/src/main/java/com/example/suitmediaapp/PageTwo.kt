package com.example.suitmediaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class PageTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_two)
        supportActionBar?.hide()
        var data = ""
        if(intent.extras != null){
            data = intent.extras!!.getString("name")!!
        }
        var pageOneName = findViewById<TextView>(R.id.pageOneName_text)
        pageOneName.text = data


//        supportActionBar?.title = "Second Screen"
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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