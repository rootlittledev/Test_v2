package com.example.littledev.test_v2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class Start extends AppCompatActivity {
    TextView question;
    Button answer_1;
    Button answer_2;
    Button answer_3;
    Button answer_4;
    Button answer_5;
    Cursor res;
    static int amount;
    static int score;
    int question_number;
    ProgressBar bar;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        DBM myDb = new DBM(this);
        res = myDb.getAllData();
        res.moveToFirst();
        amount = res.getCount();
        question_number = 1;
        question = (TextView) findViewById(R.id.question_set);
        answer_1 = (Button) findViewById(R.id.answered_1);
        answer_2 = (Button) findViewById(R.id.answered_2);
        answer_3 = (Button) findViewById(R.id.answered_3);
        answer_4 = (Button) findViewById(R.id.answered_4);
        answer_5 = (Button) findViewById(R.id.answered_5);
        bar = (ProgressBar) findViewById(R.id.progress_bar);
        text = (TextView) findViewById(R.id.question_count);
        text.setText(Integer.toString(res.getPosition()+1) + " of " + res.getCount());
        questionManager();

    }
    void reset(){
        answer_1.setEnabled(true);
        answer_2.setEnabled(true);
        answer_3.setEnabled(true);
        answer_4.setEnabled(true);
        answer_5.setEnabled(true);
        answer_1.setVisibility(View.VISIBLE);
        answer_2.setVisibility(View.VISIBLE);
        answer_3.setVisibility(View.VISIBLE);
        answer_4.setVisibility(View.VISIBLE);
        answer_5.setVisibility(View.VISIBLE);
    }
    void questionManager(){
        question.setText(res.getString(1));
        if(!res.getString(2).equals("")){
            answer_1.setText(res.getString(2));
        }else{
            answer_1.setEnabled(false);
            answer_1.setVisibility(View.GONE);
        }
        if(!res.getString(3).equals("")){
            answer_2.setText(res.getString(3));
        }else{
            answer_2.setEnabled(false);
            answer_2.setVisibility(View.GONE);}
        if(!res.getString(4).equals("")){
            answer_3.setText(res.getString(4));
        }else{
            answer_3.setEnabled(false);
            answer_3.setVisibility(View.GONE);}
        if(!res.getString(5).equals("")){
            answer_4.setText(res.getString(5));
        }else{
            answer_4.setEnabled(false);
            answer_4.setVisibility(View.GONE);}
        if(!res.getString(6).equals("")){
            answer_5.setText(res.getString(6));
        }else {
            answer_5.setEnabled(false);
            answer_5.setVisibility(View.GONE);
        }

    }
    public int getScore(){
        Log.i("test",Integer.toString(score));
        score = Math.round(score * 100 / amount);
        return score;
    }
    public void resetScore(){
        score = 0;
    }

    public void answer(View view){
        if(((Button) view).getText().toString().equals(res.getString(7))){

            score++;
            bar.setProgress(Math.round(score * 100 / res.getCount()));

        }
        if (question_number < res.getCount()) {
            res.moveToNext();
            question_number++;
            text.setText(Integer.toString(res.getPosition()+1) + " of " + res.getCount());
        }
        else{
            startActivity(new Intent(this, Result.class));
        }
        reset();
        questionManager();
    }
}
