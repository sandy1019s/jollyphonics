package com.example.jollyphonics;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RecordingActivity extends AppCompatActivity {

    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    private String letter = "A";
    private String exampleWord = "Apple";
    private String audioFilePath = "";

    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;

    private Button btnStartRecord, btnStopRecord, btnPlayRecord, btnSaveRecord;
    private TextView tvStatus, tvRecordLetterInfo;

    private boolean isRecording = false;
    private boolean isPlaying = false;
    private boolean hasRecorded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        letter = getIntent().getStringExtra("uppercase");
        exampleWord = getIntent().getStringExtra("word");
        if (letter == null) letter = "A";
        if (exampleWord == null) exampleWord = "Apple";

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        tvRecordLetterInfo = findViewById(R.id.tvRecordLetterInfo);
        tvRecordLetterInfo.setText("Letter " + letter + " - " + exampleWord);

        tvStatus = findViewById(R.id.tvStatus);
        btnStartRecord = findViewById(R.id.btnStartRecord);
        btnStopRecord = findViewById(R.id.btnStopRecord);
        btnPlayRecord = findViewById(R.id.btnPlayRecord);
        btnSaveRecord = findViewById(R.id.btnSaveRecord);

        // Check audio permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        } else {
            permissionToRecordAccepted = true;
        }

        File audioDir = new File(getFilesDir(), "recordings");
        if (!audioDir.exists()) {
            audioDir.mkdirs();
        }
        String fileName = "rec_" + letter + "_" + System.currentTimeMillis() + ".3gp";
        audioFilePath = new File(audioDir, fileName).getAbsolutePath();

        btnStartRecord.setOnClickListener(v -> startRecording());
        btnStopRecord.setOnClickListener(v -> stopRecording());
        btnPlayRecord.setOnClickListener(v -> playRecording());
        btnSaveRecord.setOnClickListener(v -> saveRecording());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            permissionToRecordAccepted = grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        if (!permissionToRecordAccepted) {
            Toast.makeText(this, "Microphone permission is required to record voice!", Toast.LENGTH_LONG).show();
        }
    }

    private void startRecording() {
        if (!permissionToRecordAccepted) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_RECORD_AUDIO_PERMISSION);
            return;
        }

        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(audioFilePath);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
            tvStatus.setText("Recording in progress... 🎤");
            btnStartRecord.setEnabled(false);
            btnStopRecord.setEnabled(true);
            btnPlayRecord.setEnabled(false);
            btnSaveRecord.setEnabled(false);
        } catch (IOException e) {
            Toast.makeText(this, "Recording failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRecording() {
        if (isRecording && mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
                isRecording = false;
                hasRecorded = true;
                tvStatus.setText("Recording finished! Tap Play or Save.");
                btnStartRecord.setEnabled(true);
                btnStopRecord.setEnabled(false);
                btnPlayRecord.setEnabled(true);
                btnSaveRecord.setEnabled(true);
            } catch (Exception e) {
                Toast.makeText(this, "Stop recording failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void playRecording() {
        if (!hasRecorded) return;

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(audioFilePath);
            mediaPlayer.prepare();
            mediaPlayer.start();
            isPlaying = true;
            tvStatus.setText("Playing recording... 🔊");

            mediaPlayer.setOnCompletionListener(mp -> {
                isPlaying = false;
                tvStatus.setText("Playback finished.");
                mp.release();
                mediaPlayer = null;
            });
        } catch (IOException e) {
            Toast.makeText(this, "Playback failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveRecording() {
        if (!hasRecorded) {
            Toast.makeText(this, "Please record audio first!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save to SharedPreferences
        SharedPreferences prefs = getSharedPreferences("JollyPhonicsRecordings", Context.MODE_PRIVATE);
        int count = prefs.getInt("recording_count", 0);
        String recordId = "rec_" + System.currentTimeMillis();
        String dateStr = new SimpleDateFormat("MMM dd, yyyy", Locale.US).format(new Date());

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(recordId + "_id", recordId);
        editor.putString(recordId + "_letter", letter);
        editor.putString(recordId + "_word", exampleWord);
        editor.putString(recordId + "_path", audioFilePath);
        editor.putString(recordId + "_date", dateStr);
        editor.putInt("recording_count", count + 1);
        editor.apply();

        Toast.makeText(this, "Recording saved successfully! 🎉", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
