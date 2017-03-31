package com.example.littledev.test_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class Create extends AppCompatActivity {

    private EditText question, answer_1, answer_2, answer_3, answer_4, answer_5;
    private RadioButton true_1, true_2, true_3, true_4, true_5;
    private String correct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        question = (EditText) findViewById(R.id.question);
        answer_1 = (EditText) findViewById(R.id.answer_1);
        answer_2 = (EditText) findViewById(R.id.answer_2);
        answer_3 = (EditText) findViewById(R.id.answer_3);
        answer_4 = (EditText) findViewById(R.id.answer_4);
        answer_5 = (EditText) findViewById(R.id.answer_5);
        true_1 = (RadioButton) findViewById(R.id.answer_t1);
        true_2 = (RadioButton) findViewById(R.id.answer_t2);
        true_3 = (RadioButton) findViewById(R.id.answer_t3);
        true_4 = (RadioButton) findViewById(R.id.answer_t4);
        true_5 = (RadioButton) findViewById(R.id.answer_t5);

    }
    public void addData(View view){
        if(true_1.isChecked()) {
            if (answer_1.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
            else
                correct = answer_1.getText().toString();
        }
        else if (true_2.isChecked())
            if (answer_2.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
            else
                correct = answer_1.getText().toString();
        else if (true_3.isChecked())
            if (answer_3.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
            else
                correct = answer_1.getText().toString();
        else if (true_4.isChecked())
            if (answer_4.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
            else
                correct = answer_1.getText().toString();
        else if (true_5.isChecked()) {
            if (answer_5.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
        }
        else {
            Toast.makeText(this, R.string.choose_correct, Toast.LENGTH_LONG).show();
            return;
        }
        DBM dataBaseManager = new DBM(this);
        boolean isInserted = dataBaseManager.insertData(question.getText().toString(),
                answer_1.getText().toString(),
                answer_2.getText().toString(),
                answer_3.getText().toString(),
                answer_4.getText().toString(),
                answer_5.getText().toString(),
                correct);
        if(isInserted) {
            Toast.makeText(this, R.string.inserted, Toast.LENGTH_LONG).show();
            Reset();
        }
        else {
            Toast.makeText(this, R.string.not_inserted, Toast.LENGTH_LONG).show();
        }
    }
    public void onFinish(View view){
        startActivity(new Intent(this,Main.class));
    }

    public void Reset(){
        question.setText("");
        answer_1.setText("");
        answer_2.setText("");
        answer_3.setText("");
        answer_4.setText("");
        answer_5.setText("");
    }
}