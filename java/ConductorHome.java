package com.example.asus.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class ConductorHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor_home);
        CalendarView myCalendarView = (CalendarView) findViewById(R.id.calendar);
        TextView tvDisp = (TextView)findViewById(R.id.textView6);

        Intent intent = getIntent();
        String cid = intent.getStringExtra("courseid");
        tvDisp.setText(cid);

        myCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {

                Toast.makeText(getApplicationContext(),
                        "Year: " + year + "\n" +
                                "Month: " + (month+1) + "\n" +
                                "Day of Month: " + dayOfMonth, Toast.LENGTH_LONG).show();


            }
        });
    }

   public void addshed(View v){


       Intent intent;
         intent = new Intent(this,AddSchedule.class);
       startActivity(intent);
   }




}
