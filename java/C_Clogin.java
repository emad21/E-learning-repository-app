package com.example.asus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class C_Clogin extends AppCompatActivity {

    EditText etid ,etpas;
    String s1 ,s2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__clogin);
        etid = (EditText)findViewById(R.id.etid);
        etpas = (EditText)findViewById(R.id.etpass);
    }



    public void adminLogin(View view){

        s1 = etid.getText().toString();
        s2 = etpas.getText().toString();
        String method = "login";
        BackgroundTaskAdmin backgroundTaskAdmin = new BackgroundTaskAdmin(this);
        backgroundTaskAdmin.execute(method,s1,s2);

    }


}
