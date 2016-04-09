package com.example.heba.navigation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.jar.Attributes;

/**
 * Created by Heba on 3/30/2016.
 */
public class Adapter {


    MyHelper helper;

   public Adapter(Context context){
       helper = new MyHelper(context);
   }

    public boolean insertUser(String name, String phone) {
        Log.i("======" , "ana gwa insert");
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(helper.NAME, name);
        contentValues.put(helper.PHONE, phone);

        db.insert(helper.TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }

    public String[] selectUsers(){
        String[ ] columns = {helper.NAME , helper.PHONE};
        String querey = "SELECT * FROM " + helper.TABLE_NAME;
        SQLiteDatabase db = helper.getReadableDatabase();
//        Cursor res = db.rawQuery(querey , null);
        Cursor res =  db.query(helper.TABLE_NAME,columns,null,null,null,null, null);
        String[] result = new String[2];
        res.moveToLast();
            result[0]= res.getString(0);
            result[1] = res.getString(1);
        db.close();

        return result;
    }
}
