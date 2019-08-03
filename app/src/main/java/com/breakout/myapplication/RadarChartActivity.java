package com.breakout.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.breakout.myapplication.model.CalcModel;
import com.breakout.myapplication.model.UKmodel;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class RadarChartActivity extends AppCompatActivity {

    HorizontalBarChart firstChart;
    HorizontalBarChart secondChart ;
    HorizontalBarChart thirdChart;
    HorizontalBarChart fourthChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        CalcModel calcModel = (CalcModel) intent.getSerializableExtra("calc");
        UKmodel uKmodel = (UKmodel) intent.getSerializableExtra("uk");
        setContentView(R.layout.graphics_layout);
        firstChart =  findViewById(R.id.first_chart);
        secondChart = findViewById(R.id.second_chart);
        thirdChart = findViewById(R.id.third_chart);
        fourthChart = findViewById(R.id.fourth_chart);
        float a = uKmodel.getCurrent_rem();
        float b = calcModel.getCurrent_rem();
        generateChart(firstChart, b, a);
        a = uKmodel.getManage_options();
        b = calcModel.getManage_options();
        generateChart(secondChart, b, a);
        a = uKmodel.getMaintenance();
        b = calcModel.getMaintenance();
        generateChart(thirdChart, b, a);
        a = uKmodel.getSumma();
        b = calcModel.getSumma();
        generateChart(fourthChart, b, a);
    }

    private void generateChart(HorizontalBarChart chart, float a, float b) {
        ArrayList<BarEntry> BARENTRY ;
        ArrayList<String> BarEntryLabels ;
        BarDataSet barDataSet;
        BarData barData;
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);
        chart.setDrawValueAboveBar(false);
        chart.getLegend().setEnabled(false);
        chart.setDescription("");
        chart.getAxisRight().setDrawLabels(false);
        chart.getAxisLeft().setDrawLabels(false);
        chart.setScaleXEnabled(false);
        chart.setScaleYEnabled(false);
        chart.getAxisRight().setAxisMinValue(0f);
        chart.getAxisLeft().setAxisMinValue(0f);
        BARENTRY = new ArrayList<>();
        BARENTRY.add(new BarEntry(a, 0));
        BARENTRY.add(new BarEntry(b, 1));
        BarEntryLabels = new ArrayList<>();
        BarEntryLabels.add("УК");
        BarEntryLabels.add("Расчет");
        barDataSet = new BarDataSet(BARENTRY, "");
        barData = new BarData(BarEntryLabels, barDataSet);
        barData.setValueTextSize(10f);
        int[] colors = new int[2];
        colors[0] = Color.parseColor("#F8D15B");
        colors[1] = Color.parseColor("#DB522A");
        barDataSet.setColors(colors);
        chart.setData(barData);
        chart.animateY(3000);
    }

    public void onComplainClick(View view) {
        startActivity(new Intent(this, ComplaintsActivity.class));
    }
}

