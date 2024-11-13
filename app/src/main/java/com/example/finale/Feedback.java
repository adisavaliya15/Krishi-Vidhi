package com.example.finale;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finale.User.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Feedback extends AppCompatActivity {

    String uid;
    EditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        User user = SharedPrefManager.getInstance(this).getUser();
        uid = user.getLOGIN_ID();

        Button btn = (Button)findViewById(R.id.button5);
        txt = (EditText)findViewById(R.id.editTextTextPersonName);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt.getText().toString().trim().equalsIgnoreCase("")) {
                    Toast.makeText(getApplicationContext(), "Please suggest us", Toast.LENGTH_LONG).show();
                }
                else{
                   adddata();
                }


            }
        });
    }


    public void adddata()
    {

        String comment = txt.getText().toString();

        final ProgressDialog pd = new ProgressDialog(Feedback.this);
        pd.setMessage("Please Wait...");
        pd.show();

        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        pd.dismiss();
                    }
                }, 2000);

        class UserLogin extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Feedback.this,Thanks.class);
                        startActivity(intent);

                    } else {

                        Toast.makeText(getApplicationContext(),obj.getString("message"), Toast.LENGTH_SHORT).show();
                        // Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                //creating request handler object
                RequestHandler requestHandler = new RequestHandler();

                //creating request parameters
                HashMap<String, String> params = new HashMap<>();
                params.put("uid",uid);
                params.put("rate",comment);
                return requestHandler.sendPostRequest(URLs.URL_ADDFB, params);
            }
        }

        UserLogin ul = new UserLogin();
        ul.execute();

    }
}