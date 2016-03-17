package com.example.asus.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class C_Cregister extends Activity{
    EditText staffid, courseid,email,pass;

    String s1,s2,s3,s4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c__cregister);
        Button button = (Button)findViewById(R.id.buttonreg);

        staffid = (EditText)findViewById(R.id.etstaffid);
        courseid =(EditText)findViewById(R.id.etcourseid);
        email =(EditText)findViewById(R.id.etstaffemail);
        pass =(EditText)findViewById(R.id.etpassword);

    }

    public void adminReg(View v){
        s1 = staffid.getText().toString();
        s2 = courseid.getText().toString();
        s3 = email.getText().toString();
        s4 = pass.getText().toString();
        String method = "register";

        BackgroundTaskAdmin backgroundTaskAdmin = new BackgroundTaskAdmin(this);
        backgroundTaskAdmin.execute(method, s1, s2, s3, s4);
    finish();
    }

}
