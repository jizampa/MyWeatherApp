package com.example.joao.myweatherapp;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class WeatherClass extends AppCompatActivity {

    private String tag = "QueueTag";

    TextView tvCurrentTemp,tvCurrenFL,tvDescriptionWeather,tv01Value,tv02Value,tv03Value,tv04Value,tv05Value,tv06Value,tv07Value,tvText01,tvText02,tvText03;
    TextView tvText04,tvText05,tvText06,tvText07;

    RadioButton rb7Years,rbNext7Days,rbPast7Months,rbPast7Days;

    RadioGroup rbGroup;

    GraphView graph;

    String city,graphOrientation;
    int type;

//    ApiCall apiCurrent;

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


//        apiCurrent = new ApiCall("Winnipeg");
//        apiCurrent.loadCurrentData();
//        tvCurrentTemp.setText(apiCurrent.getCurrentTemp());
//        tvCurrenFL.setText(apiCurrent.getCurrentFL());
//        tvDescriptionWeather.setText(apiCurrent.getCurrentDescription());
        new Apitask(true).execute();

        rb7Years.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rb7Years.isChecked()) {
                    graphOrientation="past";
                    city = "winnipeg";
                    type = ApiCall.PAST_SEVEN_YEARS;
                    new Apitask(false).execute();

                }
            }

        });

        rbPast7Months.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbPast7Months.isChecked()) {
                    graphOrientation="past";
                    city = "winnipeg";
                    type = ApiCall.PAST_SEVEN_MONTHS;
                    new Apitask(false).execute();
                }
            }
        });

        rbPast7Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbPast7Days.isChecked()) {
                    graphOrientation="past";
                    city = "winnipeg";
                    type = ApiCall.PAST_SEVEN_DAYS;
                    new Apitask(false).execute();
                }
            }
        });
        rbNext7Days.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (rbNext7Days.isChecked()) {
                    graphOrientation = "next";
                    city = "winnipeg";
                    type = ApiCall.NEXT_SEVEN_DAYS;
                    new Apitask(false).execute();
                }
            }
        });
    }

    class Apitask extends AsyncTask {
        private ApiCall api;
        private boolean currentOnly;

        public Apitask(boolean currentOnly){
            this.currentOnly = currentOnly;
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            api=new ApiCall(city);
            api.loadCurrentData();
            if (!currentOnly){
                api.historicalDataLoad(type);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if (!currentOnly) {
                graphCriator(graphOrientation, api.getXvalues(), api.getYvalues(), Double.valueOf(api.getCurrentTemp()));
            }
            tvDescriptionWeather.setText(api.getCurrentDescription());
            tvCurrenFL.setText(api.getCurrentFL());
            tvCurrentTemp.setText(api.getCurrentTemp());
        }
    }

    private void graphCriator(String graphOrientation, double[] valuesOfX, double[] valuesOfY, double currentTemp){

        graph = findViewById(R.id.graph);
        graph.removeAllSeries();

        LineGraphSeries<DataPoint> series;

        if (graphOrientation == "past"){

            tvText01.setText(String.valueOf(valuesOfX[1]));
            tvText02.setText(String.valueOf(valuesOfX[2]));
            tvText03.setText(String.valueOf(valuesOfX[3]));
            tvText04.setText(String.valueOf(valuesOfX[4]));
            tvText05.setText(String.valueOf(valuesOfX[5]));
            tvText06.setText(String.valueOf(valuesOfX[6]));
            tvText07.setText(String.valueOf(valuesOfX[7]));


            tv01Value.setText(String.valueOf(valuesOfY[1]));
            tv02Value.setText(String.valueOf(valuesOfY[2]));
            tv03Value.setText(String.valueOf(valuesOfY[3]));
            tv04Value.setText(String.valueOf(valuesOfY[4]));
            tv05Value.setText(String.valueOf(valuesOfY[5]));
            tv06Value.setText(String.valueOf(valuesOfY[6]));
            tv07Value.setText(String.valueOf(valuesOfY[7]));


            try {
                series = new LineGraphSeries<>(new DataPoint[] {

                        new DataPoint(valuesOfX[7], valuesOfY[7]),
                        new DataPoint(valuesOfX[6], valuesOfY[6]),
                        new DataPoint(valuesOfX[5], valuesOfY[5]),
                        new DataPoint(valuesOfX[4], valuesOfY[4]),
                        new DataPoint(valuesOfX[3], valuesOfY[3]),
                        new DataPoint(valuesOfX[2], valuesOfY[2]),
                        new DataPoint(valuesOfX[1], valuesOfY[1]),
                        new DataPoint(valuesOfX[0], currentTemp)

                       // new DataPoint(valuesOfX[0], Integer.valueOf(apiCurrent.getCurrentTemp()))
                });
                graph.addSeries(series);
            }
            catch (Exception e){
                Log.d("error",e.toString());
            }

        }else{

            tvText01.setText(String.valueOf(valuesOfX[7]));
            tvText02.setText(String.valueOf(valuesOfX[6]));
            tvText03.setText(String.valueOf(valuesOfX[5]));
            tvText04.setText(String.valueOf(valuesOfX[4]));
            tvText05.setText(String.valueOf(valuesOfX[3]));
            tvText06.setText(String.valueOf(valuesOfX[2]));
            tvText07.setText(String.valueOf(valuesOfX[1]));

            tv01Value.setText(String.valueOf(valuesOfY[7]));
            tv02Value.setText(String.valueOf(valuesOfY[6]));
            tv03Value.setText(String.valueOf(valuesOfY[5]));
            tv04Value.setText(String.valueOf(valuesOfY[4]));
            tv05Value.setText(String.valueOf(valuesOfY[3]));
            tv06Value.setText(String.valueOf(valuesOfY[2]));
            tv07Value.setText(String.valueOf(valuesOfY[1]));

            try {


                series = new LineGraphSeries<>(new DataPoint[]{

                new DataPoint(valuesOfX[0], currentTemp),
                new DataPoint(valuesOfX[1], valuesOfY[1]),
                new DataPoint(valuesOfX[2], valuesOfY[2]),
                new DataPoint(valuesOfX[3], valuesOfY[3]),
                new DataPoint(valuesOfX[4], valuesOfY[4]),
                new DataPoint(valuesOfX[5], valuesOfY[5]),
                new DataPoint(valuesOfX[6], valuesOfY[6]),
                new DataPoint(valuesOfX[7], valuesOfY[7])
                });
                graph.addSeries(series);
            }
            catch (Exception e){
                Log.d("error",e.toString());
            }

        }

    }
}












