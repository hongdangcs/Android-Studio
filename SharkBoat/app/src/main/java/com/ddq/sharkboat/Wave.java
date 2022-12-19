package com.ddq.sharkboat;

import android.content.Context;
import android.widget.ImageView;

public class Wave {
    // Fields for the wave's ImageView and position
    private ImageView imageView;
    private float x;
    private float y;

    // Constructor for the Wave class
    public Wave(Context context, float x, float y) {
        // Create a new ImageView for the wave
        imageView = new ImageView(context);

        // Set the image for the wave
        imageView.setImageResource(R.drawable.wave);

        // Set the initial position of the wave
        this.x = x;
        this.y = y;
    }

    // Method to get the ImageView for the wave
    public ImageView getImageView() {
        return imageView;
    }

    // Methods to get and set the x and y position of the wave
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
