package com.ddq.sharkboat;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {
    private Paint paint;
    private List<Shark> sharks;
    private List<Boat> boats;
    private Wave wave;

    public GameView(Context context) {
        super(context);

        // Set up the paint
        paint = new Paint();
        paint.setAntiAlias(true);

        // Set up the sharks
        sharks = new ArrayList<>();

        // Set up the boats
        boats = new ArrayList<>();

        // Set up the wave
  //      wave = new Wave(context);
    }

    public void setSharks(List<Shark> sharks) {
        this.sharks = sharks;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw the sharks
        for (Shark shark : sharks) {
            shark.draw(canvas, paint);
        }

        // Draw the boats
 //       for (Boat boat : boats) {
  //          boat.draw(canvas, paint);
  //      }

   //     // Draw the wave
   //     wave.draw(canvas, paint);
    }
}