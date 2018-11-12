package com.example.joao.myweatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
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
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
    RadioButton rbPast7Months;
    RadioButton rbPast7Days;

    RadioGroup rbGroup;

    GraphView graph;

    String url;
    String day ;
    String monthNumber ;
    String year;
    String dayMonthYear;

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

    Calendar caledar;

    //String urlBasePastYears ="https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=";
    String urlLast01 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2017-11-07";
    String urlLast02 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2016-11-07";
    String urlLast03 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2015-11-07";
    String urlLast04 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2014-11-07";
    String urlLast05 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2013-11-07";
    String urlLast06 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2012-11-07";
    String urlLast07 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2011-11-07";
    String urlNext7Days ="https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2018-11-09";
    String urlBase;
    String urlCity02 = "winnipeg";
    String urlAfterCity03 = "&format=json&date=";
    String date04 = "";
    String formattedDateMenos1="";
    int[] valueXGraph;
    int[] valueXGraphDay;
    int[] valueXGraphMonth;
    int[] valueXGraphYear;
    int[][] arrayDate;
    String graphOrientation;


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
        rbPast7Months = findViewById(R.id.rbpast7Months);
        rbNext7Days = findViewById(R.id.rb7NextDays);

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
        valueXGraphDay = new int[7];
        valueXGraphMonth = new int[7];
        valueXGraphYear = new int[7];
        arrayDate = new int [7][3];


       // url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=Winnipeg&format=json&num_of_days=5";
        url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json";
//        urlLast01 = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=";

        https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json&date=2018-11-09


        mReqQueue = Volley.newRequestQueue(this);

        ///////////////////
        // Getting the current date
        /////////////////////
       caledar =  Calendar.getInstance();
        Date today = caledar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateToday = df.format(today);

        caledar.add(Calendar.DAY_OF_YEAR, 1);
        Date today01 = caledar.getTime();
        SimpleDateFormat df01 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateToday01 = df01.format(today01);

        caledar = Calendar.getInstance();
        caledar.add(Calendar.MONTH, -12);
        Date todayMenus01 = caledar.getTime();
        SimpleDateFormat dfmenos01 = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateMenos1 = df01.format(todayMenus01);

        caledar.add(Calendar.DAY_OF_YEAR, -1);
        Date todayMenus01Ano = caledar.getTime();
        SimpleDateFormat dfmenos01Ano = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateToday01Ano = df01.format(todayMenus01);




        Date date = null;
        try {
            date = df.parse(formattedDateToday);

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
                    urlBase ="https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
                    value01=0.0;
                    value02=0.0;
                    value03=0.0;
                    value04=0;
                    value05=0;
                    value06=0;
                    value07=0;
                    int i = 1;
                    graphOrientation = "past";
                    dayMonthYear= "year";

                    String testUrl;

                    do {
                        testUrl = formatedUrl(i, "sub", "year");

                        callAPI(testUrl, i);
                        i++;

                    } while (i < 8);
                }
            }


        });

        rbPast7Months.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbPast7Months.isChecked()) {
                    urlBase ="https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
                    value01=0.0;
                    value02=0.0;
                    value03=0.0;
                    value04=0;
                    value05=0;
                    value06=0;
                    value07=0;
                    dayMonthYear= "month";
                    graphOrientation = "past";
                    int i = 1;
                    String testUrl;
                    do {
                        testUrl = formatedUrl(i, "sub", "month");

                        callAPI(testUrl, i);
                        i++;

                    } while (i < 8);
                }
            }
        });

        rbPast7Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbPast7Days.isChecked()) {
                    value01=0.0;
                    value02=0.0;
                    value03=0.0;
                    value04=0;
                    value05=0;
                    value06=0;
                    value07=0;
                    dayMonthYear="day";
                    graphOrientation = "past";
                    int i = 1;
                    String testUrl;
                    do {
                        testUrl = formatedUrl(i, "sub", "day");

                        callAPI(testUrl, i);
                        i++;

                    } while (i < 8);
                }

            }
        });
        rbNext7Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbNext7Days.isChecked()) {
                    urlBase = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
                    value01=0.0;
                    value02=0.0;
                    value03=0.0;
                    value04=0;
                    value05=0;
                    value06=0;
                    value07=0;
                    int i = 1;
                    graphOrientation = "future";
                    String testUrl;
                    do {
                        testUrl = formatedUrl(i, "add", "day");

                        callAPI(testUrl, i);
                        i++;

                    } while (i < 8);
                }

            }
        });


        //////////////////////////////////
        ////////TESTING AREA
        //////////////////////////////////////
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 1);



    }
