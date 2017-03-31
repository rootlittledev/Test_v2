package com.example.littledev.test_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;


class DBM extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "test";
    private static final String PRIMARY = "ID";
    private static String TABLE_NAME;
    private static String TEST;
    private static final String QUESTION = "Question";
    private static final String COL_1 = "Answer_1";
    private static final String COL_2 = "Answer_2";
    private static final String COL_3 = "Answer_3";
    private static final String COL_4 = "Answer_4";
    private static final String COL_5 = "Answer_5";
    private static final String TRUE = "True";
    private Context context;


    DBM(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;


    }
    public void setName(String table_name){
        TABLE_NAME = table_name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("create table " + TABLE_NAME +" (" + PRIMARY + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT," + COL_1 + " TEXT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + TRUE + " TEXT)");
    }

    void CreateTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("create table " + TABLE_NAME +" (" + PRIMARY + " INTEGER PRIMARY KEY AUTOINCREMENT," + QUESTION + " TEXT," + COL_1 + " TEXT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + TRUE + " TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    boolean insertData(String question, String answer_1, String answer_2, String answer_3, String answer_4, String answer_5, String correct) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(QUESTION,question);
        contentValues.put(COL_1,answer_1);
        contentValues.put(COL_2,answer_2);
        contentValues.put(COL_3,answer_3);
        contentValues.put(COL_4,answer_4);
        contentValues.put(COL_5,answer_5);
        contentValues.put(TRUE,correct);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        return result != -1;
    }
    public Cursor getTest() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
    }
    public void setTest(String name){
        TEST = name;

    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from "+TEST,null);
    }
    private void writeToSD() throws IOException {
        File sd = Environment.getExternalStorageDirectory();

        String DB_PATH = context.getDatabasePath(DATABASE_NAME).toString();

        if (sd.canWrite()) {
            String backupDBPath = "backupname.db";
            File currentDB = new File(DB_PATH, DATABASE_NAME);
            File backupDB = new File(sd, backupDBPath);

            if (currentDB.exists()) {
                FileChannel src = new FileInputStream(currentDB).getChannel();
                FileChannel dst = new FileOutputStream(backupDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        }
    }
}
