package com.example.finale;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finale.User.User;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class    login extends AppCompatActivity {
    Button callsignup, login_btn, forgot_btn;
    ImageView image;
    TextView logotext, slogan_text;
    TextInputLayout phoneno, Password;
    String phone , pass;
    private static long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setEnterTransition(null);
        getWindow().setExitTransition(null);

        /*if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Dashboard.class));
        }*/
        //Hooks
        callsignup = findViewById(R.id.forsignup);
        image = findViewById(R.id.logoimg);
        logotext = findViewById(R.id.logoname);
        slogan_text = findViewById(R.id.slogan);
        phoneno = findViewById(R.id.phone_no);
        Password = findViewById(R.id.password);
        forgot_btn = findViewById(R.id.forg_pass);
        login_btn = findViewById(R.id.go_login);

        forgot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this, forget_pass.class);
                startActivity(i);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) { // 1000 = 1second
//                    return;
//                }
//                mLastClickTime = SystemClock.elapsedRealtime();
//                Intent intent = new Intent(login.this,Dashboard.class);
//                startActivity(intent);

                if( !validatePassword() | !validatePhoneNo()){
                    return;
                }
                else {
                    login();
                }
            }
        });

        callsignup.setOnClickListener(v -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 2000) { // 1000 = 1second
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            Intent signup = new Intent(login.this, SignUp.class);

            Pair[] pairs = new Pair[8];

            pairs[0] = new Pair<View, String>(image, "one");
            pairs[1] = new Pair<View, String>(logotext, "two");
            pairs[2] = new Pair<View, String>(slogan_text, "three");
            pairs[3] = new Pair<View, String>(phoneno, "four");
            pairs[4] = new Pair<View, String>(Password, "five");
            pairs[5] = new Pair<View, String>(forgot_btn, "six");
            pairs[6] = new Pair<View, String>(login_btn, "seven");
            pairs[7] = new Pair<View, String>(callsignup, "eight");
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(login.this, pairs);
            startActivity(signup, options.toBundle());
        });

    }
    private Boolean validateEmail() {
        String val = phoneno.getEditText().getText().toString();
        String phonePattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";

        if (val.isEmpty()) {
            phoneno.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(phonePattern)) {
            phoneno.setError("Invalid phone address");
            return false;
        } else {
            phoneno.setError(null);
            phoneno.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        String val = phoneno.getEditText().getText().toString();

        if (val.isEmpty()) {
            phoneno.setError("Field cannot be empty");
            return false;
        } else {
            phoneno.setError(null);
            phoneno.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String password = Password.getEditText().getText().toString();
        String passwordVal = "^" +
             /*   "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +  */       //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (password.isEmpty()) {
            Password.setError("Field cannot be empty");
            return false;
        } else if (!password.matches(passwordVal)) {
            Password.setError("Password is too weak");
            return false;
        } else {
            Password.setError(null);
            Password.setErrorEnabled(false);
            return true;
        }
    }

    public void login() {
        if (!validate()) {
            onLoginFailed();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(login.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
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
    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }
    public void onLoginSuccess()
    {
        phone = phoneno.getEditText().getText().toString();
        pass = Password.getEditText().getText().toString();

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

                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                        //getting the user from the response
                        JSONObject userJson = obj.getJSONObject("user");
                        //creating a new user object
                        User user = new User(
                                userJson.getString("LOGIN_ID"),
                                userJson.getString("USERNAME"),
                                userJson.getString("EMAIL_ID"),
                                userJson.getString("PASSWORD"),
                                userJson.getString("PHONE_NO")
                        );

                        Log.e("phone",userJson.getString("EMAIL_ID"));
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);
                        Intent Page = new Intent(login.this, Dashboard.class);
                        startActivity(Page);

                        //starting the profile activity
                    } else {
                        Toast.makeText(getApplicationContext(), String.valueOf(obj.getString("message")), Toast.LENGTH_SHORT).show();
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
                params.put("phone", phone);
                params.put("password", pass);

                Log.e("pass",pass);
                //returing the response
                return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
            }
        }
        UserLogin ul = new UserLogin();
        ul.execute();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
    }

    public boolean validate() {

        boolean valid = true;
        phone = phoneno.getEditText().getText().toString();
        pass = Password.getEditText().getText().toString();

        if (phone.isEmpty() || phone.length() < 10) {
            phoneno.setError("enter a valid phone number");
            valid = false;
        } else {
            phoneno.setError(null);
        }

        if (pass.isEmpty() || pass.length() < 4 || pass.length() > 25) {
            Password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
            requestFocus(Password);
        } else {
            requestFocus(Password);
            Password.setError(null);
        }

        return valid;
    }
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
