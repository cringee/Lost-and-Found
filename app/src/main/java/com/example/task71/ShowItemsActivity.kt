package com.example.task71
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class ShowItemsActivity : AppCompatActivity() {

    private lateinit var itemsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_all_lost_and_found_items)

        // Get reference to the items container
        itemsContainer = findViewById(R.id.items_container)

        // Populate the items container with sample data
        val items = listOf(
            Item("Lost", "IPhone", "999999999", "iPhone 12", "2021-10-01", "Deakin Library"),
            Item("Found", "Wallet", "8888888", "Wallet", "2021-09-30", "Burwood Campus")
        )
        populateItems(items)
    }

    private fun populateItems(items: List<Item>) {
        // Clear the items container
        itemsContainer.removeAllViews()

        // Get all items from the database
        val dbHelper = ItemDatabaseHelper(this)
        val items = dbHelper.getAllItems()

        // Add each item to the container as a clickable TextView
        for (item in items) {
            val itemView = TextView(this)
            itemView.text = "${item.postType}: ${item.name}"
            itemView.textSize = 18f
            itemView.setPadding(16, 16, 16, 16)
            itemView.setOnClickListener {
                // Launch the RemoveItemActivity with the selected item
                val intent = Intent(this, RemoveItemActivity::class.java)
                intent.putExtra("item", item)
                startActivity(intent)
            }
            itemsContainer.addView(itemView)
        }
    }
}