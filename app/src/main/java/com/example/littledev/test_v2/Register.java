package com.example.littledev.test_v2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegisterUser(View view){
        EditText nameF = (EditText) findViewById(R.id.registration_name);
        EditText surnameF = (EditText) findViewById(R.id.registration_surname);
        EditText emailF = (EditText) findViewById(R.id.registration_email);
        EditText passwordF = (EditText) findViewById(R.id.registration_password);
        String name = nameF.getText().toString();
        String surname = surnameF.getText().toString();
        String email = emailF.getText().toString();
        String password = passwordF.getText().toString();
        Sql_bridge registration = new Sql_bridge(this);
        registration.execute("register",name, surname, email, password);
    }

    public void showPassword(View view){
        boolean isChecked = ((CheckBox) view).isChecked();
        EditText passwordField = (EditText) findViewById(R.id.registration_password);
        if (isChecked) {
            passwordField.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            passwordField.setSelection(passwordField.getText().length());
        }else {
            passwordField.setInputType(InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);

            passwordField.setSelection(passwordField.getText().length());
        }

    }

    public void onRegisterSuccess() {
        startActivity(new Intent(Register.this, Login.class));
        finish();
    }
}
