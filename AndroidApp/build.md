Great! Let's build your first Android application step by step — a simple app with:

* An input box where users can type anything.
* A button to save the typed information.
* A way to store that information (we’ll start with in-memory storage for simplicity).

We'll use **Android Studio** and **Kotlin** as the language (the current recommended setup by Google).

---

### ✅ Step 1: Install Android Studio

1. Download from: [https://developer.android.com/studio](https://developer.android.com/studio)
2. Install it and launch Android Studio.
3. Choose **“New Project”**.

---

### ✅ Step 2: Create a New Project

1. Choose **“Empty Activity”**.
2. Name: `SimpleInputApp`
3. Package name: `com.example.simpleinputapp` (or anything else)
4. Save location: Anywhere on your system.
5. Language: **Kotlin**
6. Minimum SDK: **API 21: Android 5.0 (Lollipop)**

Click **Finish**.

---

### ✅ Step 3: Design the UI (in `activity_main.xml`)

Open `res/layout/activity_main.xml` and replace its contents with:

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp">

    <EditText
        android:id="@+id/inputBox"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Type something here" />

    <Button
        android:id="@+id/saveButton"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Save"
        android:layout_marginTop="16dp"/>

    <TextView
        android:id="@+id/outputText"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text=""
        android:layout_marginTop="16dp"/>

</LinearLayout>
```

---

### ✅ Step 4: Add Logic (in `MainActivity.kt`)

Open `MainActivity.kt` and replace its contents with:

```kotlin
package com.example.simpleinputapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var inputBox: EditText
    private lateinit var saveButton: Button
    private lateinit var outputText: TextView

    private var storedText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputBox = findViewById(R.id.inputBox)
        saveButton = findViewById(R.id.saveButton)
        outputText = findViewById(R.id.outputText)

        saveButton.setOnClickListener {
            storedText = inputBox.text.toString()
            outputText.text = "You entered: $storedText"
        }
    }
}
```

---

### ✅ Step 5: Run Your App

1. Plug in an Android phone with Developer Mode enabled — or use an emulator.
2. Click the green **Run ▶️** button in Android Studio.
3. Your app will launch, and you can type into the box, press Save, and see the output.

---

### 🎉 Done!

You now have a working Android app with an input box that stores and shows what you typed. This is a basic in-memory version — next, you can learn about saving data permanently (e.g., `SharedPreferences`, files, or databases).

Would you like to continue by adding persistent storage or another feature next?
