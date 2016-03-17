package com.example.asus.myapplication;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by ASUS on 12/22/2015.
 */
public class SendSchedule extends AsyncTask<String,Void,String> {

    Context ctx;

    SendSchedule(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        String sendurl="http://10.101.237.71/~towfiqurrahman/addshedule.php";

        if(method.equals("Send")){

            String courseid = params[1];
            String task = params[2];
            String chapter = params[3];
            String date = params[4];

            try {
                URL ulrn = new URL(sendurl);
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);
                OutputStream OS = con.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

                String data = URLEncoder.encode("courseid", "UTF-8") +"="+ URLEncoder.encode(courseid,"UTF-8")+"&"+
                        URLEncoder.encode("task","UTF-8") +"="+ URLEncoder.encode(task,"UTF-8")+"&"+
                        URLEncoder.encode("chapter","UTF-8") +"="+ URLEncoder.encode(chapter,"UTF-8")+"&"+
                        URLEncoder.encode("date","UTF-8") +"="+ URLEncoder.encode(date,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = con.getInputStream();
                IS.close();

                return  "Insertion SUccess";




            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
    }
}
