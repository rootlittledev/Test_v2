package com.example.littledev.test_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class Result extends AppCompatActivity {

    Start test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        test = new Start();
        int result = test.getScore();
        TextView coefficient = (TextView) findViewById(R.id.end_score);
        coefficient.setText(Integer.toString(result) + "%");
    }

    public void endTest(View view){
        test.resetScore();
        startActivity(new Intent(this, Main.class));
    }
}