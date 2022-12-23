package com.ddq.osevengame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.Random;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Shark shark;
    Random random = new Random();

    Context mContext;
    Rect rect;
    public GameSurface(Context context)  {
        super(context);
        mContext = context;
        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        rect = new Rect(0, 0, screenWidth, screenHeight);

        shark = new Shark(getContext(), this, random.nextInt(screenWidth-20), random.nextInt(screenHeight-20));
    }

    public void update()  {
        this.shark.update();
    }

    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);

        this.shark.draw(canvas);
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.gameThread = new GameThread(this,holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= true;
        }
    }

}
