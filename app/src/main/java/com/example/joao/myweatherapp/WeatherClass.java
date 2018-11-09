package com.example.joao.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class WeatherClass extends AppCompatActivity {


    private String tag = "QueueTag";

    TextView tvCurrentTemp;
    TextView tvCurrenFL;
    TextView tvDescriptionWeather;

    TextView tv01Value;
    TextView tv02Value;
    TextView tv03Value;
    TextView tv04Value;
    TextView tv05Value;
    TextView tv06Value;
    TextView tv07Value;
    TextView tvText01;
    TextView tvText02;
    TextView tvText03;
    TextView tvText04;
    TextView tvText05;
    TextView tvText06;
    TextView tvText07;

    RadioButton rb7Years;
    RadioButton rbNext7Days;
    RadioButton rbPast7Days;
    RadioGroup rbGroup;

    GraphView graph;

    String url;
    String day ;
    String monthNumber ;
    String year;

    double value00;
    double value01;
    double value02;
    double value03;
    double value04;
    double value05;
    double value06;
    double value07;

    boolean resp01;
    boolean resp02;
    boolean resp03;
    boolean resp04;
    boolean resp05;
    boolean resp06;
    boolean resp07;


    //String urlBasePastYears ="https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=";
    String urlLast01 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2017-11-07";
    String urlLast02 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2016-11-07";
    String urlLast03 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2015-11-07";
    String urlLast04 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2014-11-07";
    String urlLast05 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2013-11-07";
    String urlLast06 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2012-11-07";
    String urlLast07 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2011-11-07";
    String urlNext7Days ="https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2018-11-09";
    String urlBasePastYears01 ="https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
    String urlCity02 = "winnipeg";
    String urlAfterCity03 = "&format=json&date=";
    String date04 = "";


    private RequestQueue mReqQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
        tvCurrenFL = findViewById(R.id.tvCurrenFL);
        tvDescriptionWeather = findViewById(R.id.tvDescriptionWeather);
        tvText01 = findViewById(R.id.tv01);
        tvText02 = findViewById(R.id.tv02);
        tvText03 = findViewById(R.id.tv03);
        tvText04 = findViewById(R.id.tv04);
        tvText05 = findViewById(R.id.tv05);
        tvText06 = findViewById(R.id.tv06);
        tvText07 = findViewById(R.id.tv07);

        tv01Value =findViewById(R.id.tv01Value);
        tv02Value =findViewById(R.id.tv02Value);
        tv03Value =findViewById(R.id.tv03Value);
        tv04Value =findViewById(R.id.tv04Value);
        tv05Value =findViewById(R.id.tv05Value);
        tv06Value =findViewById(R.id.tv06Value);
        tv07Value =findViewById(R.id.tv07Value);

        rb7Years = findViewById(R.id.rb7Years);
        rbPast7Days = findViewById(R.id.rbpast7Days);

        rbGroup = findViewById(R.id.rbGroup);

        boolean resp01=false;
        boolean resp02=false;
        boolean resp03=false;
        boolean resp04=false;
        boolean resp05=false;
        boolean resp06=false;
        boolean resp07=false;
        value01=0.0;
        value02=0.0;
        value03=0.0;
        value04=0;
        value05=0;
        value06=0;
        value07=0;
       // url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=Winnipeg&format=json&num_of_days=5";
        url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json";
//        urlLast01 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=";

        https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2018-11-09


        mReqQueue = Volley.newRequestQueue(this);

        ///////////////////
        // Getting the current date
        /////////////////////
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = df.format(c);

        Date date = null;
        try {
            date = df.parse(formattedDate);

            day          = (String) DateFormat.format("dd",   date); // 20
            monthNumber  = (String) DateFormat.format("MM",   date); // 06
            year         = (String) DateFormat.format("yyyy", date); // 2013

        } catch (ParseException e) {
            e.printStackTrace();
        }

        ///////////////////////////
        //// Populating the current
        ///////////////////////////
        callAPI(url,0);

/////////////////////////////
        // Setting listeners
        ////////////////////
        rb7Years.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb7Years.isChecked()) {
                    int i = 1;

                    String testUrl;

                    do {
                        testUrl = formatedUrl(i, "sub", "year");

                        callAPI(testUrl, i);
                        i++;

                    } while (i < 8);
                }
            }


        });

        rbPast7Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String test= "tet";
            }
        });
    }
