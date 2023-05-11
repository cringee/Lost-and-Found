package com.example.task71

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Set up click listeners for buttons
        val createAdvertButton = findViewById<View>(R.id.btn_create_advert)
        createAdvertButton.setOnClickListener {
            val intent = Intent(this, CreateAdvertActivity::class.java)
            startActivity(intent)
        }

        val showItemsButton = findViewById<View>(R.id.btn_show_items)
        showItemsButton.setOnClickListener {
            val intent = Intent(this, ShowItemsActivity::class.java)
            startActivity(intent)
        }
    }
}