package com.example.task71
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class CreateAdvertActivity: AppCompatActivity() {
    private lateinit var databaseHelper: ItemDatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_a_new_advert)

        // Initialize the database helper
        databaseHelper = ItemDatabaseHelper(this)

        // Set up submit button click listener
        val submitButton = findViewById<Button>(R.id.btn_submit)
        submitButton.setOnClickListener {
            // Get reference to the radio group
            val radioGroup = findViewById<RadioGroup>(R.id.radio_group_post_type)

            // Get the ID of the checked radio button
            val postType = findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()
            val name = findViewById<EditText>(R.id.edit_text_name).text.toString()
            val phone = findViewById<EditText>(R.id.edit_text_phone).text.toString()
            val description = findViewById<EditText>(R.id.edit_text_description).text.toString()
            val date = findViewById<EditText>(R.id.edit_text_date).text.toString()
            val location = findViewById<EditText>(R.id.edit_text_location).text.toString()

            // Validate form
            if (name.isEmpty() || phone.isEmpty() || description.isEmpty() || date.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Save post to database
                databaseHelper.addItem(Item(postType, name, phone, description, date, location))

                // Show success message
                Toast.makeText(this, "Post submitted successfully", Toast.LENGTH_SHORT).show()

                // Clear the form fields
                findViewById<EditText>(R.id.edit_text_name).setText("")
                findViewById<EditText>(R.id.edit_text_phone).setText("")
                findViewById<EditText>(R.id.edit_text_description).setText("")
                findViewById<EditText>(R.id.edit_text_date).setText("")
                findViewById<EditText>(R.id.edit_text_location).setText("")

                // Return to main activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        // Close the database helper
        databaseHelper.close()
    }
}