package com.example.littledev.test_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void goCreate(View view){

        startActivity(new Intent(Main.this, Name.class));
    }
    public void goDownload(View view){
        startActivity(new Intent(this, Download.class));
    }
    public void goChoose(View view){
        startActivity(new Intent(this, Choose.class));
    }
}
