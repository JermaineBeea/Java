package com.example.simpleinputapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    private EditText inputEditText;
    private Button saveButton;
    private Button loadButton;
    private TextView displayTextView;
    private SharedPreferences sharedPreferences;
    
    private static final String PREF_NAME = "SimpleInputAppPrefs";
    private static final String KEY_STORED_TEXT = "stored_text";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Initialize SharedPreferences for data storage
        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        
        // Initialize UI components
        initializeViews();
        
        // Set up button click listeners
        setupClickListeners();
        
        // Load any previously stored data
        loadStoredData();
    }
    
    private void initializeViews() {
        inputEditText = findViewById(R.id.inputEditText);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);
        displayTextView = findViewById(R.id.displayTextView);
    }
    
    private void setupClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveInputData();
            }
        });
        
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadStoredData();
            }
        });
    }
    
    private void saveInputData() {
        String inputText = inputEditText.getText().toString().trim();
        
        if (inputText.isEmpty()) {
            Toast.makeText(this, "Please enter some text first!", Toast.LENGTH_SHORT).show();
            return;
        }
        
        // Save the text to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_STORED_TEXT, inputText);
        editor.apply();
        
        // Show confirmation message
        Toast.makeText(this, "Text saved successfully!", Toast.LENGTH_SHORT).show();
        
        // Clear the input field
        inputEditText.setText("");
        
        // Update the display
        loadStoredData();
    }
    
    private void loadStoredData() {
        String storedText = sharedPreferences.getString(KEY_STORED_TEXT, "No data stored yet");
        displayTextView.setText("Stored Text: " + storedText);
    }
}