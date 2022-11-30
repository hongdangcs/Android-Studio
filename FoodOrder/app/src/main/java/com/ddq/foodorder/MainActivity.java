package com.ddq.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView donut, froyo, icecream;
    Button cart; String s = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        donut = findViewById(R.id.donutView);
        froyo = findViewById(R.id.froyoView);
        icecream = findViewById(R.id.iceCreamView);
        cart = findViewById(R.id.buttonCart);


        donut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You ordered a Donut!", Toast.LENGTH_LONG).show();
                s += "You ordered a Donut!";
            }
        });
        froyo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You ordered a Frozo!", Toast.LENGTH_LONG).show();
                s+= "You ordered a Frozo!";
            }
        });
        icecream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "You ordered a IceCream!", Toast.LENGTH_LONG).show();
                s+= "You ordered a IceCream!";
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("String", s);
                startActivity(intent);

            }
        });
    }
}