package com.example.finale;

import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class weatherData {

    private String mTemperature, micon, mcity, mWeatherType, mwind, mlastupdated, msunrise, msunset, mrain;
    private int mCondition;




    public static com.example.finale.weatherData fromJson(JSONObject jsonObject) {
        try {

            com.example.finale.weatherData weatherD = new com.example.finale.weatherData();
            weatherD.mcity = jsonObject.getString("name");
            weatherD.mCondition = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherD.mWeatherType = jsonObject.getJSONArray("weather").getJSONObject(0).getString("main");

            String mlastupdated = jsonObject.getString("dt");
            long dv = Long.parseLong(mlastupdated)*1000;
            Date df = new java.util.Date(dv);
            weatherD.mlastupdated = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.ENGLISH).format(df);

            String msunrise = jsonObject.getJSONObject("sys").getString("sunrise");
            long sr = Long.parseLong(msunrise)*1000;
            Date sro = new java.util.Date(sr);
            weatherD.msunrise = new SimpleDateFormat("hh:mm:ss", Locale.ENGLISH).format(sro);

            String msunset = jsonObject.getJSONObject("sys").getString("sunset");
            long ss = Long.parseLong(msunset)*1000;
            Date sso = new java.util.Date(ss);
            weatherD.msunset = new SimpleDateFormat("hh:mm:ss", Locale.ENGLISH).format(sso);


            double windResult = jsonObject.getJSONObject("wind").getDouble("speed") * 3.6;
            int rou = (int) Math.rint(windResult);
            weatherD.mwind = Integer.toString(rou);

            if(jsonObject.has("rain")){
               weatherD.mrain = jsonObject.getJSONObject("rain").getString("1h");

            }

            weatherD.micon = updateWeatherIcon(weatherD.mCondition);
            double tempResult = jsonObject.getJSONObject("main").getDouble("temp") - 272.15;
            int roundedValue = (int) Math.rint(tempResult);
            weatherD.mTemperature = Integer.toString(roundedValue);
            return weatherD;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }


    private static String updateWeatherIcon(int condition) {
        if (condition >= 0 && condition <= 300) {
            return "ic_four";
        } else if (condition >= 300 && condition <= 500) {
            return "ic_one";
        }  else if (condition >= 500 && condition <= 520) {
            return "ic_two";
        }  else if (condition >= 521 && condition <= 600) {
            return "ic_seven";
        }  else if (condition >= 601 && condition <= 700) {
            return "ic_one";
        } else if (condition >= 701 && condition <= 780) {
            return "ic_eight";
        } else if (condition >= 781 && condition <= 799) {
            return "ic_five";
        } else if (condition == 800) {
            return "ic_three";
        } else if (condition >= 801 && condition <= 804) {
            return "ic_six";
        } else if (condition >= 900 && condition <= 902) {
            return "ic_two";
        }
        if (condition == 903) {
            return "ic_one";
        }
        if (condition == 904) {
            return "ic_three";
        }
        if (condition >= 905 && condition <= 1000) {
            return "ic_two";
        }

        return "dunno";


    }

    public String getmTemperature() {
        return mTemperature + "°C";
    }

    public String getMicon() {
        return micon;
    }

    public String getMcity() {
        return mcity;
    }

    public String getMlastupdated() {
        return "Last updated: "+mlastupdated;
    }

    public String getMsunrise() {
        return msunrise;
    }

    public String getMsunset() {
        return msunset;
    }

    public String getmWeatherType() {
        return "વાતાવરણ: "+ mWeatherType;
    }

    public String getMwind() {
        return "પવન ની ગતી: "+ mwind + " km/h";
    }

    public String getMrain() {
        if(mrain!=null){
            return mrain + " mm";
        } else{return "No Rain Here..!";

        }
    }

}
