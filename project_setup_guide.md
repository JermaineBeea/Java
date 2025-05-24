# Complete Android Project Setup Guide

## Project Structure
Your Android project should have the following structure:

```
SimpleInputApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/simpleinputapp/
│   │   │   │   └── MainActivity.java
│   │   │   ├── res/
│   │   │   │   ├── drawable/
│   │   │   │   │   ├── edit_text_background.xml
│   │   │   │   │   ├── button_background.xml
│   │   │   │   │   ├── button_background_secondary.xml
│   │   │   │   │   └── display_background.xml
│   │   │   │   ├── layout/
│   │   │   │   │   └── activity_main.xml
│   │   │   │   ├── values/
│   │   │   │   │   └── strings.xml
│   │   │   │   └── mipmap/
│   │   │   │       └── (launcher icons)
│   │   │   └── AndroidManifest.xml
│   │   └── build.gradle
│   └── build.gradle (Project level)
```

## Step-by-Step Setup Instructions

### 1. Create New Android Project
1. Open Android Studio
2. Select "Create New Project"
3. Choose "Empty Activity"
4. Set Application Name: "Simple Input App"
5. Set Package Name: "com.example.simpleinputapp"
6. Choose minimum SDK: API 24 (Android 7.0)
7. Click "Finish"

### 2. Replace Generated Files
Replace the automatically generated files with the code provided in the artifacts above:

#### Main Activity (MainActivity.java)
- Location: `app/src/main/java/com/example/simpleinputapp/MainActivity.java`
- Replace with the MainActivity.java code provided

#### Layout File (activity_main.xml)
- Location: `app/src/main/res/layout/activity_main.xml`
- Replace with the activity_main.xml code provided

#### Android Manifest (AndroidManifest.xml)
- Location: `app/src/main/AndroidManifest.xml`
- Replace with the AndroidManifest.xml code provided

#### String Resources (strings.xml)
- Location: `app/src/main/res/values/strings.xml`
- Replace with the strings.xml code provided

#### Build Configuration (build.gradle)
- Location: `app/build.gradle`
- Replace with the build.gradle code provided

### 3. Create Drawable Resources
Create these XML files in `app/src/main/res/drawable/`:

1. `edit_text_background.xml`
2. `button_background.xml`
3. `button_background_secondary.xml`
4. `display_background.xml`

Copy the content from the "Drawable Resources" artifact above, creating separate files for each shape definition.

### 4. Sync and Build
1. Click "Sync Now" when prompted
2. Build the project (Build → Make Project)
3. Run the app on an emulator or device

## How the App Works

### Core Functionality
- **Input Field**: Users can type any text they want
- **Save Button**: Stores the entered text permanently on the device
- **Load Button**: Retrieves and displays the stored text
- **Display Area**: Shows the currently stored information

### Data Storage
The app uses **SharedPreferences** to store data persistently:
- Data survives app restarts
- Data is stored locally on the device
- Simple key-value storage system
- Perfect for small amounts of data like user preferences or simple text

### Key Components Explained

#### MainActivity.java
- **onCreate()**: Sets up the app when it starts
- **initializeViews()**: Connects Java code to UI elements
- **setupClickListeners()**: Handles button clicks
- **saveInputData()**: Saves text to device storage
- **loadStoredData()**: Retrieves text from storage

#### UI Elements
- **EditText**: Input field for typing
- **Buttons**: Save and Load functionality
- **TextView**: Displays stored information
- **LinearLayout**: Organizes elements vertically

#### Storage System
- **SharedPreferences**: Android's built-in storage for small data
- **Key-Value Pairs**: "stored_text" key holds the user's input
- **Persistent**: Data remains after closing the app

## Testing Your App

1. **Install and Open**: Run the app on your device/emulator
2. **Enter Text**: Type something in the input field
3. **Save**: Press "Save Text" button
4. **Verify**: See confirmation message and cleared input field
5. **Check Display**: Stored text appears in the display area
6. **Test Persistence**: Close and reopen app - data should still be there
7. **Load Function**: Press "Load Text" to refresh the display

## Next Steps for Learning

This simple app demonstrates:
- Basic Android project structure
- UI design with XML layouts
- Java programming for Android
- Data persistence with SharedPreferences
- Event handling (button clicks)
- User feedback with Toast messages

You can expand this app by adding:
- Multiple input fields
- List of saved items
- Edit/delete functionality
- Different data types (numbers, dates)
- Database storage for complex data