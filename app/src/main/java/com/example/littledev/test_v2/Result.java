package com.example.littledev.test_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class Result extends AppCompatActivity {

    Start test;
    Start_local test_local;
    String local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        local = Choose.located;
        if(local.equals("local")){
            test_local = new Start_local();
            int result = test_local.getScore();
            String res =  result + "%";
            TextView coefficient = (TextView) findViewById(R.id.end_score);
            coefficient.setText(res);
        }
        else {
            test = new Start();
            int result = test.getScore();
            String res =  result + "%";
            TextView coefficient = (TextView) findViewById(R.id.end_score);
            coefficient.setText(res);
        }
        Toast showEmpty = Toast.makeText(this, R.string.your_result, Toast.LENGTH_SHORT);
        showEmpty.show();
    }

    public void endTest(View view){
        if (local.equals("local"))
            test_local.resetScore();
        else
        test.resetScore();
        startActivity(new Intent(this, Main.class));
    }

    public void writeResult(View view){
        //TODO: push result to db
    }
}