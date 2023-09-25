package com.example.logbook3lan2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.logbook3lan2.AddActivity;
import com.example.logbook3lan2.CustomAdapter;
import com.example.logbook3lan2.MyDatabasehelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabasehelper myDB;
    ArrayList<String> student_id, student_name,student_dob,student_email,student_image;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
        myDB = new MyDatabasehelper(MainActivity.this);
        student_id =new ArrayList<>();
        student_name =new ArrayList<>();
        student_dob =new ArrayList<>();
        student_email =new ArrayList<>();
        student_image=new ArrayList<>();
        storeDataInArrays();
        customAdapter = new CustomAdapter(MainActivity.this, student_id, student_name,  student_dob,student_email,student_image);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }
    void storeDataInArrays (){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                student_id.add(cursor.getString(0));
                student_name.add(cursor.getString(1));
                student_dob.add(cursor.getString(2));
                student_email.add(cursor.getString(3));
                String imagePath = cursor.getString(4);
                student_image.add(imagePath);
            }
        }
    }

}