package com.ddq.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        result = findViewById(R.id.textView);
        Intent intent = getIntent();
        result.setText( + intent.getIntExtra("String", 0));
    }
}