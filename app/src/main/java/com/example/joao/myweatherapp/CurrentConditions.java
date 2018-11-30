package com.example.joao.myweatherapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentConditions extends AppCompatActivity {

    private String currentDescription,currentFL,currentTemp, location,windspeedKmph,humidity,pressure,HeatIndexC,winddir16Point,WindChillC,chanceofrain,chanceoffrost,chanceofsunshine,chanceofhightemp,chanceofsnow,chanceoffog;
    TextView tvCurrentTemp,tvCurrentFL,tvWindspeedKmph,tvHumidity,tvPressure,tvHeatIndexC,tvSunHour,tvWinddir16Point,tvWindChillC,tvChanceofrain,tvChanceoffrost,tvChanceofsunshine,tvChanceofhightemp,tvChanceofsnow,tvChanceoffog;
    String city;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_conditions);

        city = "Winnipeg";

        tvCurrentTemp=findViewById(R.id.tvCurrentTempValue);
        tvCurrentFL=findViewById(R.id.tvFeelsLikeValue);
        tvWindspeedKmph=findViewById(R.id.tvWindSpeedValue);
        tvHumidity=findViewById(R.id.tvHumidityValue);
        tvPressure=findViewById(R.id.tvPresureValue);
        tvHeatIndexC=findViewById(R.id.tvHeatIdexValue);
        tvSunHour=findViewById(R.id.tvSunHourValue);
        tvWinddir16Point=findViewById(R.id.tvWindDirectionValue);
        tvChanceofrain=findViewById(R.id.tvChanceofRainValue);
        tvWindChillC=findViewById(R.id.tvWindChillValue);
        tvChanceoffrost=findViewById(R.id.tvChanceofFrostValue);
        tvChanceofsunshine=findViewById(R.id.tvChanceofSunshineValue);
        tvChanceofhightemp=findViewById(R.id.tvChanceofHightTempValue);
        tvChanceofsnow=findViewById(R.id.tvChanceofSnowValue);
        tvChanceoffog=findViewById(R.id.tvChanceofFogValue);

        new Apitask().execute();
    }


    class Apitask extends AsyncTask {
        ApiCall api;

        @Override
        protected Object doInBackground(Object[] objects) {
            api = new ApiCall(city);
            api.loadCurrentData();
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            tvCurrentTemp.setText(api.getCurrentTemp());
            tvCurrentFL.setText(api.getCurrentFL());
            tvWindspeedKmph.setText(api.getwindspeedKmph());
            tvHumidity.setText(api.gethumidity());
            tvPressure.setText(api.getpressure());
            tvHeatIndexC.setText(api.getHeatIndexC());
            tvSunHour.setText(api.getSunHour());
            tvWinddir16Point.setText(api.getwinddir16Point());
            tvWindChillC.setText(api.getWindChillC());
            tvChanceofrain.setText(api.getChanceofrain());
            tvChanceoffrost.setText(api.getChanceoffrost());
            tvChanceofsunshine.setText(api.getChanceofsunshine());
            tvChanceofhightemp.setText(api.getChanceofhightemp());
            tvChanceofsnow.setText(api.getChanceofsnow());
            tvChanceoffog.setText(api.getChanceoffog());
        }
    }
}
