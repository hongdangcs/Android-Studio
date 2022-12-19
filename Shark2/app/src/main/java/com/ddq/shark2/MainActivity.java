package com.ddq.shark2;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        int screenHeight = getResources().getDisplayMetrics().heightPixels;


        ImageView sharkImageView = findViewById(R.id.shark_image_view);

        int sharkWidth = sharkImageView.getWidth();
        int sharkHeight = sharkImageView.getHeight();

        Random random = new Random();
        float x = random.nextInt(screenWidth - sharkWidth);
        float y = random.nextInt(screenHeight - sharkHeight);

// Set the position of the shark
        sharkImageView.setX(x);
        sharkImageView.setY(y);

        AnimationDrawable sharkAnimation = (AnimationDrawable) getResources().getDrawable(R.drawable.shark_animation);
        sharkImageView.setBackground(sharkAnimation);

// Start the animation
        sharkAnimation.start();

        // Set the initial position of the shark
        sharkImageView.setTranslationX(0);

// Move the shark to the right
        sharkImageView.setTranslationX(sharkImageView.getTranslationX() + 10);

// Move the shark to the left
        sharkImageView.setTranslationX(sharkImageView.getTranslationX() - 10);

// Stop the animation
//        sharkAnimation.stop();
    }
}