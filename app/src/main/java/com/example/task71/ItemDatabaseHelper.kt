package com.example.task71

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.Serializable

class ItemDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "item_database"
        private const val TABLE_NAME = "items"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_POST_TYPE = "post_type"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_PHONE = "phone"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_DATE = "date"
        private const val COLUMN_LOCATION = "location"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_POST_TYPE TEXT, $COLUMN_NAME TEXT, $COLUMN_PHONE TEXT, $COLUMN_DESCRIPTION TEXT, $COLUMN_DATE TEXT, $COLUMN_LOCATION TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addItem(item: Item): Long {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_POST_TYPE, item.postType)
            put(COLUMN_NAME, item.name)
            put(COLUMN_PHONE, item.phone)
            put(COLUMN_DESCRIPTION, item.description)
            put(COLUMN_DATE, item.date)
            put(COLUMN_LOCATION, item.location)
        }
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }
    fun getAllItems(): List<Item> {
        val items = mutableListOf<Item>()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val idIndex = cursor.getColumnIndex(COLUMN_ID)
                val postTypeIndex = cursor.getColumnIndex(COLUMN_POST_TYPE)
                val nameIndex = cursor.getColumnIndex(COLUMN_NAME)
                val phoneIndex = cursor.getColumnIndex(COLUMN_PHONE)
                val descriptionIndex = cursor.getColumnIndex(COLUMN_DESCRIPTION)
                val dateIndex = cursor.getColumnIndex(COLUMN_DATE)
                val locationIndex = cursor.getColumnIndex(COLUMN_LOCATION)
                val id = if (idIndex >= 0) cursor.getLong(idIndex) else -1
                val postType = if (postTypeIndex >= 0) cursor.getString(postTypeIndex) else ""
                val name = if (nameIndex >= 0) cursor.getString(nameIndex) else ""
                val phone = if (phoneIndex >= 0) cursor.getString(phoneIndex) else ""
                val description = if (descriptionIndex >= 0) cursor.getString(descriptionIndex) else ""
                val date = if (dateIndex >= 0) cursor.getString(dateIndex) else ""
                val location = if (locationIndex >= 0) cursor.getString(locationIndex) else ""
                val item = Item(postType, name, phone, description, date, location, id)
                items.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return items
    }

    fun removeItem(item: Item) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(item.id.toString()))
        db.close()
    }
}

data class Item(
    val postType: String,
    val name: String,
    val phone: String,
    val description: String,
    val date: String,
    val location: String,
    val id: Long = -1L
) : Serializable
