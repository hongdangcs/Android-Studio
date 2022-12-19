package com.ddq.sharkboat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.ddq.sharkboat.R;

import java.util.Random;

public class Shark {
    private Context context;
    private Bitmap image;
    private float x;
    private float y;
    private float velocityX;
    private float velocityY;
    private float width;
    private float height;
    private float screenWidth;
    private float screenHeight;
    private Random random;

    public Shark(Context context) {
        this.context = context;
        random = new Random();
        setRandomPosition();
    }

    public void setRandomPosition() {
        // Set a random position for the shark
        x = random.nextInt((int) screenWidth);
        y = random.nextInt((int) screenHeight);
    }

    public void setVelocity(float velocityX, float velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void setScreenSize(float screenWidth, float screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public void setImage(int resourceId) {
        image = BitmapFactory.decodeResource(context.getResources(), R.drawable.shark);
        width = image.getWidth();
        height = image.getHeight();
    }

    public void updatePosition() {
        // Update the position of the shark based on its velocity
        x += velocityX;
        y += velocityY;
    }

    public void draw(Canvas canvas, Paint paint) {
        // Draw the image
        canvas.drawBitmap(image, x, y, paint);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}