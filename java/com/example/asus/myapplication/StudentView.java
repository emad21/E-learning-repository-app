package com.example.asus.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentView extends AppCompatActivity {
    ListView listView;
    String jsonResult;
    String url="http://10.101.237.71/~towfiqurrahman/access.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_view);
        listView = (ListView) findViewById(R.id.listView1);
        TextView tvDisp = (TextView)findViewById(R.id.txtsetname);
        Intent intent = getIntent();
        String cid = intent.getStringExtra("User Name:");
        tvDisp.setText(cid);
        accessWebService();
    }

    // Async Task to access the web
    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                URL ulrn = new URL(url);
                HttpURLConnection con = (HttpURLConnection) ulrn.openConnection();
                InputStream response = con.getInputStream();
                jsonResult = inputStreamToString(response).toString();
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            }
            catch (IOException e) {

                // e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error..." + e.toString(),
                        Toast.LENGTH_LONG).show();
            }
            return answer;
        }
        @Override
        protected void onPostExecute(String result) {

            ListDrawer();
        }
    }

    // end async task

    public void accessWebService() {
        JsonReadTask task = new JsonReadTask();
        // passes values for the urls string array
        task.execute(new String[]{url});
    }

    // build hash set for list view
    public void ListDrawer() {
        List<Map<String, String>> studentList = new ArrayList<Map<String, String>>();
        try {
            JSONObject jsonResponse = new JSONObject(jsonResult);
            JSONArray jsonMainNode = jsonResponse.optJSONArray("stu_info");
            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);

                String number = jsonChildNode.optString("courseid");
                String title = jsonChildNode.optString("task");
                String author = jsonChildNode.optString("chapter");
                String email = jsonChildNode.optString("date");

                String outPut ="Course-code:"+ number + "\n" +"Activity:"+ title + "\n"+"Chapters:" + author + "\n"+"Date:" + email;
                studentList.add(createStudent("students", outPut));
            }
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, studentList,
                android.R.layout.simple_list_item_1,
                new String[] { "students" }, new int[] { android.R.id.text1 });
        listView.setAdapter(simpleAdapter);
    }

    private HashMap<String, String> createStudent(String name, String number) {
        HashMap<String, String> studentNameNo = new HashMap<String, String>();
        studentNameNo.put(name, number);
        return studentNameNo;
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

    public void SendEmail(View view){
        Intent emailintent = new Intent(this,SendEmail.class);
        startActivity(emailintent);
    }


}