////////////////////////////////////
    ///My functions
    ////////////////////////////////////////

    private String formatedUrl(int num, String operation, String field){

        int dayToModify=0;
        int yearToModify=0;
        int monthToModify=0;
        String monthDay;


        Date todayMenus01;
        SimpleDateFormat dfmenos01,monthDayDF;



        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        dayToModify = Integer.valueOf(day);
        monthToModify = Integer.valueOf(monthNumber);
        yearToModify = Integer.valueOf(year);

        switch (field){
            case "day":
                num = (operation == "sub"? num * -1 : num);
                caledar = Calendar.getInstance();
                caledar.add(Calendar.DAY_OF_YEAR, num);
                todayMenus01 = caledar.getTime();
                dfmenos01 = new SimpleDateFormat("yyyy-MM-dd");

//                monthDayDF = new SimpleDateFormat("MMM-dd");
//                monthDay = monthDayDF.format(todayMenus01);
                formattedDateMenos1 = dfmenos01.format(todayMenus01);
                //dayToModify = (operation == "sub"? dayToModify - num : dayToModify + num);
                break;
            case "month":
                caledar = Calendar.getInstance();
                caledar.add(Calendar.MONTH, (-1 *num));
                todayMenus01 = caledar.getTime();
                dfmenos01 = new SimpleDateFormat("yyyy-MM-dd");
                formattedDateMenos1 = dfmenos01.format(todayMenus01);

                //yearToModify = (operation == "sub"? yearToModify - num : yearToModify + num);

                //monthToModify = (operation == "sub"? monthToModify - num : monthToModify + num);
                break;
            case "year":
                caledar = Calendar.getInstance();
                caledar.add(Calendar.MONTH, (-12 * num));
                todayMenus01 = caledar.getTime();
                dfmenos01 = new SimpleDateFormat("yyyy-MM-dd");
                formattedDateMenos1 = dfmenos01.format(todayMenus01);
                yearToModify = (operation == "sub"? yearToModify - num : yearToModify + num);
                break;

        }
        num =  Math.abs(num);
        valueXGraphYear[num-1] = caledar.get(Calendar.YEAR);
        valueXGraphMonth[num-1] = (caledar.get(Calendar.MONTH))+1;
        valueXGraphDay[num-1] = caledar.get(Calendar.DAY_OF_MONTH);
        arrayDate[num-1][0]=caledar.get(Calendar.DAY_OF_MONTH);
        arrayDate[num-1][1]=(caledar.get(Calendar.MONTH))+1;
        arrayDate[num-1][2]=caledar.get(Calendar.YEAR);
        //String result = String.format("%s%s%s%d/%02d/%02d",urlBasePastYears01,urlCity02,urlAfterCity03,yearToModify,monthToModify,dayToModify );
        String result = String.format("%s%s%s%s",urlBase,urlCity02,urlAfterCity03,formattedDateMenos1);

        return result;
    }

    private void setText(int field, Calendar cal, String dayYearOrMonth){
       SimpleDateFormat monthDay, day, year;



        cal = Calendar.getInstance();
        cal.get(Calendar.DAY_OF_MONTH);


//        switch (field){
//            case 1:
//                tvText01.setText(String.valueOf(value));
//                break;
//            case 2:
//                tvText02.setText(String.valueOf(value));
//                break;
//            case 3:
//                tvText03.setText(String.valueOf(value));
//                break;
//            case 4:
//                tvText04.setText(String.valueOf(value));
//                break;
//            case 5:
//                tvText05.setText(String.valueOf(value));
//                break;
//            case 6:
//                tvText06.setText(String.valueOf(value));
//                break;
//            case 7:
//                tvText07.setText(String.valueOf(value));
//                break;
//        }

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
                                    tv01Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));

                                    resp01=true;
                                    break;
                                case 2:
                                    value02 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv02Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
                                    resp02=true;
                                    break;
                                case 3:
                                    value03 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv03Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
                                    resp03=true;
                                    break;
                                case 4:
                                    value04 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv04Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
                                    resp04=true;
                                    break;
                                case 5:
                                    value05 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv05Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
                                    resp05=true;
                                    break;
                                case 6:
                                    value06 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv06Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
                                    resp06=true;
                                    break;
                                case 7:
                                    value07 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                                    tv07Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
                                    resp07=true;
                                    break;

                            }
                            if (flag!= 0){
                                graphCriator();
                            }


                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }





    private void graphCriator(){

        switch (dayMonthYear){
            case "day":
                valueXGraph = valueXGraphDay;
                break;
            case "month":
                valueXGraph = valueXGraphMonth;
                break;
            case "year":
                valueXGraph = valueXGraphYear;
                break;

        }


        graph = findViewById(R.id.graph);
        graph.removeAllSeries();
        Log.d("vaulues -00", String.valueOf(value00));
        Log.d("vaulues -01", String.valueOf(value01));
        Log.d("vaulues -02", String.valueOf(value02));
        Log.d("vaulues -03", String.valueOf(value03));
        Log.d("vaulues -04", String.valueOf(value04));
        Log.d("vaulues -05", String.valueOf(value05));
        Log.d("vaulues -06", String.valueOf(value06));
        LineGraphSeries<DataPoint> series;


        if (graphOrientation == "past"){

            tvText01.setText(String.valueOf(valueXGraph[0]));
            tvText02.setText(String.valueOf(valueXGraph[1]));
            tvText03.setText(String.valueOf(valueXGraph[2]));
            tvText04.setText(String.valueOf(valueXGraph[3]));
            tvText05.setText(String.valueOf(valueXGraph[4]));
            tvText06.setText(String.valueOf(valueXGraph[5]));
            tvText07.setText(String.valueOf(valueXGraph[6]));

           series = new LineGraphSeries<>(new DataPoint[] {


//
//                new DataPoint(valueXGraph[0], value07),
//                new DataPoint(valueXGraph[1], value06),
//                new DataPoint(valueXGraph[2], value05),
//                new DataPoint(valueXGraph[3], value04),
//                new DataPoint(valueXGraph[4], value03),
//                new DataPoint(valueXGraph[5], value02),
//                new DataPoint(valueXGraph[6], value01)
                    new DataPoint(1, value07),
                    new DataPoint(2, value06),
                    new DataPoint(3, value05),
                    new DataPoint(4, value04),
                    new DataPoint(5, value03),
                    new DataPoint(6, value02),
                    new DataPoint(7, value01)


            });
            graph.addSeries(series);

        }else{
            tvText01.setText(String.valueOf(valueXGraph[6]));
            tvText02.setText(String.valueOf(valueXGraph[5]));
            tvText03.setText(String.valueOf(valueXGraph[4]));
            tvText04.setText(String.valueOf(valueXGraph[3]));
            tvText05.setText(String.valueOf(valueXGraph[2]));
            tvText06.setText(String.valueOf(valueXGraph[1]));
            tvText07.setText(String.valueOf(valueXGraph[0]));
            try {


                series = new LineGraphSeries<>(new DataPoint[]{
//
//                new DataPoint(valueXGraph[0], value07),
//                new DataPoint(valueXGraph[1], value06),
//                new DataPoint(valueXGraph[2], value05),
//                new DataPoint(valueXGraph[3], value04),
//                new DataPoint(valueXGraph[4], value03),
//                new DataPoint(valueXGraph[5], value02),
//                new DataPoint(valueXGraph[6], value01)
                        new DataPoint(valueXGraph[0], value01),
                        new DataPoint(2, value02),
                        new DataPoint(3, value03),
                        new DataPoint(4, value04),
                        new DataPoint(5, value05),
                        new DataPoint(6, value06),
                        new DataPoint(7, value07)

                });
                graph.addSeries(series);
            }
            catch (Exception e){
                Log.d("error",e.toString());
            }

        }




    }

    }












