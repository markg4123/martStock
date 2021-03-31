package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class SummaryActivity extends AppCompatActivity {
   RecyclerView pricesRcv;
    RecyclerView.Adapter pricesAdapter;
    Document doc;
    ArrayList<Prices> prices = new ArrayList<>();
    BarChart barChart;
    String hefPrice;
    Float pri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        barChart = (BarChart) findViewById(R.id.bargraph);


        pricesRcv = findViewById(R.id.pricesRcv);
        pricesRcv.setHasFixedSize(true);
          int numberOfColumns =2;
        pricesRcv.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        new cattleReport().execute();
    }

    public class cattleReport extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try {

                doc = Jsoup.connect("https://www.agriland.ie/beef/").get();

                Elements reps = doc.getAllElements();
                for (Element tag : reps) {
                    if (tag.attr("class").equals("col-6")) {

                        String animalType = tag.getElementsByTag("p").first().text();
                        String priceChange = tag.getElementsByClass("price").first().text();
                        String pricePerKg = tag.getElementsByClass("price").last().text();

                        Prices price = new Prices(animalType.toUpperCase(), priceChange,pricePerKg);
                        prices.add(price);

                        ArrayList<BarEntry> priceperkg = new ArrayList<>();

                        if(tag.attr("href").equals("https://www.agriland.ie/factory-prices/young-bull-prices/")) {
                             hefPrice = tag.getElementsByClass("price").last().text();
                             pri = Float.valueOf(hefPrice.replace("€", "").replace(" Euro Per Kg", ""));
                        }



                        Float money = Float.valueOf(pricePerKg.replace("€", "").replace(" Euro Per Kg", ""));
                        priceperkg.add(new BarEntry(Float.parseFloat(String.valueOf(pri)),0));
                        priceperkg.add(new BarEntry(Float.parseFloat(String.valueOf(money)),1));
                        priceperkg.add(new BarEntry(Float.parseFloat(String.valueOf(money)),2));
                        priceperkg.add(new BarEntry(Float.parseFloat(String.valueOf(money)),3));

                        ArrayList animalTypes = new ArrayList<>();

                        animalTypes.add("Heifers");
                        animalTypes.add("Steers");
                        animalTypes.add("Dry/Fat Cows");
                        animalTypes.add("Bulls");
                        animalTypes.add("Young Bulls");

                        BarDataSet barDataSet = new BarDataSet(priceperkg, "Cattle Price/Kg");
//                        barChart.animateY(1500);
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

                        BarData data = new BarData(animalTypes, barDataSet);
                        barChart.setData(data);


                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);


            pricesAdapter = new PricesAdapter(prices);
            pricesRcv.setAdapter(pricesAdapter);


        }
    }
}