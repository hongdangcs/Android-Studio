package com.ddq.sharkboat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boat {
    private Bitmap image1;
    private Bitmap image2;
    private int x;
    private int y;
    private int currentFrame = 1;
    private int health = 100;
    private int screenWidth;

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private int radius;
    private int screenHeight;

    public Boat(Context context) {
        // Load the two images of the boat
        image1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.boat0);
        image2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.boat1);
    }

    public void draw(Canvas canvas) {
        // Draw the current frame of the boat animation
        if (currentFrame == 1) {
            canvas.drawBitmap(image1, x, y, null);
        } else {
            canvas.drawBitmap(image2, x, y, null);
        }
        // Advance to the next frame of the animation
        currentFrame++;
        if (currentFrame > 2) {
            currentFrame = 1;
        }
    }

    public void setScreenSize(int width, int height) {
        screenWidth = width;
        screenHeight = height;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void reduceHealth() {
        health -= 20;
    }

    public int getHealth() {
        return health;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return image1.getWidth();
    }

    public int getHeight() {
        return image1.getHeight();
    }

    public Bitmap getImage1() {
        return image1;
    }

    public void setImage1(Bitmap image1) {
        this.image1 = image1;
    }

    public Bitmap getImage2() {
        return image2;
    }

    public void setImage2(Bitmap image2) {
        this.image2 = image2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void updatePosition() {
        // Update the position of the boat based on user input
        // You can use touch events or keyboard input to control the boat's movement
    }

    public void releaseResources() {
        image1.recycle();
        image1 = null;
        image2.recycle();
        image2 = null;
    }

}