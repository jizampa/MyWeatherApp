package com.example.joao.myweatherapp;

import android.util.Log;

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

public class ApiCall {

    public static final int PAST_SEVEN_DAYS = 1;
    public static final int PAST_SEVEN_MONTHS = 2;
    public static final int PAST_SEVEN_YEARS = 3;
    public static final int NEXT_SEVEN_DAYS = 4;
    public static double currentTemperature = 0;

    private JSON json;
    private String currentDescription,currentFL,currentTemp, location,windspeedKmph,humidity,pressure,HeatIndexC,winddir16Point,WindChillC,chanceofrain,chanceoffrost,chanceofsunshine,chanceofhightemp,chanceofsnow,chanceoffog,sunHour;
    private double[] valuesOfX;
    private double[] valuesOfY;
    private double valuesOfXCurrent;


    public ApiCall(String location){
        this.location = location;
    }

    private JSON getJSON(String jsonUrl){

        JSON json = null;
        String response = null;
        try {
            URL url = new URL(jsonUrl);
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

            json= new JSON(new JSONObject(sb.toString())) ;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            Log.d("Async", e.toString());
        }
        return json;
    }

    public boolean loadCurrentData(){

        json = getJSON("https://api.worldweatheronline.com/premium/v1/weather.ashx?key=38e388fea8fa4ff3a1f155126180411&q="+location+"&format=json");
        boolean ret = (json!=null);

        if(ret){
            currentTemp= json.get("data/current_condition[0]/temp_C");
            currentTemperature = Double.valueOf(currentTemp);
            currentFL = json.get("data/current_condition[0]/FeelsLikeC");
            currentDescription = json.get("data/current_condition[0]/weatherDesc[0]/value");
            windspeedKmph = json.get("data/weather[0]/hourly[4]/windspeedKmph");
            humidity = json.get("data/weather[0]/hourly[4]/humidity");
            pressure = json.get("data/weather[0]/hourly[4]/pressure");
            HeatIndexC=json.get("data/weather[0]/hourly[4]/HeatIndexC");
            winddir16Point = json.get("data/weather[0]/hourly[4]/windspeedKmph");
            WindChillC = json.get("data/weather[0]/hourly[4]/winddir16Point");
            chanceofrain = json.get("data/weather[0]/hourly[4]/chanceofrain");
            chanceoffrost = json.get("data/weather[0]/hourly[4]/chanceoffrost");
            chanceofsunshine = json.get("data/weather[0]/hourly[4]/chanceofsunshine");
            chanceofhightemp = json.get("data/weather[0]/hourly[4]/chanceofhightemp");
            chanceofsnow = json.get("data/weather[0]/hourly[4]/chanceofsnow");
            chanceoffog = json.get("data/weather[0]/hourly[4]/chanceoffog");
            sunHour = json.get("data/weather[0]/sunHour");

        }
        return ret;
    }

    public String getCurrentTemp(){
        return currentTemp;
    }

    public String getCurrentFL(){
        return currentFL;
    }

    public String getCurrentDescription(){
        return currentDescription;
    }

    public String getwindspeedKmph(){
        return windspeedKmph;
    }

    public String gethumidity(){
        return humidity;
    }

    public String getpressure(){
        return pressure;
    }

    public String getHeatIndexC(){
        return HeatIndexC;
    }

    public String getwinddir16Point(){
        return winddir16Point;
    }

    public String getWindChillC(){
        return WindChillC;
    }

    public String getChanceofrain(){
        return chanceofrain;
    }

    public String getChanceoffrost(){
        return chanceoffrost;
    }

    public String getChanceofsunshine(){
        return chanceofsunshine;
    }
    public String getChanceofhightemp(){
        return chanceofhightemp;
    }

    public String getChanceofsnow(){
        return chanceofsnow;
    }

    public String getChanceoffog(){
        return chanceoffog;
    }

    public String getSunHour(){
        return sunHour;
    }

    ///////Loading Historical data
    public boolean historicalDataLoad(int type){
        String date="";
        boolean ret = (json!=null);
        int i = 1;
        Calendar calendar;
        Date dateDate;
        SimpleDateFormat sdfDate;
        String pastOrfuture="";
        valuesOfX = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};
        valuesOfY = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

            do {
                calendar = Calendar.getInstance();
                switch (type) {
                    case 1:
                        valuesOfX[0]= Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                        calendar.add(Calendar.DAY_OF_YEAR, (-1 * i));
                        valuesOfX[i]= Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                        pastOrfuture="past-weather";
                    break;
                    case 2:
                        valuesOfX[0] = Double.valueOf(calendar.get(Calendar.MONTH)) + 1;
                        pastOrfuture="past-weather";
                        calendar.add(Calendar.MONTH, (-1 * i));
                        valuesOfX[i] = Double.valueOf(calendar.get(Calendar.MONTH)) + 1;
                    break;
                    case 3:
                        valuesOfX[0] = Double.valueOf(calendar.get(Calendar.YEAR));
                        pastOrfuture="past-weather";
                        calendar.add(Calendar.MONTH, (-12 * i));
                        valuesOfX[i] = Double.valueOf(calendar.get(Calendar.YEAR));
                    break;
                    case 4:
                        valuesOfX[0] = Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                        pastOrfuture="weather";
                        calendar.add(Calendar.DAY_OF_YEAR, (i));
                        valuesOfX[i] = Double.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
                        break;
                }

                dateDate = calendar.getTime();
                sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                date = sdfDate.format(dateDate);
                json = getJSON("https://api.worldweatheronline.com/premium/v1/"+pastOrfuture+".ashx?key=38e388fea8fa4ff3a1f155126180411&q="+location+"&format=json&date="+date);
                valuesOfY[i] = Double.valueOf(json.get("data/weather[0]/hourly[4]/tempC"));
                i++;
            }while (i<8);

        return ret;
    }

    public double[] getXvalues(){
        return valuesOfX;
    }
    public double[] getYvalues(){
        return valuesOfY;
    }

}
