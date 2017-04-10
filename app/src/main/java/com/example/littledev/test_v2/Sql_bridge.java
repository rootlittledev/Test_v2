package com.example.littledev.test_v2;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

class Sql_bridge extends AsyncTask<String,Void,String> {
    private Context ctx;
    public static int acc_id;
    private String subject;
    private String test;

    Sql_bridge(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        AlertDialog alertDialog;
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Main Information....");
    }

    @Override
    protected String doInBackground(String... params) {
        Random random = new Random();
        acc_id = random.nextInt(999999 - 11111) + 11111;
        String reg_url = "http://e-shops.hol.es/app_register";
        String log_url = "http://e-shops.hol.es/app_login";
        String get_test = "http://e-shops.hol.es/app_test";
        String get_subject = "http://e-shops.hol.es/app_subject";
        String method = params[0];
        switch (method) {
            case "register":
                String name = params[1];
                String surname = params[2];
                String password = params[4];
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("surname", "UTF-8") + "=" + URLEncoder.encode(surname, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&" +
                            URLEncoder.encode("acc_id", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(acc_id), "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "login":
                String login_name = params[1];
                String login_pass = params[2];
                try {
                    URL url = new URL(log_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("login", "UTF-8") + "=" + URLEncoder.encode(login_name, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(login_pass, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;


                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case "test": {
                String response = "";
                try {
                    URL url = new URL(get_test);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("test", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                test = response;
                return "test";
            }
            case "subject": {
                String response = "";
                try {
                    URL url = new URL(get_subject);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("Test", "UTF-8") + "=" + URLEncoder.encode(params[0], "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                subject = response;
                return "subject";
            }
            case "result":
                String rName = params[1];
                String rScore = params[3];
                try {
                    URL url = new URL(reg_url);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(rName, "UTF-8") + "&" +
                            URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(rScore, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String response = "";
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return response;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        switch (result) {
            case "Registered":
                Toast.makeText(ctx, R.string.registered, Toast.LENGTH_LONG).show();
                if (ctx instanceof Register) {
                    ((Register) ctx).onRegisterSuccess();
                }
                break;
            case "Login":
                Toast.makeText(ctx, R.string.logged, Toast.LENGTH_LONG).show();
                if (ctx instanceof Login) {
                    ((Login) ctx).onLoginSuccess();
                }
                break;
            case "test":
                List<String> questions = new ArrayList<>();
                List<String> answer_1 = new ArrayList<>();
                List<String> answer_2 = new ArrayList<>();
                List<String> answer_3 = new ArrayList<>();
                List<String> answer_4 = new ArrayList<>();
                List<String> answer_5 = new ArrayList<>();
                List<String> correct = new ArrayList<>();
                JSONObject jsonResponse;
                try {
                    jsonResponse = new JSONObject(test);
                    JSONArray jsonMainNode = jsonResponse.optJSONArray("result");
                    int lengthJsonArr = jsonMainNode.length();
                    for(int i=0; i < lengthJsonArr; i++)
                    {
                        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                        questions.add(jsonChildNode.optString("question"));
                        answer_1.add(jsonChildNode.optString("answer_1"));
                        answer_2.add(jsonChildNode.optString("answer_2"));
                        answer_3.add(jsonChildNode.optString("answer_3"));
                        answer_4.add(jsonChildNode.optString("answer_4"));
                        answer_5.add(jsonChildNode.optString("answer_5"));
                        correct.add(jsonChildNode.optString("correct"));

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                HashMap<String, List<String>> testM = new HashMap<>();
                testM.put("question",questions);
                testM.put("answer_1",answer_1);
                testM.put("answer_2",answer_2);
                testM.put("answer_3",answer_3);
                testM.put("answer_4",answer_4);
                testM.put("answer_5",answer_5);
                testM.put("correct",correct);
                Start.setTest(testM);
                if (ctx instanceof Start) {
                    ((Start) ctx).questionManager();
                }
                break;

            case "subject":
                List<String> subject = new ArrayList<>();
                JSONObject jsonResponseS;
                try {
                    jsonResponseS = new JSONObject(this.subject);
                    JSONArray jsonMainNodeS = jsonResponseS.optJSONArray("result");
                    int lengthJsonArr = jsonMainNodeS.length();
                    for(int i=0; i < lengthJsonArr; i++)
                    {
                        JSONObject jsonChildNodeS = jsonMainNodeS.getJSONObject(i);
                        subject.add(jsonChildNodeS.optString("subject"));
                        Log.i("test",subject.get(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Choose.setSubjects(subject);
                if (ctx instanceof Choose) {
                    ((Choose) ctx).getTests();
                }
                break;
            case "inserted":
                Toast.makeText(ctx, R.string.insert, Toast.LENGTH_LONG).show();
        }


    }

    boolean isConnected() throws InterruptedException, IOException {
        String command = "ping -c 1 google.com";
        return (Runtime.getRuntime().exec(command).waitFor() == 0);
    }
}
