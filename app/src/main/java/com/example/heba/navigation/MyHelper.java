package com.example.heba.navigation;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Heba on 3/29/2016.
 */
public class MyHelper extends SQLiteOpenHelper {


    public static String dbName = "mydb";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_NAME = "User";
    public static String NAME = "Name";
    public static String UID = "id";
    public static String PHONE = "Phone";
    public static String CreateDB = "create table " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY autoincrement , " + NAME + " text," + PHONE + " text)";


    MyHelper(Context context){
        super(context, dbName, null, DATABASE_VERSION);
    }

//    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CreateDB);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}