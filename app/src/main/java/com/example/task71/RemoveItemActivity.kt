package com.example.task71
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RemoveItemActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var locationTextView: TextView
    private lateinit var removeButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.remove_the_item)

        // Get references to the views
        nameTextView = findViewById(R.id.name_text_view)
        phoneTextView = findViewById(R.id.phone_text_view)
        descriptionTextView = findViewById(R.id.description_text_view)
        dateTextView = findViewById(R.id.date_text_view)
        locationTextView = findViewById(R.id.location_text_view)
        removeButton = findViewById(R.id.remove_button)

        // Get the selected item from the intent
        val item = intent.getSerializableExtra("item") as Item

        // Set the text of the views to the item's properties
        nameTextView.text = "Name: ${item.name}"
        phoneTextView.text = "Phone: ${item.phone}"
        descriptionTextView.text = "Description: ${item.description}"
        dateTextView.text = "Date: ${item.date}"
        locationTextView.text = "Location: ${item.location}"

        // Set the click listener for the remove button
        removeButton.setOnClickListener {
            // Remove the item from the database
            val dbHelper = ItemDatabaseHelper(this)
            dbHelper.removeItem(item)

            // Return to the ShowItemsActivity
            val intent = Intent(this, ShowItemsActivity::class.java)
            startActivity(intent)
        }
    }
}