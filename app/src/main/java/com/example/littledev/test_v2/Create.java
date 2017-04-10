package com.example.littledev.test_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class Create extends AppCompatActivity {

    private EditText question, answer_1, answer_2, answer_3, answer_4, answer_5;
    private RadioButton true_1, true_2, true_3, true_4, true_5;
    private String correct;
    private Button add_3, add_4, add_5, minus_3, minus_4, minus_5;

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
        add_3 = (Button) findViewById(R.id.add_3);
        add_4 = (Button) findViewById(R.id.add_4);
        add_5 = (Button) findViewById(R.id.add_5);
        minus_3 = (Button) findViewById(R.id.minus_3);
        minus_4 = (Button) findViewById(R.id.minus_4);
        minus_5 = (Button) findViewById(R.id.minus_5);


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
                correct = answer_2.getText().toString();
        else if (true_3.isChecked())
            if (answer_3.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
            else
                correct = answer_3.getText().toString();
        else if (true_4.isChecked())
            if (answer_4.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
            else
                correct = answer_4.getText().toString();
        else if (true_5.isChecked()) {
            if (answer_5.getText().toString().equals("")) {
                Toast.makeText(this, R.string.cant_be_empty, Toast.LENGTH_LONG).show();
                return;
            }
            else
                correct = answer_5.getText().toString();
        }
        else {
            Toast.makeText(this, R.string.choose_correct, Toast.LENGTH_LONG).show();
            return;
        }

        if (answer_2.getText().toString().equals("") || answer_1.getText().toString().equals("") || question.getText().toString().equals("")) {
            Toast.makeText(this, R.string.first_or_second_not_filled, Toast.LENGTH_LONG).show();
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
        Log.i("test", correct);
        if(isInserted) {
            Toast.makeText(this, R.string.inserted, Toast.LENGTH_LONG).show();
            Reset();
        }
        else {
            Toast.makeText(this, R.string.not_inserted, Toast.LENGTH_LONG).show();
        }
    }
    public void addField(View view){
        Log.i("test",view.getTag().toString());
        switch (view.getTag().toString()){
            case "add_3":
            {
                if (answer_2.getText().toString().equals("") || answer_1.getText().toString().equals("") || question.getText().toString().equals("")) {
                    Toast.makeText(this, R.string.first_or_second_not_filled, Toast.LENGTH_LONG).show();
                    break;
                }
                add_4.setVisibility(View.VISIBLE);
                minus_3.setVisibility(View.VISIBLE);
                answer_3.setVisibility(View.VISIBLE);
                true_3.setVisibility(View.VISIBLE);
                add_3.setVisibility(View.GONE);
                break;
            }
            case "add_4":
            {
                if (answer_3.getText().toString().equals("")) {
                    Toast.makeText(this, R.string.third_empty, Toast.LENGTH_LONG).show();
                    break;
                }
                add_5.setVisibility(View.VISIBLE);
                minus_4.setVisibility(View.VISIBLE);
                answer_4.setVisibility(View.VISIBLE);
                true_4.setVisibility(View.VISIBLE);
                add_4.setVisibility(View.GONE);
                minus_3.setVisibility(View.GONE);
                break;
            }
            case "add_5":
            {
                if (answer_4.getText().toString().equals("")) {
                    Toast.makeText(this, R.string.fourth_empty, Toast.LENGTH_LONG).show();
                    break;
                }
                minus_5.setVisibility(View.VISIBLE);
                answer_5.setVisibility(View.VISIBLE);
                true_5.setVisibility(View.VISIBLE);
                add_5.setVisibility(View.GONE);
                minus_4.setVisibility(View.GONE);
                break;
            }
            case "minus_3":
            {
                minus_3.setVisibility(View.GONE);
                answer_3.setVisibility(View.GONE);
                true_3.setVisibility(View.GONE);
                add_4.setVisibility(View.GONE);
                add_3.setVisibility(View.VISIBLE);
                break;
            }
            case "minus_4":
            {
                minus_4.setVisibility(View.GONE);
                answer_4.setVisibility(View.GONE);
                true_4.setVisibility(View.GONE);
                minus_3.setVisibility(View.VISIBLE);
                add_5.setVisibility(View.GONE);
                add_4.setVisibility(View.VISIBLE);
                break;
            }
            case "minus_5":
            {
                minus_5.setVisibility(View.GONE);
                answer_5.setVisibility(View.GONE);
                true_5.setVisibility(View.GONE);
                minus_4.setVisibility(View.VISIBLE);
                add_5.setVisibility(View.VISIBLE);
                break;
            }
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