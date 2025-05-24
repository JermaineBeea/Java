package com.example.simpleinputapp

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    
    private lateinit var inputEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var displayTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences
    
    companion object {
        private const val PREF_NAME = "SimpleInputAppPrefs"
        private const val KEY_STORED_TEXT = "stored_text"
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize SharedPreferences for data storage
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE)
        
        // Initialize UI components
        initializeViews()
        
        // Set up button click listeners
        setupClickListeners()
        
        // Load any previously stored data
        loadStoredData()
    }
    
    private fun initializeViews() {
        inputEditText = findViewById(R.id.inputEditText)
        saveButton = findViewById(R.id.saveButton)
        loadButton = findViewById(R.id.loadButton)
        displayTextView = findViewById(R.id.displayTextView)
    }
    
    private fun setupClickListeners() {
        saveButton.setOnClickListener { saveInputData() }
        loadButton.setOnClickListener { loadStoredData() }
    }
    
    private fun saveInputData() {
        val inputText = inputEditText.text.toString().trim()
        
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter some text first!", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Save the text to SharedPreferences
        with(sharedPreferences.edit()) {
            putString(KEY_STORED_TEXT, inputText)
            apply()
        }
        
        // Show confirmation message
        Toast.makeText(this, "Text saved successfully!", Toast.LENGTH_SHORT).show()
        
        // Clear the input field
        inputEditText.setText("")
        
        // Update the display
        loadStoredData()
    }
    
    private fun loadStoredData() {
        val storedText = sharedPreferences.getString(KEY_STORED_TEXT, "No data stored yet")
        displayTextView.text = "Stored Text: $storedText"
    }
}