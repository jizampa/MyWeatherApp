//package com.example.joao.myweatherapp;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.CompoundButton;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.google.gson.JsonSyntaxException;
//import com.jjoe64.graphview.GraphView;
//import com.jjoe64.graphview.series.DataPoint;
//import com.jjoe64.graphview.series.LineGraphSeries;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//public class WeatherClass extends AppCompatActivity {
//
//
//    private String tag = "QueueTag";
//
//    TextView tvCurrentTemp;
//    TextView tvCurrenFL;
//    TextView tvDescriptionWeather;
//    TextView tv01Value;
//    TextView tv02Value;
//    TextView tv03Value;
//    TextView tv04Value;
//    TextView tv05Value;
//    TextView tv06Value;
//    TextView tv07Value;
//    TextView tvText01;
//    TextView tvText02;
//    TextView tvText03;
//    TextView tvText04;
//    TextView tvText05;
//    TextView tvText06;
//    TextView tvText07;
//
//    RadioButton rb7Years;
//    RadioButton rbNext7Days;
//    RadioButton rbPast7Months;
//    RadioButton rbPast7Days;
//
//    RadioGroup rbGroup;
//
//    GraphView graph;
//
//    String url;
//    String day ;
//    String monthNumber ;
//    String year;
//    String dayMonthYear;
//
//    double value00;
//    double value01;
//    double value02;
//    double value03;
//    double value04;
//    double value05;
//    double value06;
//    double value07;
//
//    boolean resp01;
//    boolean resp02;
//    boolean resp03;
//    boolean resp04;
//    boolean resp05;
//    boolean resp06;
//    boolean resp07;
//
//    Calendar caledar;
//
//    String urlBase;
//    String urlCity02 = "winnipeg";
//    String urlAfterCity03 = "&format=json&date=";
//    String date04 = "";
//    String formattedDateMenos1="";
//    //    double [] valueXGraph;
//    int[] valueXGraph;
//    int[] valueXGraphDay;
//    int[] valueXGraphMonth;
//    int[] valueXGraphYear;
//    int[][] arrayDate;
////    String graphOrientation;
//
//
//    private RequestQueue mReqQueue;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_weather);
//
//        tvCurrentTemp = findViewById(R.id.tvCurrentTemp);
//        tvCurrenFL = findViewById(R.id.tvCurrenFL);
//        tvDescriptionWeather = findViewById(R.id.tvDescriptionWeather);
//        tvText01 = findViewById(R.id.tv01);
//        tvText02 = findViewById(R.id.tv02);
//        tvText03 = findViewById(R.id.tv03);
//        tvText04 = findViewById(R.id.tv04);
//        tvText05 = findViewById(R.id.tv05);
//        tvText06 = findViewById(R.id.tv06);
//        tvText07 = findViewById(R.id.tv07);
//
//        tv01Value =findViewById(R.id.tv01Value);
//        tv02Value =findViewById(R.id.tv02Value);
//        tv03Value =findViewById(R.id.tv03Value);
//        tv04Value =findViewById(R.id.tv04Value);
//        tv05Value =findViewById(R.id.tv05Value);
//        tv06Value =findViewById(R.id.tv06Value);
//        tv07Value =findViewById(R.id.tv07Value);
//
//        rb7Years = findViewById(R.id.rb7Years);
//        rbPast7Days = findViewById(R.id.rbpast7Days);
//        rbPast7Months = findViewById(R.id.rbpast7Months);
//        rbNext7Days = findViewById(R.id.rb7NextDays);
//
//        rbGroup = findViewById(R.id.rbGroup);
//
//        Date date = null;
//
//        boolean resp01=false;
//        boolean resp02=false;
//        boolean resp03=false;
//        boolean resp04=false;
//        boolean resp05=false;
//        boolean resp06=false;
//        boolean resp07=false;
//        value01=0.0;
//        value02=0.0;
//        value03=0.0;
//        value04=0;
//        value05=0;
//        value06=0;
//        value07=0;
//        valueXGraphDay = new int[8];
//        valueXGraphMonth = new int[8];
//        valueXGraphYear = new int[8];
//        arrayDate = new int [7][3];
////        CallApi callApi = new CallApi();
////
////
////        // Getting the current values
////        try {
////            callApi.setCurrentValues();
////        } catch (JSONException e) {
////            e.printStackTrace();
////        }
//
//
//
////        // initiate with values so I don't have to call the api
////        valueXGraphDay = new int[] {11, 10 , 9, 8, 7, 6, 5};
////        valueXGraphMonth = new int[] {10, 9, 8, 7, 6, 5, 4};
////        valueXGraphYear = new int[] {2017, 2016, 2015, 2014, 2013, 2012, 2011};
////        value00 =-15;
////        tvCurrentTemp.setText("-15");
////        tvCurrenFL.setText("-25");
////        tvDescriptionWeather.setText("cold");
////        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//        url = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json";
//
//        mReqQueue = Volley.newRequestQueue(this);
//
//        ///////////////////////////
//        //// Populating the current
//        ///////////////////////////
//        callAPI(url,0);
//
//
//
//
//        rb7Years.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (rb7Years.isChecked()) {
//
//
//                    ApiCall apiCall = new ApiCall("Winnipeg");
//                    apiCall.historicalDataLoad(ApiCall.PAST_SEVEN_YEARS);
//
//
////                    urlBase ="https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
////                    value01=0.0;
////                    value02=0.0;
////                    value03=0.0;
////                    value04=0;
////                    value05=0;
////                    value06=0;
////                    value07=0;
////                    int i = 1;
////                    graphOrientation = "past";
////                    dayMonthYear= "year";
////
////                    String testUrl;
//
//////                    // tobe deleter////////////////////////////////////////////////
//////
//////
//////                    value01 = Double.valueOf("-9");
//////                    tv01Value.setText(String.valueOf(valueXGraphYear[0]));
//////                    value02 = Double.valueOf("-5");
//////                    tv02Value.setText(String.valueOf(valueXGraphYear[1]));
//////                    value03 = Double.valueOf("-3");
//////                    tv03Value.setText(String.valueOf(valueXGraphYear[2]));
//////                    value04 = Integer.valueOf("-9");
//////                    tv04Value.setText(String.valueOf(valueXGraphYear[3]));
//////                    value05 = Integer.valueOf("-15");
//////                    tv05Value.setText(String.valueOf(valueXGraphYear[4]));
//////                    value06 = Integer.valueOf("-25");
//////                    tv06Value.setText(String.valueOf(valueXGraphYear[5]));
//////                    value07 = Integer.valueOf("-2");
//////                    tv07Value.setText(String.valueOf(valueXGraphYear[6]));
//////                    graphCriator();
//////////////////////////////////////////////////////////////////////////////////////////
////
////                    do {
////                        testUrl = formatedUrl(i, "sub", "year");
////
////                        callAPI(testUrl, i);
////                        i++;
////
////                    } while (i < 8);
//                }
//            }
//
//
//        });
//
//        rbPast7Months.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (rbPast7Months.isChecked()) {
//                    urlBase ="https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
//                    value01=0.0;
//                    value02=0.0;
//                    value03=0.0;
//                    value04=0;
//                    value05=0;
//                    value06=0;
//                    value07=0;
//                    dayMonthYear= "month";
//                    graphOrientation = "past";
//                    int i = 1;
//                    String testUrl;
//
//
//                    do {
//                        testUrl = formatedUrl(i, "sub", "month");
//
//                        callAPI(testUrl, i);
//                        i++;
//
//                    } while (i < 8);
//                }
//            }
//        });
//
//        rbPast7Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (rbPast7Days.isChecked()) {
//
//
////                    CallApi callApi = new CallApi();
////                    try {
////                        valueXGraph=callApi.gettLast7dayYValues();
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
//
//
//                    value01=0.0;
//                    value02=0.0;
//                    value03=0.0;
//                    value04=0;
//                    value05=0;
//                    value06=0;
//                    value07=0;
//                    dayMonthYear="day";
//                    graphOrientation = "past";
//                    int i = 1;
//                    String testUrl;
//
//                    // tobe deleter
//                    graphCriator();
/////////////////////////////////////////////////
//
//                    do {
//                        testUrl = formatedUrl(i, "sub", "day");
//
//                        callAPI(testUrl, i);
//                        i++;
//
//                    } while (i < 8);
//                }
//
//            }
//        });
//        rbNext7Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (rbNext7Days.isChecked()) {
//
//
//
//
//
//
//
//
//                    urlBase = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
//                    value01=0.0;
//                    value02=0.0;
//                    value03=0.0;
//                    value04=0;
//                    value05=0;
//                    value06=0;
//                    value07=0;
//                    int i = 1;
//                    dayMonthYear="day";
//                    graphOrientation = "future";
//                    String testUrl;
//
//                    // tobe deleter
//                    graphCriator();
///////////////////////////////////////////////
//
//                    do {
//                        testUrl = formatedUrl(i, "add", "day");
//
//                        callAPI(testUrl, i);
//                        i++;
//
//                    } while (i < 8);
//                }
//
//            }
//        });
//
//    }
//    ////////////////////////////////
//    ///My functions
//    ////////////////////////////////////////
//
//    private String formatedUrl(int num, String operation, String field){
//
//        Date todayMenus01;
//        SimpleDateFormat dfmenos01,monthDayDF;
//
//        caledar = Calendar.getInstance();
//        valueXGraphYear[0] = caledar.get(Calendar.YEAR);
//        valueXGraphMonth[0] = (caledar.get(Calendar.MONTH))+1;
//        valueXGraphDay[0] = caledar.get(Calendar.DAY_OF_MONTH);
//
//
//        switch (field){
//            case "day":
//                num = (operation == "sub"? num * -1 : num);
//                caledar = Calendar.getInstance();
//                caledar.add(Calendar.DAY_OF_YEAR, num);
//                todayMenus01 = caledar.getTime();
//                dfmenos01 = new SimpleDateFormat("yyyy-MM-dd");
//                formattedDateMenos1 = dfmenos01.format(todayMenus01);
//
//                break;
//            case "month":
//                caledar = Calendar.getInstance();
//                caledar.add(Calendar.MONTH, (-1 *num));
//                todayMenus01 = caledar.getTime();
//                dfmenos01 = new SimpleDateFormat("yyyy-MM-dd");
//                formattedDateMenos1 = dfmenos01.format(todayMenus01);
//                break;
//            case "year":
//                caledar = Calendar.getInstance();
//                caledar.add(Calendar.MONTH, (-12 * num));
//                todayMenus01 = caledar.getTime();
//                dfmenos01 = new SimpleDateFormat("yyyy-MM-dd");
//                formattedDateMenos1 = dfmenos01.format(todayMenus01);
//                break;
//
//        }
//        num =  Math.abs(num);
//        valueXGraphYear[num] = caledar.get(Calendar.YEAR);
//        valueXGraphMonth[num] = (caledar.get(Calendar.MONTH))+1;
//        valueXGraphDay[num] = caledar.get(Calendar.DAY_OF_MONTH);
//        String result = String.format("%s%s%s%s",urlBase,urlCity02,urlAfterCity03,formattedDateMenos1);
//
//        return result;
//    }
//
//    private void callAPI(String url, final int flag) {
//
//
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
////                            Gson gson = new Gson();
////                            dataResponse = gson.fromJson(response, Data.class);
//                            JSON json = new JSON(new JSONObject(response));
//
//                            switch (flag){
//                                case 0:
//                                    value00 =Double.valueOf(json.get("data/current_condition[0]/temp_C"));
//                                    tvCurrentTemp.setText(json.get("data/current_condition[0]/temp_C"));
//                                    tvCurrenFL.setText(json.get("data/current_condition[0]/FeelsLikeC"));
//                                    tvDescriptionWeather.setText(json.get("data/current_condition[0]/weatherDesc[0]/value"));
//                                    break;
//                                case 1:
//                                    value01 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
//                                    tv01Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
//                                    resp01=true;
//                                    break;
//                                case 2:
//                                    value02 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
//                                    tv02Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
//                                    resp02=true;
//                                    break;
//                                case 3:
//                                    value03 = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
//                                    tv03Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
//                                    resp03=true;
//                                    break;
//                                case 4:
//                                    value04 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
//                                    tv04Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
//                                    resp04=true;
//                                    break;
//                                case 5:
//                                    value05 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
//                                    tv05Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
//                                    resp05=true;
//                                    break;
//                                case 6:
//                                    value06 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
//                                    tv06Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
//                                    resp06=true;
//                                    break;
//                                case 7:
//                                    value07 = Integer.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
//                                    tv07Value.setText(json.get("data/weather[0]/hourly[3]/tempC"));
//                                    resp07=true;
//                                    break;
//
//                            }
//                            if (flag!= 0){
//                                graphCriator();
//                            }
//
//
//                        } catch (JsonSyntaxException e) {
//                            e.printStackTrace();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
////
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        queue.add(stringRequest);
//
//    }
//
//
//    private void graphCriator(String graphOrientation, double[] valuesOfX, double[] valuesOfY){
//
////        switch (dayMonthYear){
////            case "day":
////                valueXGraph = valueXGraphDay;
////                break;
////            case "month":
////                valueXGraph = valueXGraphMonth;
////                break;
////            case "year":
////                valueXGraph = valueXGraphYear;
////                break;
////
////        }
//
//
//        graph = findViewById(R.id.graph);
//        graph.removeAllSeries();
//
//        LineGraphSeries<DataPoint> series;
//
//
//        if (graphOrientation == "past"){
//            tvText01.setText(String.valueOf(valuesOfX[0]));
//            tvText01.setText(String.valueOf(valuesOfX[1]));
//            tvText02.setText(String.valueOf(valuesOfX[2]));
//            tvText03.setText(String.valueOf(valuesOfX[3]));
//            tvText04.setText(String.valueOf(valuesOfX[4]));
//            tvText05.setText(String.valueOf(valuesOfX[5]));
//            tvText06.setText(String.valueOf(valuesOfX[6]));
//
//            try {
//                series = new LineGraphSeries<>(new DataPoint[] {
//
//                        new DataPoint(valuesOfX[6], valuesOfY[6]),
//                        new DataPoint(valuesOfX[5], valuesOfY[5]),
//                        new DataPoint(valuesOfX[4], valuesOfY[4]),
//                        new DataPoint(valuesOfX[3], valuesOfY[3]),
//                        new DataPoint(valuesOfX[2], valuesOfY[2]),
//                        new DataPoint(valuesOfX[1], valuesOfY[1]),
//                        new DataPoint(valuesOfX[0], valuesOfY[0])
//                });
//                graph.addSeries(series);
//            }
//            catch (Exception e){
//                Log.d("error",e.toString());
//            }
//
//        }else{
//            tvText01.setText(String.valueOf(valueXGraph[7]));
//            tvText02.setText(String.valueOf(valueXGraph[6]));
//            tvText03.setText(String.valueOf(valueXGraph[5]));
//            tvText04.setText(String.valueOf(valueXGraph[4]));
//            tvText05.setText(String.valueOf(valueXGraph[3]));
//            tvText06.setText(String.valueOf(valueXGraph[2]));
//            tvText07.setText(String.valueOf(valueXGraph[1]));
//            try {
//
//
//                series = new LineGraphSeries<>(new DataPoint[]{
////
//                        new DataPoint(valueXGraph[0], value00),
//                        new DataPoint(valueXGraph[1], value01),
//                        new DataPoint(valueXGraph[2], value02),
//                        new DataPoint(valueXGraph[3], value03),
//                        new DataPoint(valueXGraph[4], value04),
//                        new DataPoint(valueXGraph[5], value05),
//                        new DataPoint(valueXGraph[6], value06),
//                        new DataPoint(valueXGraph[7], value07),
////                        new DataPoint(1, value01),
////                        new DataPoint(2, value02),
////                        new DataPoint(3, value03),
////                        new DataPoint(4, value04),
////                        new DataPoint(5, value05),
////                        new DataPoint(6, value06),
////                        new DataPoint(7, value07)
//
//                });
//                graph.addSeries(series);
//            }
//            catch (Exception e){
//                Log.d("error",e.toString());
//            }
//
//        }
//
//
//
//
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
