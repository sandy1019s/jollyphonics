package com.example.jollyphonics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStartLearning = findViewById(R.id.btnStartLearning);
        Button btnMyRecordings = findViewById(R.id.btnMyRecordings);

        btnStartLearning.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LetterListActivity.class);
            startActivity(intent);
        });

        btnMyRecordings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MyRecordingsActivity.class);
            startActivity(intent);
        });
    }
}
