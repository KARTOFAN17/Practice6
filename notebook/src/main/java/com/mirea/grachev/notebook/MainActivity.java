package com.mirea.grachev.notebook;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editText1;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);
        textView = findViewById(R.id.textView);
        @SuppressLint("SdCardPath") String pathh = "/data/data/com.mirea.grachev.notebook/files/fileName.txt";
        File file = new File(pathh);
        if (file.exists()) {
            try {
                pathh = "fileName.txt";
                FileInputStream outputStream = openFileInput(pathh);
                byte[] bytes = new byte[outputStream.available()];
                outputStream.read(bytes);
                String text = new String(bytes);
                outputStream = openFileInput(text);
                byte[] bytess = new byte[outputStream.available()];
                outputStream.read(bytess);
                text = new String(bytess);
                textView.setText(text);
                Log.d("Created","Text "+text);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        String path = "fileName.txt";
        String text = editText1.getText().toString() + ".txt";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(path, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("onPause"," It SAVED "+text);
    }

    @SuppressLint("SetText")
    public void onSaveText(View view) throws IOException {
        String fileName = editText1.getText().toString();
        fileName= fileName+".txt";
        @SuppressLint("SdCardPath") String path = "/data/data/com.mirea.grachev.notebook/files/"+fileName;
        File file = new File(path);
        String text=editText.getText().toString();
        if (file.exists()) {
            PrintWriter prWr = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            text=" "+text;
            prWr.write(text);
            prWr.close();
            Log.d("Open ","save "+text);
        }
        else
        {
            FileOutputStream outputStream;
            outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(text.getBytes());
            outputStream.close();
            Log.d("Creat note","save "+text);
        }
        textView.setText("Текст : " +text+ "\nзаписан в файл : "+fileName);

    }
}