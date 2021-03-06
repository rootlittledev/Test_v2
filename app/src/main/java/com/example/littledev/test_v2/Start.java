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

import java.util.HashMap;
import java.util.List;


public class Start extends AppCompatActivity {
    TextView question;
    Button answer_1;
    Button answer_2;
    Button answer_3;
    Button answer_4;
    Button answer_5;
    static int score;
    int index;
    static int size;
    public static String test_name;
    ProgressBar bar;
    TextView text;
    String correct;
    static HashMap<String, List<String>> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Sql_bridge bridge = new Sql_bridge(this);
        bridge.execute("test", test_name);
        index = 0;
        score = 0;

        question = (TextView) findViewById(R.id.question_set);
        answer_1 = (Button) findViewById(R.id.answered_1);
        answer_2 = (Button) findViewById(R.id.answered_2);
        answer_3 = (Button) findViewById(R.id.answered_3);
        answer_4 = (Button) findViewById(R.id.answered_4);
        answer_5 = (Button) findViewById(R.id.answered_5);
        bar = (ProgressBar) findViewById(R.id.progress_bar);
        text = (TextView) findViewById(R.id.question_count);


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

    public static void setTest(HashMap<String, List<String>> map){
        test = map;
    }
    public void questionManager(){
        size = test.get("question").size();
        text.setText(Integer.toString(index+1) + " of " + size);
        question.setText(test.get("question").get(index));
        if(!test.get("answer_1").get(index).equals("")){
            answer_1.setText(test.get("answer_1").get(index));
        }else{
            answer_1.setEnabled(false);
            answer_1.setVisibility(View.GONE);
        }
        if(!test.get("answer_2").get(index).equals("")){
            answer_2.setText(test.get("answer_2").get(index));
        }else{
            answer_2.setEnabled(false);
            answer_2.setVisibility(View.GONE);}
        if(!test.get("answer_3").get(index).equals("")){
            answer_3.setText(test.get("answer_3").get(index));
        }else{
            answer_3.setEnabled(false);
            answer_3.setVisibility(View.GONE);}
        if(!test.get("answer_4").get(index).equals("")){
            answer_4.setText(test.get("answer_4").get(index));
        }else{
            answer_4.setEnabled(false);
            answer_4.setVisibility(View.GONE);}
        if(!test.get("answer_5").get(index).equals("")){
            answer_5.setText(test.get("answer_5").get(index));
        }else {
            answer_5.setEnabled(false);
            answer_5.setVisibility(View.GONE);
        }
        correct = test.get("correct").get(index);
        index++;

    }
    public int getScore(){
        score = Math.round(score * 100 / size);
        return score;
    }
    public void resetScore(){
        score = 0;
    }

    public void answer(View view){

        if(view.getTag().equals(correct)){
            score++;
            bar.setProgress(Math.round(score * 100 / size));

        }
        if (index < test.get("question").size()) {
            reset();
            questionManager();
        }

        else{
            startActivity(new Intent(this, Result.class));
        }
    }
}
