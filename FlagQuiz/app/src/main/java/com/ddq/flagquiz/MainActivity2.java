package com.ddq.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textview;
    private FlagsDatabase flagsDatabase;
    private int getCount ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textview = findViewById(R.id.textViewDatabaseTest);

        flagsDatabase = new FlagsDatabase(MainActivity2.this);
        getCount = new FlagsDAO().getInt(flagsDatabase);

        textview.setText(""+getCount);



    }
}