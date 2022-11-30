package com.ddq.sqlitedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String IS_ACTIVE = "is_active";
    public static final String CUSTOMER_TABLE = "customer_table";
    public static final String ID = "id";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "create table " + CUSTOMER_TABLE + "(" + ID + " integer primary key autoincrement, " + NAME + " text, " + AGE + " integer, " + IS_ACTIVE + " bool)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addCustomer(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, customerModel.getName());
        contentValues.put(AGE, customerModel.getAge());
        contentValues.put(IS_ACTIVE, customerModel.isActive());
        long insert = db.insert(CUSTOMER_TABLE, null, contentValues);
        if (insert == -1) {
            return false;
        }
        return true;
    }

    public List<CustomerModel> getAllCustomer() {
        List<CustomerModel> returnList = new ArrayList<>();
        String query = "select * from " + CUSTOMER_TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                boolean active = cursor.getInt(3) == 1 ? true : false;
                CustomerModel customer = new CustomerModel(id, name, age, active);
                returnList.add(customer);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return returnList;
    }

    public boolean deleteCustomer(CustomerModel customerModel){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "delete from "+ CUSTOMER_TABLE+ " where "+ ID + " = " + customerModel.getId();
        Cursor query1 = db.rawQuery(query, null);
        if(query1.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }
}
