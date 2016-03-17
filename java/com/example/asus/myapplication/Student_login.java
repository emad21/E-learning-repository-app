package com.example.asus.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class Student_login extends AppCompatActivity {
    EditText etid ,etpas;
    String s1 ,s2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        etid = (EditText)findViewById(R.id.edtID);
        etpas = (EditText)findViewById(R.id.edtpass);
    }


    public void studentLogin(View view){

        s1 = etid.getText().toString();
        s2 = etpas.getText().toString()     ;

        String method = "login";

        BackgroundTaskStudent backgroundTaskStudent = new BackgroundTaskStudent(this);
        backgroundTaskStudent.execute(method,s1,s2);



    }

}
