package com.example.finale;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class weather extends MyBaseActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    TextView textView;
    private LocationRequest locationRequest;
    public static final int REQUEST_CHECK_SETTING = 1001;

    final String APP_ID = "YOUR_API_KEY";
    final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather";
    final long MIN_TIME = 5000;
    final float MIN_DISTANCE = 1000;
    final int REQUEST_CODE = 101;

    String Location_Provider = LocationManager.NETWORK_PROVIDER;

    TextView NameofCity, weatherState, Temperature, Wind, LUDate, Sunrise, Sunset, Rain1h, Rain3h;
    RelativeLayout mweatherIcon;

    LocationManager mLocationManager;
    LocationListener mLocationListner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_weather, null, false);
        drawer.addView(v, 0);
        setTitle("હવામાન");
        {

            swipeRefreshLayout = findViewById(R.id.refreshLayout);
            textView = findViewById(R.id.textView);
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    //your code on swipe refresh
                    //we are checking networking connectivity
                    Intent mIntent = getIntent();
                    String city = mIntent.getStringExtra("City");
                    if (city != null) {
                        getWeatherForNewCity(city);
                    } else {
                        getWeatherForCurrentLocation();
                    }

                    swipeRefreshLayout.setRefreshing(false);
                }

            });
            swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
        }


        /*refreshLayout = findViewById(R.id.refreshlayout);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });*/

        final EditText editText = findViewById(R.id.searchCity);
        weatherState = findViewById(R.id.weatherCondition);
        Temperature = findViewById(R.id.temperature);
        mweatherIcon = findViewById(R.id.weatherIcon);
        NameofCity = findViewById(R.id.cityName);
        Wind = findViewById(R.id.wind);
        LUDate = findViewById(R.id.lastupdated);
        Sunrise = findViewById(R.id.sunrise);
        Sunset = findViewById(R.id.sunset);
        Rain1h = findViewById(R.id.rain1h);
        Rain3h = findViewById(R.id.rain3h);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {


            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String newCity = editText.getText().toString();
                Intent intent = new Intent(weather.this, weather.class);
                intent.putExtra("City", newCity);
                startActivity(intent);

                return true;
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent mIntent = getIntent();
        String city = mIntent.getStringExtra("City");
        if (city != null) {
            getWeatherForNewCity(city);
        } else {
            getWeatherForCurrentLocation();
        }


    }

    private void getWeatherForNewCity(String city) {
        RequestParams params = new RequestParams();
        params.put("q", city);
        params.put("appid", APP_ID);
        letsdoSomeNetworking(params);

    }

    private void getWeatherForCurrentLocation() {

        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        mLocationListner = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                String Latitude = String.valueOf(location.getLatitude());
                String Longitude = String.valueOf(location.getLongitude());

                RequestParams params = new RequestParams();
                params.put("lat", Latitude);
                params.put("lon", Longitude);
                params.put("appid", APP_ID);
                letsdoSomeNetworking(params);

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

                locationRequest = LocationRequest.create();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                locationRequest.setInterval(5000);
                locationRequest.setFastestInterval(2000);

                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
                builder.setAlwaysShow(true);

                Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext()).checkLocationSettings(builder.build());
                result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                        try {
                            LocationSettingsResponse response = task.getResult(ApiException.class);
                        } catch (ApiException e) {
                            switch (e.getStatusCode()) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                                    try {
                                        ResolvableApiException resolvableApiException = (ResolvableApiException) e;
                                        resolvableApiException.startResolutionForResult(weather.this, REQUEST_CHECK_SETTING);
                                    } catch (IntentSender.SendIntentException sendIntentException) {
                                        sendIntentException.printStackTrace();
                                    }
                                    break;
                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    break;
                            }
                        }
                    }
                });

            }
        };


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        mLocationManager.requestLocationUpdates(Location_Provider, MIN_TIME, MIN_DISTANCE, mLocationListner);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHECK_SETTING) {

            switch (resultCode) {
                case Activity.RESULT_OK:
                    break;

                case Activity.RESULT_CANCELED:
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(weather.this, "Location get Succesffully", Toast.LENGTH_SHORT).show();
                getWeatherForCurrentLocation();
            } else {
                //user denied the permission
                Toast.makeText(weather.this, "Location not get Succesffully", Toast.LENGTH_SHORT).show();
            }
        }

    }


    private void letsdoSomeNetworking(RequestParams params) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(WEATHER_URL, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                weatherData weatherD = weatherData.fromJson(response);
                updateUI(weatherD);


                // super.onSuccess(statusCode, headers, response);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });


    }

    private void updateUI(weatherData weather) {


        Temperature.setText(weather.getmTemperature());
        NameofCity.setText(weather.getMcity());
        weatherState.setText(weather.getmWeatherType());
        Wind.setText(weather.getMwind());
        LUDate.setText(weather.getMlastupdated());
        Sunrise.setText(weather.getMsunrise());
        Sunset.setText(weather.getMsunset());
        int resourceID = getResources().getIdentifier(weather.getMicon(), "drawable", getPackageName());
        mweatherIcon.setBackgroundResource(resourceID);
        if (Rain1h != null) {
            Rain1h.setText(weather.getMrain());
        } else {

            Rain1h.setText("Rain");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mLocationListner);
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(weather.this, Dashboard.class);
        startActivity(intent1);
    }
}