package com.yudahendriawan.funcount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class MainActivity extends AppCompatActivity {

    TextView character, word, lowercase, uppercase,
            number, specialChar, vocal, consonan, sentences;
    EditText inputText;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        character = findViewById(R.id.character);
        word = findViewById(R.id.word);
        lowercase = findViewById(R.id.lowercase);
        uppercase = findViewById(R.id.uppercase);
        number = findViewById(R.id.number);
        specialChar = findViewById(R.id.specialchar);
        vocal = findViewById(R.id.vocal);
        consonan = findViewById(R.id.consonan);
        sentences = findViewById(R.id.sentences);
        clear = findViewById(R.id.clear);

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputText.setText(null);
                character.setText(null);
                word.setText(null);
                lowercase.setText(null);
                uppercase.setText(null);
                number.setText(null);
                specialChar.setText(null);
                vocal.setText(null);
                consonan.setText(null);

                sentences.setText(null);
            }
        });
//        count = findViewById(R.id.count);
//
//        count.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


        inputText = findViewById(R.id.inputText);

        inputText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                character.setText(countChar(inputText.getText().toString())+"");
//                word.setText(countWord(inputText.getText().toString())+"");
//                lowercase.setText(countLowerCaseLetters(inputText.getText().toString())+"");
//                uppercase.setText(countUpperCaseLetters(inputText.getText().toString())+"");
//                number.setText(countNumber(inputText.getText().toString())+"");
//                specialChar.setText(countSpecialChar(inputText.getText().toString())+"");
//                vocal.setText(countVokalLetters(inputText.getText().toString())+"");
//                consonan.setText(countConsonanLetters(inputText.getText().toString())+"");
//                try {
//                    sentences.setText(countSentences(inputText.getText().toString())+"");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                String text = s.toString();

                character.setText(countChar(text) + "");
                word.setText(countWord(text) + "");
                lowercase.setText(countLowerCaseLetters(text) + "");
                uppercase.setText(countUpperCaseLetters(text) + "");
                number.setText(countNumber(text) + "");
                specialChar.setText(countSpecialChar(text) + "");
                vocal.setText(countVokalLetters(text) + "");
                consonan.setText(countConsonanLetters(text) + "");
                try {
                    sentences.setText(countSentences(text) + "");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    public int countChar(String kalimat) {
        int count = 0;
        for (int i = 0; i < kalimat.length(); i++) {
            if (kalimat.charAt(i) != ' ') {
                count = count + 1;
            }
        }
        return count;
    }

    public int countWord(String kalimat) {
        if (kalimat == null || kalimat.isEmpty()) {
            return 0;
        }
        String[] words = kalimat.split("\\s+");
        return words.length;
    }

    public int countLowerCaseLetters(String kalimat) {
        int lower = 0;
        for (int i = 0; i < kalimat.length(); i++) {
            char ch = kalimat.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                lower++;
            }
        }
        return lower;
    }

    public int countUpperCaseLetters(String kalimat) {
        int upper = 0;
        for (int i = 0; i < kalimat.length(); i++) {
            char ch = kalimat.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                upper++;
            }
        }
        return upper;
    }

    public int countNumber(String kalimat) {
        int number = 0;
        for (int i = 0; i < kalimat.length(); i++) {
            char ch = kalimat.charAt(i);
            if (ch >= '0' && ch <= '9') {
                number++;
            }
        }
        return number;
    }

    public int countSpecialChar(String kalimat) {
        int spesial = 0;

        for (int i = 0; i < kalimat.length(); i++) {
            char ch = kalimat.charAt(i);
            if (!(ch >= '0' && ch <= '9') && !(ch >= 'A' && ch <= 'Z')
                    && !(ch >= 'a' && ch <= 'z')) {
                spesial++;
            }
        }

        return spesial;
    }

    public int countVokalLetters(String string) {
        int vokal = 0;

        String stringLower = string.toLowerCase();

        for (int i = 0; i < stringLower.length(); i++) {
            char c = stringLower.charAt(i);
            if (c == 'a' || c == 'i' || c == 'u' || c == 'e' || c == 'o') {
                vokal++;
            }
        }

        return vokal;
    }

    public int countConsonanLetters(String string) {
        int consonan = 0;

        String stringLower = string.toLowerCase();

        for (int i = 0; i < stringLower.length(); i++) {
            char c = stringLower.charAt(i);
            if ((c != 'a' && c != 'i' && c != 'u' && c != 'e' && c != 'o') && (c >= 'a' && c <= 'z')) {
                consonan++;
            }
        }
        return consonan;
    }

    public static int countSentences(String string) throws IOException {

        Reader targetReader = new StringReader(string);

        BufferedReader reader = new BufferedReader(targetReader);
        int sentenceCount = 0;
        String line;
        String delimiters = "?!.";

        while ((line = reader.readLine()) != null) { // Continue reading until end of file is reached
            for (int i = 0; i < line.length(); i++) {
                if (delimiters.indexOf(line.charAt(i)) != -1) { // If the delimiters string contains the character
                    sentenceCount++;
                }
            }
        }

        reader.close();
        return sentenceCount;
    }
}
