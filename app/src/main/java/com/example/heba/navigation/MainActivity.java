package com.example.heba.navigation;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends Activity {

    public static String sharedName = "MyFile";
    String fileName = "InternalFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnNext = (Button) findViewById(R.id.btnNext);
        Button btnInternal= (Button) findViewById(R.id.btnInternal);
        Button btnExternal = (Button) findViewById(R.id.btnExternal);
        Button btnSql = (Button) findViewById(R.id.btnSqlite);
        final EditText txtName = (EditText) findViewById(R.id.txtName);
        final EditText txtPhone = (EditText) findViewById(R.id.txtPhone);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.i("+++++" , txtName.getText().toString());
                Log.i("+++++" , txtPhone.getText().toString());

                SharedPreferences shared = getSharedPreferences(sharedName , MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("Name" , txtName.getText().toString());
                editor.putString("Phone", txtPhone.getText().toString());
                editor.commit();

                txtName.setText("");
                txtPhone.setText("");

                Intent i = new Intent(MainActivity.this , SecondActivity.class);
               // i.putExtra("name" , txtName.getText().toString());
               // i.putExtra("phone" , txtPhone.getText().toString());
                startActivity(i);
            }
        });

        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(fileName , Context.MODE_PRIVATE);
                    fos.write(txtName.getText().toString().getBytes());
                    fos.write(";".getBytes());
                    fos.write(txtPhone.getText().toString().getBytes());
                    fos.close();
                    txtName.setText("");
                    txtPhone.setText("");
                    Intent i = new Intent(MainActivity.this , SecondActivity.class);
                    // i.putExtra("name" , txtName.getText().toString());
                    // i.putExtra("phone" , txtPhone.getText().toString());
                    startActivity(i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btnSql.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adapter adapter = new Adapter(getApplicationContext());
                adapter.insertUser(txtName.getText().toString() , txtPhone.getText().toString());
                txtName.setText("");
                txtPhone.setText("");
                Intent i = new Intent(MainActivity.this , SecondActivity.class);
                // i.putExtra("name" , txtName.getText().toString());
                // i.putExtra("phone" , txtPhone.getText().toString());
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
