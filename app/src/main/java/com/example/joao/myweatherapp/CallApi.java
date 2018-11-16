package com.example.joao.myweatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CallApi {

    private String tvCurrentTemp, tvCurrenFL, tvDescriptionWeather;
    private double[] valuesForY = new double[8];
    private String currentFL, currentTemp, currentDesc;
    private String resposne;
    private boolean isReady;

    private Context thisContext;

    public void CallApi(Context passedContext) throws JSONException {
        thisContext = passedContext;
    }

    public double[] getValuesYPast7Years() throws JSONException {
        this.setLast7YearsYValues();
        return valuesForY;
    }

    private void setLast7YearsYValues() throws JSONException {
        setCurrentValues(); // to add the current temperarture to the array
        String[] datesArray = new String[7];
        GetDates getdates = new GetDates();
        datesArray = getdates.getArrayOfPastDates("year");
        valuesForY = setValuesForYLastDates(datesArray);
    }

    public double[] getLast7monthYValues() throws JSONException {
        this.setLast7monthYValues();
        return valuesForY;
    }

    private void setLast7monthYValues() throws JSONException {
        setCurrentValues(); // to add the current temperarture to the array
        String[] datesArray = new String[7];
        GetDates getdates = new GetDates();
        datesArray = getdates.getArrayOfPastDates("month");
        valuesForY = setValuesForYLastDates(datesArray);

    }

    public double[] gettLast7dayYValues() throws JSONException {
        this.setLast7dayYValues();
        return valuesForY;
    }

    private void setLast7dayYValues() throws JSONException {
        setCurrentValues(); // to add the current temperarture to the array
        String[] datesArray = new String[7];
        GetDates getdates = new GetDates();
        datesArray = getdates.getArrayOfPastDates("day");
        valuesForY = setValuesForYLastDates(datesArray);
    }

    public String getCurrentTemp() {
        return currentTemp;
    }

    public String getcurrentFL() {
        return currentFL;
    }

    public String getcurrentDesc() {
        return currentDesc;
    }

    public void setCurrentValues() throws JSONException {
//        HttpHandler httphadler = new HttpHandler();
//        JSON json = new JSON(new JSONObject(httphadler.makeServiceCall(this.getCurrentDateUrl("Winnipeg"))));
        isReady = false;
        this.getStringResponse(this.getCurrentDateUrl("Winnipeg"));
        JSON json = new JSON(new JSONObject(resposne));
        valuesForY[0] = Double.valueOf(json.get("data/current_condition[0]/temp_C"));
//        currentTemp=json.get("data/current_condition[0]/temp_C");
//        currentFL=json.get("data/current_condition[0]/FeelsLikeC");
//        currentDesc = json.get("data/current_condition[0]/weatherDesc[0]/value");
    }

    public void testingResponse(){
        JSON json = null;
        try {
            json = new JSON(new JSONObject(resposne));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        valuesForY[0] = Double.valueOf(json.get("data/current_condition[0]/temp_C"));
    }
    public double[] setValuesForYLastDates(String[] datesArray) throws JSONException {
//        HttpHandler httphadler = new HttpHandler();
        double[] valuesForY = new double[7];
        int i = 1;
        for (String date : datesArray) {
            isReady = false;
            this.getStringResponse(date);
            JSON json = new JSON(new JSONObject(resposne));
            valuesForY[i] = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
            i++;
        }
        return valuesForY;
    }

    public void getStringResponse(String url) {
        ThreadForGetStringResponse threadToGetStringResponse = new ThreadForGetStringResponse();
        threadToGetStringResponse.execute(url);
        do {

        } while (isReady == false);
    }

    public String getPastWeatherUrl(String city, String date) {

        String urlCity02 = "winnipeg";
        //String urlCity02 = city;
        //String urlCurrentBase = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json";
        String urlPastBase = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
        String urlAfterCity03 = "&format=json&date=";
        return String.format("%s%s%s%s", urlPastBase, urlCity02, urlAfterCity03, date);
    }

    public String getCurrentDateUrl(String city) {

        String urlCity02 = city;
        String urlCurrentBase = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
        String urlAfterCity03 = "&format=json";
        return String.format("%s%s%s", urlCurrentBase, urlCity02, urlAfterCity03);
    }


    class ThreadForGetStringResponse extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String response = null;
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", System.getProperty("http.agent"));
                InputStream in = new BufferedInputStream(conn.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
                in.close();


                return sb.toString();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e){
                Log.d("Async", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            resposne = s;
            isReady = true;
            testingResponse();
        }

//        class HttpHandler {
//
//            public String makeServiceCall(String reqUrl) {
//                String response = null;
//
//                try {
//                    URL url = new URL(reqUrl);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");
//                    conn.setRequestProperty("User-Agent", System.getProperty("http.agent"));
//                    InputStream in = new BufferedInputStream(conn.getInputStream());
//                    response = convertStreamToString(in);
//
//                } catch (MalformedURLException e) {
//                    Log.d("HttpHandler", e.toString());
//                } catch (ProtocolException e) {
//                    Log.d("HttpHandler", e.toString());
//                } catch (IOException e) {
//                    Log.d("HttpHandler", e.toString());
//                } catch (Exception e) {
//                    Log.d("HttpHandler", e.toString());
//
//                }
//                return response;
//            }
//
//            private String convertStreamToString(InputStream is) {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//                StringBuilder sb = new StringBuilder();
//                String line;
//                try {
//                    while ((line = reader.readLine()) != null) {
//                        sb.append(line).append('\n');
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//
//                } finally {
//                    try {
//                        is.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                return sb.toString();
//            }
//        }


//        public String getPastWeatherUrl(String city, String date) {
//
//            String urlCity02 = "winnipeg";
//            //String urlCity02 = city;
//            //String urlCurrentBase = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=winnipeg&format=json";
//            String urlPastBase = "https://api.worldweatheronline.com/premium/v1/past-weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
//            String urlAfterCity03 = "&format=json&date=";
//            return String.format("%s%s%s%s", urlPastBase, urlCity02, urlAfterCity03, date);
//        }
//
//        public String getCurrentDateUrl(String city) {
//
//            String urlCity02 = city;
//            String urlCurrentBase = "https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q=";
//            String urlAfterCity03 = "&format=json";
//            return String.format("%s%s%s", urlCurrentBase, urlCity02, urlAfterCity03);
//        }
    }

    class GetDates {

        Calendar calendar = Calendar.getInstance();
        ;
        Date date;
        SimpleDateFormat sdfDate;
        String[] arrayOfXValues = new String[8];


        public String[] getValueOfX() {
            return arrayOfXValues;
        }

        public String[] getArrayOfPastDates(String DayMonthYear) {

            String[] reponseArray = new String[7];
            int i = 1;

            do {
                switch (DayMonthYear) {
                    case "year":
                        calendar.add(Calendar.MONTH, (-12 * i));
                        arrayOfXValues[i] = String.valueOf(calendar.get(Calendar.YEAR));
                        break;
                    case "month":
                        calendar.add(Calendar.MONTH, (-1 * i));
                        arrayOfXValues[i] = String.valueOf(calendar.get(Calendar.MONTH)) + 1;
                        break;
                    case "day":
                        calendar.add(Calendar.DAY_OF_YEAR, (-1 * i));
                        arrayOfXValues[i] = String.valueOf(Calendar.DAY_OF_MONTH);
                        break;
                }

                date = calendar.getTime();
                sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                reponseArray[1] = sdfDate.format(date);

                i++;

            } while (i < 8);

            return reponseArray;
        }


        public String[] getArrayOfNext7Days() {

            String[] reponseArray = new String[7];
            int i = 1;

            do {
                calendar.add(Calendar.DAY_OF_YEAR, (i));
                date = calendar.getTime();
                sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                reponseArray[1] = sdfDate.format(date);
                i++;
            } while (i < 8);
            return reponseArray;
        }

//     public static String getCurrentDate(){
////        String result;
////        Calendar calendar;
////        Date date;
////        SimpleDateFormat sdfDate;
////        calendar = Calendar.getInstance();;
////        date = calendar.getTime();
////        sdfDate = new SimpleDateFormat("yyyy-MM-dd");
////
////        return sdfDate.format(date);
////     }
    }
}