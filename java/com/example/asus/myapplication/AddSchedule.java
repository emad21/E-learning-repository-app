package com.example.asus.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddSchedule extends AppCompatActivity {

    TextView result,chapter,tvid,tvdate ;
    String radioSelect,cId,text,task,tdate;
    EditText courseid;
    Button btnDate;
    int year_x ,month_x,date_x;
    static  final int DILOG_ID =0;
    EditText edtid;

    ArrayList<String> selection = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);

        tvdate = (TextView)findViewById(R.id.textView16);
        tvid = (TextView)findViewById(R.id.textView15);
        edtid = (EditText)findViewById(R.id.edtID);
        chapter = (TextView)findViewById(R.id.textChapters);
        result = (TextView)findViewById(R.id.textTask);

        courseid = (EditText)findViewById(R.id.edtID);

        result.setEnabled(false);
        chapter.setEnabled(false);
        tvdate.setEnabled(false);
        tvid.setEnabled(false);


        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        date_x = cal.get(Calendar.DAY_OF_MONTH);

        showDialogonButtonClick();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


        public void showDialogonButtonClick(){
            btnDate = (Button)findViewById(R.id.btnDate);

            btnDate.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        showDialog(DILOG_ID);
                        }
                    }

            );
        }

    @Override
    protected Dialog onCreateDialog(int id){

        if(id == DILOG_ID)
            return new DatePickerDialog(this,dpickerListener,year_x,month_x,date_x);
        return null;

    }


    public DatePickerDialog.OnDateSetListener dpickerListener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        year_x = year;
        month_x = monthOfYear+1;
        date_x = dayOfMonth;

        Toast.makeText(AddSchedule.this, year_x + "/" + month_x + "/" + date_x ,Toast.LENGTH_LONG).show();

    }
};

    public void selectTask(View v){
        boolean checked = ((RadioButton)v).isChecked();

        switch (v.getId()){

            case R.id.task_quiz:
                radioSelect = "Quiz";
                result.setText(radioSelect);
                result.setEnabled(true);
                break;

            case R.id.task_asnmnt:
                radioSelect = "Assignment";
                result.setText(radioSelect);
                result.setEnabled(true);
                break;

            case R.id.task_mid:
                radioSelect = "Midterm Examination";
                result.setText(radioSelect);
                result.setEnabled(true);
                break;

            case R.id.task_nocls:
                radioSelect = "No Class";
                result.setText(radioSelect);
                result.setEnabled(true);
                break;



        }

    }

    public void selectChapter(View view){
        boolean checked = ((CheckBox)view).isChecked();

        switch (view.getId()){
            case R.id.chapter1:
                if(checked)
                {selection.add("Chapter1");}
                else {
                    selection.remove("Chapter1");
                }
                break;

            case R.id.chapter2:
                if(checked)
                {selection.add("Chapter2");}
                else {
                    selection.remove("Chapter2");
                }
                break;

            case R.id.chapter3:
                if(checked)
                {selection.add("Chapter3");}
                else {
                    selection.remove("Chapter3");
                }
                break;
            case R.id.chapter4:
                if(checked)
                {selection.add("Chapter4");}
                else {
                    selection.remove("Chapter4");
                }
                break;
            case R.id.chapter5:
                if(checked)
                {selection.add("Chapter5");}
                else {
                    selection.remove("Chapter5");
                }
                break;
        }
    }

    public void finalsubmit(View view){

        cId = edtid.getText().toString();

        String final_chapter = "";

        for(String Selection:selection){
            final_chapter = final_chapter + Selection +"\n";
        }

        cId = edtid.getText().toString();
        task = radioSelect;
        text = final_chapter;
        tdate =  year_x + "/" + month_x + "/" + date_x ;


        String method = "Send";
        SendSchedule sendSchedule = new SendSchedule(this);
        sendSchedule.execute(method,cId,task,text,tdate);
        finish();


        chapter.setText(text);
        chapter.setEnabled(true);
        tvid.setText(cId);
        tvid.setEnabled(true);
        tvdate.setText(tdate);
        tvdate.setEnabled(true);

    }





}
