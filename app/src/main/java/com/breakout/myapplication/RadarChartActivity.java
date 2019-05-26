package com.breakout.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class RadarChartActivity extends AppCompatActivity {

    HorizontalBarChart chart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        chart =  findViewById(R.id.chart1);
        float a = 20f;
        float b = 10f;

        generateChart(chart, a, b);


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
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(barData);
        chart.animateY(3000);
    }
}

