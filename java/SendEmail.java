package com.example.asus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class SendEmail extends AppCompatActivity {


    String subject, emailid, message;
    EditText etsub,etemail,etmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);
        etemail = (EditText)findViewById(R.id.etemail);
        etsub = (EditText)findViewById(R.id.etemailsub);
        etmsg = (EditText)findViewById(R.id.etemailmsg);

    }
        public void sendemail(View view){

            emailid = etemail.getText().toString();
            subject = etsub.getText().toString();
             message =       etmsg.getText().toString();

            Intent emailintent = new Intent(Intent.ACTION_SEND);
            emailintent.putExtra(Intent.EXTRA_EMAIL, new String[]{emailid});
            emailintent.putExtra(Intent.EXTRA_SUBJECT,subject);
            emailintent.setType("plain/text");

            emailintent.putExtra(Intent.EXTRA_TEXT,message);
            startActivity(emailintent);
        }
}
