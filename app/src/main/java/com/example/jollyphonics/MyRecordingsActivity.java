package com.example.jollyphonics;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyRecordingsActivity extends AppCompatActivity implements RecordingAdapter.OnRecordingActionListener {

    private ListView listViewRecordings;
    private List<RecordingModel> recordingList;
    private RecordingAdapter adapter;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recordings);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        listViewRecordings = findViewById(R.id.listViewRecordings);
        loadRecordings();
    }

    private void loadRecordings() {
        recordingList = new ArrayList<>();
        SharedPreferences prefs = getSharedPreferences("JollyPhonicsRecordings", Context.MODE_PRIVATE);
        
        for (String key : prefs.getAll().keySet()) {
            if (key.endsWith("_id")) {
                String recordId = prefs.getString(key, "");
                String letter = prefs.getString(recordId + "_letter", "A");
                String word = prefs.getString(recordId + "_word", "Apple");
                String path = prefs.getString(recordId + "_path", "");
                String date = prefs.getString(recordId + "_date", "");

                // Verify file exists
                if (new File(path).exists()) {
                    recordingList.add(new RecordingModel(recordId, letter, word, path, date));
                }
            }
        }

        adapter = new RecordingAdapter(this, recordingList, this);
        listViewRecordings.setAdapter(adapter);
    }

    @Override
    public void onPlay(RecordingModel recording) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(recording.getFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            Toast.makeText(this, "Playing: Letter " + recording.getLetter(), Toast.LENGTH_SHORT).show();

            mediaPlayer.setOnCompletionListener(mp -> {
                mp.release();
                mediaPlayer = null;
            });
        } catch (IOException e) {
            Toast.makeText(this, "Playback failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDelete(RecordingModel recording) {
        new AlertDialog.Builder(this)
                .setTitle("Delete Recording")
                .setMessage("Are you sure you want to delete this recording?")
                .setPositiveButton("Delete", (dialog, which) -> {
                    // Delete file
                    File file = new File(recording.getFilePath());
                    if (file.exists()) {
                        file.delete();
                    }

                    // Remove from SharedPreferences
                    SharedPreferences prefs = getSharedPreferences("JollyPhonicsRecordings", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.remove(recording.getId() + "_id");
                    editor.remove(recording.getId() + "_letter");
                    editor.remove(recording.getId() + "_word");
                    editor.remove(recording.getId() + "_path");
                    editor.remove(recording.getId() + "_date");
                    editor.apply();

                    Toast.makeText(this, "Recording deleted", Toast.LENGTH_SHORT).show();
                    loadRecordings();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
