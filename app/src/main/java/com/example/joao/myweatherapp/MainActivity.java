package com.example.joao.myweatherapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    Button btnHistoricalForecast;
    Button btnMoreAboutToday;

    TextView tvCurrentTemp,tvCurrentFL,tvCurrentDesc;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHistoricalForecast= findViewById(R.id.btnStart);
        btnMoreAboutToday = findViewById(R.id.btnMoreAboutToday);
        tvCurrentDesc = findViewById(R.id.tvCurrenDesc);
        tvCurrentFL = findViewById(R.id.tvCurrentFL);
        tvCurrentTemp = findViewById(R.id.tvCurrenTemp);
//        CallApi callApi = new CallApi();
//
//        try {
//            callApi.setCurrentValues();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

//        tvCurrentTemp.setText(callApi.getCurrentTemp());
//        tvCurrentFL.setText(callApi.getcurrentFL());
//        tvCurrentDesc.setText(callApi.getcurrentDesc());


        btnHistoricalForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(MainActivity.this, WeatherClass.class);
                startActivity(intent);

            }
        });
        btnMoreAboutToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, CurrentConditions.class);
                startActivity(intent);
            }
        });

        new Apitask().execute();
    }

    class Apitask extends AsyncTask{
        ApiCall api;

        @Override
        protected Object doInBackground(Object[] objects) {
            api=new ApiCall("Winnipeg");
            api.loadCurrentData();

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            tvCurrentTemp.setText(api.getCurrentTemp());
            tvCurrentFL.setText(api.getCurrentFL());
            tvCurrentDesc.setText(api.getCurrentDescription());
        }
    }
}

