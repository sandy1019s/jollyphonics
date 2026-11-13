package com.example.jollyphonics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class LetterListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_list);

        ImageButton btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

        GridView gridView = findViewById(R.id.gridViewLetters);
        List<LetterModel> letterList = getAlphabetList();

        LetterAdapter adapter = new LetterAdapter(this, letterList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            LetterModel selected = letterList.get(position);
            Intent intent = new Intent(LetterListActivity.this, LetterActivity.class);
            intent.putExtra("uppercase", selected.getUppercase());
            intent.putExtra("lowercase", selected.getLowercase());
            intent.putExtra("phonics", selected.getPhonicsSound());
            intent.putExtra("word", selected.getExampleWord());
            intent.putExtra("emoji", selected.getEmoji());
            startActivity(intent);
        });
    }

    private List<LetterModel> getAlphabetList() {
        List<LetterModel> list = new ArrayList<>();
        int[] colors = {
                R.color.sky_blue, R.color.sunshine_yellow, R.color.mint_green,
                R.color.coral_pink, R.color.lavender, R.color.peach
        };

        String[][] data = {
                {"A", "a", "/æ/", "Apple", "🍎"},
                {"B", "b", "/b/", "Ball", "⚽"},
                {"C", "c", "c", "Cat", "🐱"},
                {"D", "d", "/d/", "Dog", "🐶"},
                {"E", "e", "/e/", "Elephant", "🐘"},
                {"F", "f", "/f/", "Fish", "🐟"},
                {"G", "g", "/g/", "Goat", "🐐"},
                {"H", "h", "/h/", "Hat", "👒"},
                {"I", "i", "/ɪ/", "Igloo", "⛺"},
                {"J", "j", "/dʒ/", "Jug", "🏺"},
                {"K", "k", "/k/", "Kite", "🪁"},
                {"L", "l", "/l/", "Lion", "🦁"},
                {"M", "m", "/m/", "Monkey", "🐵"},
                {"N", "n", "/n/", "Nest", "🪺"},
                {"O", "o", "/ɒ/", "Owl", "🦉"},
                {"P", "p", "/p/", "Penguin", "🐧"},
                {"Q", "q", "Q", "Queen", "👑"},
                {"R", "r", "/r/", "Rabbit", "🐰"},
                {"S", "s", "/s/", "Sun", "☀️"},
                {"T", "t", "/t/", "Tree", "🌳"},
                {"U", "u", "/ʌ/", "Umbrella", "☂️"},
                {"V", "v", "/v/", "Violin", "🎻"},
                {"W", "w", "/w/", "Watch", "⌚"},
                {"X", "x", "X", "Xylophone", "🎹"},
                {"Y", "y", "Y", "Yak", "🐃"},
                {"Z", "z", "/z/", "Zebra", "🦓"}
        };

        for (int i = 0; i < data.length; i++) {
            list.add(new LetterModel(
                    data[i][0],
                    data[i][1],
                    data[i][2],
                    data[i][3],
                    data[i][4],
                    colors[i % colors.length]
            ));
        }

        return list;
    }
}
