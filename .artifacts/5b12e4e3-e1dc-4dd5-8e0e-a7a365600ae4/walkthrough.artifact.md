# Walkthrough - Separate Audio Playback Buttons & Slow Speech Rate

We have updated the Phonics Sound screen to include two separate audio playback buttons and slowed down the TextToSpeech engine for young children.

## Changes

### 1. Layout & Strings
- **[MODIFY]** `app/src/main/res/values/strings.xml`: Added string resources `play_letter_sound` ("🔊 Play Letter Sound") and `play_example_word` ("🔊 Play Example Word").
- **[MODIFY]** `app/src/main/res/layout/activity_letter.xml`: Replaced the single "Play Sound" button with two distinct buttons:
  - `btnPlayLetterSound` (Teal/Orange styled)
  - `btnPlayExampleWord` (Blue styled)

### 2. Audio Playback Logic (`LetterActivity.java`)
- **[MODIFY]** `app/src/main/java/com/example/jollyphonics/LetterActivity.java`:
  - Configured `tts.setSpeechRate(0.75f)` in `onInit()` for slower, child-friendly pronunciation.
  - Implemented `playLetterSound()`: Speaks **only** the isolated phonics sound (e.g. `æ`, `b`, `c`, `z`) without letter names or example words.
  - Implemented `playExampleWord()`: Speaks the phrase `"A for Apple"`, `"B for Ball"`, etc., naturally and clearly.
  - Used `QUEUE_FLUSH` to prevent audio overlap when tapping either button.

## Verification Results

### Automated Tests
- Executed Gradle build (`app:assembleDebug`) successfully with **zero compilation errors**.
