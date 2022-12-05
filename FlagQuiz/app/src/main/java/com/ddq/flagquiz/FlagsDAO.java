package com.ddq.flagquiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FlagsDAO
{
    public  ArrayList<FlagsModel> getRandomTenQuestion (FlagsDatabase fd){
        ArrayList<FlagsModel> models = new ArrayList<>();
        SQLiteDatabase liteDatabase = fd.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("select * from flagquizgametable order by random () limit 10", null);
        int flagidIndex = cursor.getColumnIndex("id");
        int flagNameIndex = cursor.getColumnIndex("name");
        int flagImageIndex = cursor.getColumnIndex("image");
        while (cursor.moveToNext()){
            FlagsModel model = new FlagsModel(cursor.getInt(flagidIndex), cursor.getString(flagNameIndex),cursor.getString(flagImageIndex));
            models.add(model);
        }
        return models;
    }

    public ArrayList<FlagsModel> getWrongAns (FlagsDatabase fd, int id){
        ArrayList<FlagsModel> models = new ArrayList<>();
        SQLiteDatabase liteDatabase = fd.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("select * from flagquizgametable where id != "+ id + " order by random () limit 3", null);
        int flagidIndex = cursor.getColumnIndex("id");
        int flagNameIndex = cursor.getColumnIndex("name");
        int flagImageIndex = cursor.getColumnIndex("image");
        while (cursor.moveToNext()){
            FlagsModel model = new FlagsModel(cursor.getInt(flagidIndex), cursor.getString(flagNameIndex),cursor.getString(flagImageIndex));
            models.add(model);
        }
        return models;
    }

    public int getInt(FlagsDatabase fd){
        SQLiteDatabase sqLiteDatabase = fd.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from progress", null);
        return cursor.getCount();
    }

}
