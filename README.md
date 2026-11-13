# JollyPhonics – Learn Letters & Phonics 🦁🍎

[![Android Studio](https://img.shields.io/badge/Android%20Studio-DDS%20%26%20Java-orange.svg)](https://developer.android.com/studio)
[![Java](https://img.shields.io/badge/Language-Java-blue.svg)](https://www.java.com)
[![Platform](https://img.shields.io/badge/Platform-Android-green.svg)](https://www.android.com)

**JollyPhonics** is a fully functional, premium educational Android application designed specifically for preschool children (aged 3–7) to learn English alphabets, phonics sounds, and example words through an interactive, colorful, and child-friendly gaming interface.

---

## 📱 About the Application

JollyPhonics makes early childhood education engaging and fun! Built entirely in **Java** and **XML** using Android Studio, the app provides a cheerful pastel environment with animal illustrations, rounded cards, touch animations, and reliable audio-visual feedback. Children can explore all 26 letters (A–Z), hear authentic phonics sounds and example words, record their own voices, and review saved recordings anytime.

---

## 📸 Screenshots

<div align="center">
  <table border="1" cellpadding="8" cellspacing="0">
    <tr>
      <td align="center">
        <img src="WhatsApp%20Image%202026-11-13%20at%201.03.01%20PM.jpeg" width="150" alt="JollyPhonics home screen"><br>
        <b>Home Screen</b><br>
        <sub>Friendly welcome screen with quick access to learning and saved recordings.</sub>
      </td>
      <td align="center">
        <img src="WhatsApp%20Image%202026-11-13%20at%201.03.01%20PM%20(1).jpeg" width="150" alt="Alphabet learning screen"><br>
        <b>Alphabet Grid</b><br>
        <sub>Colorful A–Z cards help children explore letters and example words.</sub>
      </td>
      <td align="center">
        <img src="WhatsApp%20Image%202026-11-13%20at%201.03.01%20PM%20(2).jpeg" width="150" alt="Phonics sound screen"><br>
        <b>Phonics Sound</b><br>
        <sub>Shows the selected letter, its phonics sound, illustration, and audio controls.</sub>
      </td>
      <td align="center">
        <img src="WhatsApp%20Image%202026-11-13%20at%201.03.02%20PM.jpeg" width="150" alt="Voice recorder screen"><br>
        <b>Voice Recorder</b><br>
        <sub>Children can record, replay, and save their own pronunciation.</sub>
      </td>
      <td align="center">
        <img src="WhatsApp%20Image%202026-11-13%20at%201.03.02%20PM%20(1).jpeg" width="150" alt="My recordings screen"><br>
        <b>My Recordings</b><br>
        <sub>Saved recordings can be played again or deleted from the recordings list.</sub>
      </td>
    </tr>
  </table>
</div>

---

## ✨ Features

- **Interactive A–Z Alphabet Learning**: Displayed in an attractive 3-column `GridView` with colorful pastel letter cards representing uppercase and lowercase letters.
- **Dedicated Phonics Pronunciation**:
  - **🔊 Play Letter Sound**: Pronounces **only** the isolated phonics sound (e.g. short `æ` for Apple, `/b/` for Ball) without reading the letter name.
  - **🔊 Play Example Word**: Pronounces the natural phrase slowly (e.g., `"A for Apple"`, `"B for Ball"`, `"Z for Zebra"`).
- **Voice Recording Studio**:
  - Children can record their own pronunciation using `MediaRecorder`.
  - Built-in runtime microphone permission handling (`RECORD_AUDIO`).
  - Listen back immediately before saving.
- **My Recordings Screen**:
  - Displays saved user recordings in a `ListView`.
  - Review, play (`MediaPlayer`), or delete recordings (with confirmation dialog) stored permanently in local app storage.
- **Robust TTS Fallback**: Powered by Android `TextToSpeech` with a child-friendly speech rate (`0.75f`).
- **Child-Themed UI/UX**: Designed with vibrant pastel colors (sky blue, sunshine yellow, mint green, coral pink, lavender), custom rounded cards, and smooth interactions.

---

## 🛠️ Technologies Used

- **Programming Language**: Java (100% Java application logic)
- **UI Design**: XML Layouts, Material 3, CardViews, GridView, ListView
- **Audio APIs**: `MediaPlayer`, `MediaRecorder`, `TextToSpeech`
- **Data Persistence**: `SharedPreferences`, Internal File Storage (`getFilesDir()`)
- **IDE**: Android Studio

---

## 📂 Project Structure

```text
jollyPhonics/
├── app/
│   ├── build.gradle.kts
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/jollyphonics/
│           │   ├── MainActivity.java           # Home screen & navigation
│           │   ├── LetterListActivity.java     # A-Z Alphabet grid view
│           │   ├── LetterActivity.java         # Phonics sound & playback controller
│           │   ├── RecordingActivity.java      # Voice recording studio
│           │   ├── MyRecordingsActivity.java   # Saved recordings list
│           │   ├── LetterAdapter.java          # Grid adapter for A-Z letters
│           │   ├── RecordingAdapter.java       # List adapter for recordings
│           │   ├── LetterModel.java            # Data model for letters
│           │   └── RecordingModel.java         # Data model for recordings
│           └── res/
│               ├── drawable/                   # Custom shapes, buttons & vector icons
│               ├── layout/                     # Activity & list item XML layouts
│               └── values/                     # Colors, strings, and themes
```

---

## 🔄 Application Workflow

1. **Home Screen (`MainActivity`)**: Welcomes the child with a friendly lion mascot, app title, and two main big buttons: **START LEARNING** and **MY RECORDINGS**.
2. **Alphabet Learning (`LetterListActivity`)**: Displays all 26 letters (A–Z) in colorful tiles. Tapping any letter opens its detail view.
3. **Phonics & Audio (`LetterActivity`)**: Shows uppercase/lowercase letters, example word, and illustration. Children can tap **Play Letter Sound** or **Play Example Word**, or tap **Record My Voice**.
4. **Voice Recording (`RecordingActivity`)**: Provides recording controls (Start, Stop, Play, Save) with live status indicators.
5. **My Recordings (`MyRecordingsActivity`)**: Lists all saved recordings with custom Play and Delete action buttons.

---

## 🔐 Permissions Required

```xml
<uses-permission android:name="android.permission.RECORD_AUDIO" />
```
*Microphone runtime permission is requested dynamically when opening the voice recording screen.*

---

## 🚀 Installation and Setup Instructions

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/sandy1019s/jollyphonics.git
   ```
2. **Open in Android Studio**:
   - Open Android Studio.
   - Select **Open an Existing Project** and choose the cloned `jollyPhonics` folder.
3. **Gradle Sync**:
   - Allow Android Studio to automatically sync Gradle dependencies.

---

## ▶️ How to Run the Application

1. Connect an Android device with USB debugging enabled or start an **Android Emulator** (API 24+).
2. Click the **Run 'app'** (green play triangle) button in Android Studio toolbar.
3. Explore letters, listen to phonics sounds, record your voice, and enjoy learning!

---

## 👨‍💻 Author

- **GitHub**: [@sandy1019s](https://github.com/sandy1019s)

---

*Made with ❤️ for kids learning phonics!*
