package com.example.finale;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finale.User.User;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class forget_pass extends AppCompatActivity {

    TextInputLayout fullName, email, phone, password;
    TextView fullNameLable, usernameLable;
    String uid;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        setTitle("પ્રોફાઇલ");


        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phone = findViewById(R.id.phone_profile);
        password = findViewById(R.id.password_profile);
        fullNameLable = findViewById(R.id.fullname_field);
        edit = findViewById(R.id.btnupdate);

        User user = SharedPrefManager.getInstance(this).getUser();
        uid = user.getLOGIN_ID();
        String pass = user.getPASSWORD();
        String phoneno = user.getPHONE_NO();
/*
        Log.e("asd",emailid);
        fullNameLable.setText(name);
        fullName.getEditText().setText(name);
        email.getEditText().setText(emailid);*/
        phone.getEditText().setText(phoneno);
        password.getEditText().setText(pass);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                forgetpassbtn();

            }
        });
    }

    public void forgetpassbtn() {

        final ProgressDialog progressDialog = new ProgressDialog(forget_pass.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Updating..");
        progressDialog.show();



        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }



 /*   @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }*/

    public void onLoginSuccess()
    {

        String u_phoneNo = phone.getEditText().getText().toString();
        String u_password = password.getEditText().getText().toString();

        class ForgetPassword extends AsyncTask<Void, Void, String> {

            ProgressBar progressBar;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
/*
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s!=null){
                    // Do you work here on success
                }else{
                    // null response or Exception occur
                }
            }
*/
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    //converting response to json object
                    JSONObject obj = new JSONObject(s);

                    //if no error in response
                    if (!obj.getBoolean("error")) {

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                        Intent Page = new Intent(forget_pass.this, login.class);
                        startActivity(Page);

                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

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
                params.put("password", u_password);
                params.put("contact", u_phoneNo);
                params.put("id", uid);

                Log.e("pass",u_password);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_FORGETPASS, params);
            }
        }

        ForgetPassword ul = new ForgetPassword();
        ul.execute();


    }


}