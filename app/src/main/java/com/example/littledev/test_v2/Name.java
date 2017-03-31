package com.example.littledev.test_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
    }

    public void nameTest(View view){
        EditText table_name = (EditText) findViewById(R.id.table_name);
        DBM dataBaseManager = new DBM(this);
        dataBaseManager.setName(table_name.getText().toString());
        dataBaseManager.CreateTable();
        Toast showEmpty = Toast.makeText(this, R.string.named_test, Toast.LENGTH_SHORT);
        showEmpty.show();
        startActivity(new Intent(this, Create.class));
    }

}
