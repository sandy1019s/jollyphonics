package com.example.jollyphonics;

public class RecordingModel {
    private String id;
    private String letter;
    private String exampleWord;
    private String filePath;
    private String date;

    public RecordingModel(String id, String letter, String exampleWord, String filePath, String date) {
        this.id = id;
        this.letter = letter;
        this.exampleWord = exampleWord;
        this.filePath = filePath;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public String getExampleWord() {
        return exampleWord;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getDate() {
        return date;
    }
}
