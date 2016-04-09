package com.example.heba.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SecondActivity extends ActionBarActivity {

    public static String sharedName = "MyFile";
    String fileName = "InternalFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button btnShared = (Button) findViewById(R.id.btnShared);
        final Button btnInternal= (Button) findViewById(R.id.btnInternal);
        Button btnExternal = (Button) findViewById(R.id.btnExternal);
        Button btnSql = (Button) findViewById(R.id.btnSqlite);
        final TextView txtName = (TextView) findViewById(R.id.textName);
        final TextView txtPhone = (TextView) findViewById(R.id.textPhone);

        btnShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared = getSharedPreferences(sharedName , MODE_PRIVATE);
                Log.i("-------------", shared.getString("Name", "Null"));
                Log.i("-------------", shared.getString("Phone", "Null"));

                txtName.setText(shared.getString("Name", "Null"));
                txtPhone.setText(shared.getString("Phone" , "Null"));
            }
        });

        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fis = openFileInput(fileName);
                    int c;
                    String temp  = "";
                    while((c = fis.read()) != -1){
                        temp = temp + Character.toString((char)c);
                    }
                    String[] data = temp.split(";");
                    txtName.setText(data[0]);
                    txtPhone.setText(data[1]);
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
                String[] res = adapter.selectUsers();
                txtName.setText(res[0]);
                txtPhone.setText(res[1]);
            }
        });

        //Intent i = getIntent();
       // txtName.setText(i.getStringExtra("name"));
        //txtPhone.setText(i.getStringExtra("phone"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
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
