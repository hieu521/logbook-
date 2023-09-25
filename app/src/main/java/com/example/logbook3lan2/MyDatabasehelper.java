package com.example.logbook3lan2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;


import androidx.annotation.Nullable;

public class MyDatabasehelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "PersonLogbook3.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_student";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "student_name";
    private static final String COLUMN_DOB = "student_dob";
    private static final String COLUMN_EMAIL = "student_email";
    private static final String COLUMN_IMAGE_PATH = "student_image_path";
    MyDatabasehelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DOB + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_IMAGE_PATH + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addStudent(String name, String dob ,String email,String imagePath){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DOB, dob);
        cv.put(COLUMN_EMAIL,String.valueOf(email) );
        cv.put(COLUMN_IMAGE_PATH, imagePath);
        long result=db.insert(TABLE_NAME,null,cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }
    Cursor readAllData(){
        String query = " SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor = null;
        if ( db !=null){
            cursor=db.rawQuery(query, null);

        }
        return cursor;
    }
}
