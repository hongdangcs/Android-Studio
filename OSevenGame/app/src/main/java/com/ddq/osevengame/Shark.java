package com.ddq.osevengame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class Shark {

    public static final float VELOCITY = 0.1f;

    private int movingVectorX = 10;
    private int movingVectorY = 5;

    private long lastDrawNanoTime =-1;

    private GameSurface gameSurface;

    protected Bitmap image;
    protected int x;
    protected int y;
    int width;int height;

    public Shark(Context context, GameSurface gameSurface,  int x, int y) {

        this.gameSurface= gameSurface;
        this.x= x;
        this.y= y;
        Bitmap originalImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.shark);
         this.width = originalImage.getWidth();
         this.height = originalImage.getHeight();
        float scaleFactor = 1.2f; // Change this value to adjust the size of the image
        image = Bitmap.createScaledBitmap(originalImage, (int)(width * scaleFactor), (int)(height * scaleFactor), false);
    }

    public void update()  {
        // Current time in nanoseconds
        long now = System.nanoTime();

        // Never once did draw.
        if(lastDrawNanoTime==-1) {
            lastDrawNanoTime= now;
        }
        // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
        int deltaTime = (int) ((now - lastDrawNanoTime)/ 1000000 );

        // Distance moves
        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX* movingVectorX + movingVectorY*movingVectorY);

        // Calculate the new position of the game character.
        this.x = x +  (int)(distance* movingVectorX / movingVectorLength);
        this.y = y +  (int)(distance* movingVectorY / movingVectorLength);

        // When the game's character touches the edge of the screen, then change direction

        if(this.x < 0 )  {
            this.x = 0;
            this.movingVectorX = - this.movingVectorX;
        } else if(this.x > this.gameSurface.getWidth() -width)  {
            this.x= this.gameSurface.getWidth()-width;
            this.movingVectorX = - this.movingVectorX;
        }

        if(this.y < 0 )  {
            this.y = 0;
            this.movingVectorY = - this.movingVectorY;
        } else if(this.y > this.gameSurface.getHeight()- height)  {
            this.y= this.gameSurface.getHeight()- height;
            this.movingVectorY = - this.movingVectorY ;
        }

        // rowUsing
    }

    public void draw(Canvas canvas)  {
        canvas.drawBitmap(image,x, y, null);
        // Last draw time.
        this.lastDrawNanoTime= System.nanoTime();
    }

    public void setMovingVector(int movingVectorX, int movingVectorY)  {
        this.movingVectorX= movingVectorX;
        this.movingVectorY = movingVectorY;
    }



    public int getX()  {
        return this.x;
    }

    public int getY()  {
        return this.y;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
