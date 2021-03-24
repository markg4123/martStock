package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GraphActivity extends AppCompatActivity {

    BarChart barChart;
    String price1, price2,price3,price4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> prices = new ArrayList<>();

        price1 = getIntent().getExtras().getString("price1");
        price2 = getIntent().getExtras().getString("price2");
        price3 = getIntent().getExtras().getString("price3");
        price4 = getIntent().getExtras().getString("price4");

        Float hefPrice = Float.parseFloat(price1);
        Float bulPrice = Float.parseFloat(price2);
        Float dryPrice = Float.parseFloat(price3);
        Float calfPrice = Float.parseFloat(price4);

        prices.add(new BarEntry(hefPrice,0));
        prices.add(new BarEntry(bulPrice,1));
        prices.add(new BarEntry(dryPrice,2));
        prices.add(new BarEntry(calfPrice,3));


        ArrayList animalType = new ArrayList<>();

        animalType.add("Heifers");
        animalType.add("Bullocks");
        animalType.add("Dry/Fat Cows");
        animalType.add("Calves");

       BarDataSet barDataSet = new BarDataSet(prices, "Cattle Price/Kg");
       barChart.animateY(1500);
       barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

       BarData data = new BarData(animalType, barDataSet);
       barChart.setData(data);




    }

}