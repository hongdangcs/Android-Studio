package com.ddq.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static TextView startTextView;
    private static Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.button);
        startTextView = findViewById(R.id.startTextView);

        copyDatabase();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
    }

    public void copyDatabase() {
        try {
            DatabaseCopyHelper databaseCopyHelper = new DatabaseCopyHelper(MainActivity.this);
            databaseCopyHelper.createDataBase();
            databaseCopyHelper.openDataBase();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}