////////////////////////////////////
    ///My functions
    ////////////////////////////////////////

    private String formatedUrl(int num, String operation, String field){

        int dayToModify=0;
        int yearToModify=0;
        int monthToModify=0;


        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        dayToModify = Integer.valueOf(day);
        monthToModify = Integer.valueOf(monthNumber);
        yearToModify = Integer.valueOf(year);

        switch (field){
            case "day":
                dayToModify = (operation == "sub"? dayToModify - num : dayToModify + num);
                break;
            case "month":

                monthToModify = (operation == "sub"? monthToModify - num : monthToModify + num);
                break;
            case "year":

                yearToModify = (operation == "sub"? yearToModify - num : yearToModify + num);


                break;
        }
        String result = String.format("%s%s%s%d/%02d/%02d",urlBasePastYears01,urlCity02,urlAfterCity03,yearToModify,monthToModify,dayToModify );

        return result;
    }

    private void setText(int field, String value){
        switch (field){
            case 1:
                tvText01.setText(String.valueOf(value));
                break;
            case 2:
                tvText02.setText(String.valueOf(value));
                break;
            case 3:
                tvText03.setText(String.valueOf(value));
                break;
            case 4:
                tvText04.setText(String.valueOf(value));
                break;
            case 5:
                tvText05.setText(String.valueOf(value));
                break;
            case 6:
                tvText06.setText(String.valueOf(value));
                break;
            case 7:
                tvText07.setText(String.valueOf(value));
                break;
        }

    }


    private void callAPI(String url, final int flag) {


        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
//                            Gson gson = new Gson();
//                            dataResponse = gson.fromJson(response, Data.class);
                            JSON json = new JSON(new JSONObject(response));

                            switch (flag){
                                case 0:
                                    value00 =Double.valueOf(json.get("data/current_condition[0]/temp_C"));
                                    tvCurrentTemp.setText(json.get("data/current_condition[0]/temp_C"));
                                    tvCurrenFL.setText(json.get("data/current_condition[0]/FeelsLikeC"));
                                    tvDescriptionWeather.setText(json.get("data/current_condition[0]/weatherDesc[0]/value"));
                                break;
                                case 1:
                                    value01 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv01Value.setText(json.get("data/weather[0]/hourly[4]/tempC"));
                                    resp01=true;
                                    break;
                                case 2:
                                    value02 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv02Value.setText(json.get("data/weather[0]/hourly[4]/tempC"));
                                    resp02=true;
                                    break;
                                case 3:
                                    value03 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv03Value.setText(json.get("data/weather[0]/hourly[4]/tempC"));
                                    resp03=true;
                                    break;
                                case 4:
                                    value04 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv04Value.setText(json.get("data/weather[0]/hourly[4]/tempC"));
                                    resp04=true;
                                    break;
                                case 5:
                                    value05 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv05Value.setText(json.get("data/weather[0]/hourly[4]/tempC"));
                                    resp05=true;
                                    break;
                                case 6:
                                    value06 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv06Value.setText(json.get("data/weather[0]/hourly[4]/tempC"));
                                    resp06=true;
                                    break;
                                case 7:
                                    value07 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv07Value.setText(json.get("data/weather[0]/hourly[4]/tempC"));
                                    resp07=true;
                                    break;

                            }

                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (resp01==true && resp02==true && resp03==true && resp04==true && resp05==true && resp06==true && resp07==true){
                            graphCriator();
                            resp01=resp02=resp03=resp04=resp05=resp06=resp07= false;

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }
    private void graphCriator(){

        graph = findViewById(R.id.graph);
        graph.clearSecondScale();
        Log.d("vaulues -00", String.valueOf(value00));
        Log.d("vaulues -01", String.valueOf(value01));
        Log.d("vaulues -02", String.valueOf(value02));
        Log.d("vaulues -03", String.valueOf(value03));
        Log.d("vaulues -04", String.valueOf(value04));
        Log.d("vaulues -05", String.valueOf(value05));
        Log.d("vaulues -06", String.valueOf(value06));
        Log.d("vaulues -07", String.valueOf(value07));

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {

//


                new DataPoint(11, value07),
                new DataPoint(12, value06),
                new DataPoint(13, value05),
                new DataPoint(14, value04),
                new DataPoint(15, value03),
                new DataPoint(16, value02),
                new DataPoint(17, value01),
                new DataPoint(18, value00)
        });
        graph.addSeries(series);

    }

    }












