package com.ddq.flagquiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FlagsDatabase extends SQLiteOpenHelper {

    public FlagsDatabase(@Nullable Context context) {
        super(context, "ddqbraintrain.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS \"progress\" (\n" +
                "\t\"game_id\"\tINTEGER,\n" +
                "\t\"game_name\"\tTEXT,\n" +
                "\t\"max_score\"\tINTEGER,\n" +
                "\t\"user_score\"\tINTEGER,\n" +
                "\t\"complete_status\"\tINTEGER\n" +
                ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists progress");
        onCreate(db);

    }
}
