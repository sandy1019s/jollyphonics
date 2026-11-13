package com.example.jollyphonics;

import java.io.Serializable;

public class LetterModel implements Serializable {
    private String uppercase;
    private String lowercase;
    private String phonicsSound;
    private String exampleWord;
    private String emoji;
    private int backgroundColorRes;

    public LetterModel(String uppercase, String lowercase, String phonicsSound, String exampleWord, String emoji, int backgroundColorRes) {
        this.uppercase = uppercase;
        this.lowercase = lowercase;
        this.phonicsSound = phonicsSound;
        this.exampleWord = exampleWord;
        this.emoji = emoji;
        this.backgroundColorRes = backgroundColorRes;
    }

    public String getUppercase() {
        return uppercase;
    }

    public String getLowercase() {
        return lowercase;
    }

    public String getPhonicsSound() {
        return phonicsSound;
    }

    public String getExampleWord() {
        return exampleWord;
    }

    public String getEmoji() {
        return emoji;
    }

    public int getBackgroundColorRes() {
        return backgroundColorRes;
    }

    public String getSpeechText() {
        return uppercase + " for " + exampleWord;
    }
}
