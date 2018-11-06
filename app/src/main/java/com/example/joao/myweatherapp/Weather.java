package com.example.joao.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Weather extends AppCompatActivity {

    Button test;
    private String tag = "QueueTag";
    TextView tvCurrentTemp;
    String url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=Winnipeg&format=json&num_of_days=5";
    private RequestQueue mReqQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        test= findViewById(R.id.btnTest);
        mReqQueue = Volley.newRequestQueue(this);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callAPI();
            }
        });

    }

    private void callAPI() {
        final TextView mTextView = (TextView) findViewById(R.id.mTextView);


        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        mTextView.setText("Response is: "+ response.substring(0,500));
                        Dataclass dataResponse;

                        try {
                            Gson gson = new Gson();
                            dataResponse = gson.fromJson(response, Dataclass.class);
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();

                        }

                        try {
                            JSONObject obj = new JSONObject(response);
                            obj.getString("temp_C");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mTextView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    class Dataclass{
        public RequestClass[] request;
        public WeatherClass[] weather;

        class RequestClass {
            public String type;
            public String query;
        }
        class WeatherClass {
            public String date;
            public AstronomyClass[] astronomy;
            public String maxtempC;
            public String maxtempF;
            public String mintempC;
            public String mintempF;
            public String totalSnow_cm;
            public String sunHour;
            public String uvIndex;
            public HourlyClass[] hourly;

            class AstronomyClass {
                public String sunrise;
                public String sunset;
                public String moonrise;
                public String moonset;
                public String moon_phase;
                public String moon_illumination;
            }
            class HourlyClass{
                public String time;
                public String tempC;
                public String tempF;
                public String windspeedMiles;
                public String windspeedKmph;
                public String winddirDegree;
                public String winddir16Point;
                public String weatherCode;
                public ArrayList<weatherIconUrlClass> weatherIconUrl;
                public ArrayList<weatherDescClass> weatherDesc;
                public String precipMM;
                public String humidity;
                public String visibility;
                public String pressure;
                public String cloudcover;
                public String HeatIndexC;
                public String HeatIndexF;
                public String DewPointC;
                public String DewPointF;
                public String WindChillC;
                public String WindChillF;
                public String WindGustMiles;
                public String WindGustKmph;
                public String FeelsLikeC;
                public String FeelsLikeF;

                class weatherIconUrlClass{
                    public String value;
                }

                class weatherDescClass{
                    public String value;
                }
            }

        }

    }


    }












