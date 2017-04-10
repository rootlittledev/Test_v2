package com.example.littledev.test_v2;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Locale;

public class Login extends AppCompatActivity {

    public static String login;
    public static String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        login = "";
        pass = "";
        showId();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lenguage_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        String languageToLoad;
        Locale locale;
        Configuration config;
        Intent refresh = new Intent(Login.this, Login.class);
        switch (item.getItemId()) {
            case R.id.english:
                languageToLoad = "en"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                startActivity(refresh);
                Toast.makeText(this, R.string.changed_to_english, Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.russian:
                languageToLoad = "ru"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                startActivity(refresh);
                Toast.makeText(this, R.string.changed_to_russian, Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.ukrainian:
                languageToLoad = "uk"; // your language
                locale = new Locale(languageToLoad);
                Locale.setDefault(locale);
                config = new Configuration();
                config.locale = locale;
                getBaseContext().getResources().updateConfiguration(config,
                        getBaseContext().getResources().getDisplayMetrics());
                startActivity(refresh);
                Toast.makeText(this, R.string.changed_to_ukrainian, Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_settings:
                Toast.makeText(this, R.string.settings, Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }

    public void onLoginSuccess() {
        startActivity(new Intent(Login.this, Main.class));
        finish();
    }

    public void showId(){
        Sql_bridge bridge = new Sql_bridge(this);
        if(Sql_bridge.acc_id != 0) {
            TextView id_acc = (TextView) findViewById(R.id.registered_id);
            TextView id_label = (TextView) findViewById(R.id.registered_id_label);
            id_acc.setVisibility(View.VISIBLE);
            id_label.setVisibility(View.VISIBLE);
            id_acc.setText(Integer.toString(bridge.acc_id));
            Sql_bridge.acc_id = 0;
        }
    }

    public void onLogin(View view) throws IOException, InterruptedException {
        EditText eLogin = (EditText) findViewById(R.id.username);
        EditText ePass = (EditText) findViewById(R.id.password);
        login = eLogin.getText().toString();
        pass = ePass.getText().toString();
        Sql_bridge bridge = new Sql_bridge(this);
        if(bridge.isConnected()){
            bridge.execute("login",login,pass);
        }
        else
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_LONG).show();

    }

    public void onRegister(View view) {
        startActivity(new Intent(this, Register.class));
    }
}
