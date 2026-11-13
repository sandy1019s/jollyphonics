package com.example.jollyphonics;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LetterActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;
    private String uppercase, lowercase, phonics, word, emoji;
    private boolean isTtsReady = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);

        tts = new TextToSpeech(this, this);

        uppercase = getIntent().getStringExtra("uppercase");
        lowercase = getIntent().getStringExtra("lowercase");
        phonics = getIntent().getStringExtra("phonics");
        word = getIntent().getStringExtra("word");
        emoji = getIntent().getStringExtra("emoji");

        if (uppercase == null) uppercase = "A";
        if (lowercase == null) lowercase = "a";
        if (phonics == null) phonics = "/æ/";
        if (word == null) word = "Apple";
        if (emoji == null) emoji = "🍎";

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        TextView tvLetter = findViewById(R.id.tvDetailLetter);
        TextView tvPhonics = findViewById(R.id.tvDetailPhonics);
        TextView tvWord = findViewById(R.id.tvDetailWord);
        TextView tvEmoji = findViewById(R.id.tvDetailEmoji);

        tvLetter.setText(uppercase + " " + lowercase);
        String cleanSound = phonics.replace("/", "");
        tvPhonics.setText("Sound: " + cleanSound);
        tvWord.setText(word);
        tvEmoji.setText(emoji);

        Button btnPlayLetterSound = findViewById(R.id.btnPlayLetterSound);
        Button btnPlayExampleWord = findViewById(R.id.btnPlayExampleWord);
        Button btnRecordVoice = findViewById(R.id.btnRecordVoice);

        btnPlayLetterSound.setOnClickListener(v -> playLetterSound());
        btnPlayExampleWord.setOnClickListener(v -> playExampleWord());

        btnRecordVoice.setOnClickListener(v -> {
            Intent intent = new Intent(LetterActivity.this, RecordingActivity.class);
            intent.putExtra("uppercase", uppercase);
            intent.putExtra("word", word);
            startActivity(intent);
        });
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED) {
                isTtsReady = true;
                // Set slower child-friendly speech rate as requested
                tts.setSpeechRate(0.75f);
            }
        }
    }

    private void playLetterSound() {
        if (isTtsReady) {
            String cleanSound = phonics.replace("/", "");
            // Pronounce only the isolated letter phonics sound clearly
            tts.speak(cleanSound, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Toast.makeText(this, "Sound: " + phonics.replace("/", ""), Toast.LENGTH_SHORT).show();
        }
    }

    private void playExampleWord() {
        if (isTtsReady) {
            // Natural child-friendly slow phrase: "A for Apple"
            String speechText = uppercase + " for " + word;
            tts.speak(speechText, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            Toast.makeText(this, uppercase + " for " + word, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
