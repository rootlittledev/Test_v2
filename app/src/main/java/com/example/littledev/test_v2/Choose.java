package com.example.littledev.test_v2;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Choose extends AppCompatActivity {

    DBM dataBaseManager;
    public static String located;
    public static List<String> tests;
    Spinner listViewL;
    Spinner listView;

    public static void setSubjects(List<String> subjects) {
        tests = subjects;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        dataBaseManager = new DBM(this);
        listViewL = (Spinner) findViewById(R.id.tests_list_local);
        listView = (Spinner) findViewById(R.id.tests_list);
        located = "";
        Sql_bridge bridge = new Sql_bridge(this);
        bridge.execute("subject");
        getTestsL();
        //getTests();


    }

    private void  getTestsL(){
        Cursor c = dataBaseManager.getTest();
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
        listViewL.setAdapter(adapter);
        if (adapter.isEmpty()){
            Toast showEmpty = Toast.makeText(this, R.string.no_tests_local, Toast.LENGTH_SHORT);
            showEmpty.show();
        }

    }
    public void  getTests(){
        List<String> arrTblNames = tests;

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrTblNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listView.setAdapter(adapter);
        if (adapter.isEmpty()){
            Toast showEmpty = Toast.makeText(this, R.string.no_tests_online, Toast.LENGTH_SHORT);
            showEmpty.show();
        }

    }


    public void onLocated(View view)
    {
        if(view.getTag().toString().equals("local")){
            located = "local";
            listView.setEnabled(false);
            listViewL.setEnabled(true);
        }
        else if(view.getTag().toString().equals("online"))
            located = "online";
            listViewL.setEnabled(false);
            listView.setEnabled(true);


    }

    public void onChoose(View view){
        if (located.equals("local")) {
            Spinner spinner = (Spinner) findViewById(R.id.tests_list_local);
            if (!spinner.getSelectedItem().toString().equals("")) {
                String item = spinner.getSelectedItem().toString();
                dataBaseManager.setTest(item);
                startActivity(new Intent(this, Start_local.class));
            }
            else
                Toast.makeText(this, R.string.choose_test, Toast.LENGTH_LONG).show();
        }
        else if (located.equals("online")) {
            Spinner spinner = (Spinner) findViewById(R.id.tests_list);
            if (!spinner.getSelectedItem().toString().equals("")){
                Start.test_name = spinner.getSelectedItem().toString();
                startActivity(new Intent(this, Start.class));
            }else
                Toast.makeText(this, R.string.choose_test, Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, R.string.choose_location, Toast.LENGTH_LONG).show();

    }
}