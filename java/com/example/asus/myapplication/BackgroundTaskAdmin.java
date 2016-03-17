package com.example.asus.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;


/**
 * Created by ASUS on 12/12/2015.
 */
public class BackgroundTaskAdmin extends AsyncTask<String, Void, String> {

AlertDialog alertDialog;

    Context ctx;



    BackgroundTaskAdmin(Context ctx)
    {
        this.ctx = (Context) ctx;
    }

    @Override
    protected void onPreExecute() {

        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle(("Login Info"));
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        String saveurl="http://10.101.237.71/~towfiqurrahman/AdminInfo1.php";
        String logurl="http://10.101.237.71/~towfiqurrahman/login1.php";

        if(method.equals("register"))
        {

            String staffid = params[1];
            String courseid = params[2];
            String emailid = params[3];
            String password = params[4];

            try {
                URL ulrn = new URL(saveurl);
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                con.setRequestMethod("POST");
                con.setDoOutput(true);

                OutputStream OS = con.getOutputStream();

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("staffid","UTF-8")+"="+ URLEncoder.encode(staffid,"UTF-8")+"&"+
                        URLEncoder.encode("courseid","UTF-8")+"="+ URLEncoder.encode(courseid,"UTF-8")+"&"+
                        URLEncoder.encode("email","UTF-8") +"="+ URLEncoder.encode(emailid,"UTF-8")+"&"+
                        URLEncoder.encode("pass","UTF-8") +"="+ URLEncoder.encode(password,"UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream IS = con.getInputStream();
                IS.close();

                return  "Insertion SUccess";



            } catch (MalformedURLException e) {

                e.printStackTrace();

            }
            catch (IOException j) {
                j.printStackTrace();
            }


        }


        else if(method.equals("login")){
            String login_name = params[1];
            String login_pass = params[2];


            try {
                URL url = new URL(logurl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
          OutputStream outputStream = connection.getOutputStream();
           BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                 String data = URLEncoder.encode("login_name","UTF-8")+"="+ URLEncoder.encode(login_name,"UTF-8")+"&"+
              URLEncoder.encode("login_pass","UTF-8") +"="+ URLEncoder.encode(login_pass,"UTF-8");




                 bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( inputStream,"iso-8859-1"));
                String response = "";
                String line = "";

               while ((line = bufferedReader.readLine()) != null){
                   response += line;
               }

                bufferedReader.close();
                inputStream.close();
                connection.disconnect();
               String result =response;

                if(result != null){

                    Intent intent = new Intent(ctx, ConductorHome.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    ctx.startActivity(intent);


                }else{


                }
                return response;

            } catch (MalformedURLException e) {
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

        if(result.equals("Insertion SUccess")){
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }else
        {
            alertDialog.setMessage(result);
            alertDialog.show();

            Intent intent;
            intent = new Intent(ctx, ConductorHome.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("courseid",result);

            ctx.startActivity(intent);

        }

    }


}
