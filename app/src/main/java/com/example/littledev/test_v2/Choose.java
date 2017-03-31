package com.example.littledev.test_v2;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Choose extends AppCompatActivity {

    DBM dataBaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        dataBaseManager = new DBM(this);
        getTests();
    }

    private void  getTests(){
        Cursor c = dataBaseManager.getTest();
        Spinner listView = (Spinner) findViewById(R.id.tests_list);
        List<String> arrTblNames = new ArrayList<>();

        if (c.moveToFirst()) {
            while ( !c.isAfterLast() ) {
                if(!c.getString( c.getColumnIndex("name")).equals("android_metadata") && !c.getString(c.getColumnIndex("name")).equals("sqlite_sequence"))
                    arrTblNames.add( c.getString( c.getColumnIndex("name")) );
                c.moveToNext();
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrTblNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(adapter);
        if (adapter.isEmpty()){
            String message = "No tests found";
            Toast showEmpty = Toast.makeText(this, message, Toast.LENGTH_SHORT);
            showEmpty.show();
        }

    }

    public void onChoose(View view){

        Spinner spinner = (Spinner) findViewById(R.id.tests_list);
        String item = spinner.getSelectedItem().toString();
        dataBaseManager.setTest(item);
        startActivity(new Intent(this,Start.class));

    }
}