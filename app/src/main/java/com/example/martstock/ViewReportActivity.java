package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class ViewReportActivity extends AppCompatActivity {
    Spinner martSpinner;
    Button martButton, button;
    RecyclerView reportRcv;
    RecyclerView.Adapter reportAdapter;
    ArrayList<Report> reports = new ArrayList<>();
    int numberOfColumns;

    String value;
    double bullockAveragePrice = 0.0;
    double heiferAveragePrice = 0.0;
    double cowAveragePrice = 0.0;
    double calfAveragePrice = 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_report);


        reportRcv = findViewById(R.id.reportRcv);
        reportRcv.setHasFixedSize(true);
        numberOfColumns =2;
        reportRcv.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

        martButton = findViewById(R.id.martText);
        button = findViewById(R.id.button);
        martSpinner = findViewById(R.id.martSpinner);



        final String[] martName = {"Bullocks", "Heifers", "Calves", "Dry/Fat Cows"};

        martSpinner.setAdapter(new ArrayAdapter<>(ViewReportActivity.this, android.R.layout
                .simple_spinner_dropdown_item, martName));

        new calfReport().execute();
        new heiferReport().execute();
        new cowReport().execute();
        new bullockReport().execute();



        martButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value = martSpinner.getSelectedItem().toString();
                if (value.equals("Heifers"))
                    new heiferReport().execute();
                if (value.equals("Bullocks"))
                    new bullockReport().execute();
                if (value.equals("Calves"))
                    new calfReport().execute();
                if (value.equals("Dry/Fat Cows"))
                    new cowReport().execute();
            }
        });
    }

    public class heiferReport extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            reports.clear();
            try {
                Document doc = Jsoup.connect("https://www.livestock-live.com/MartMemberAccess/Market/Sales?SearchRing=HFR&SearchDateWeek=2&SearchWeightFrom=&SearchWeightTo=&SearchBreed=&SearchOrderBy=Price&DoSearch=DoSearch").get();

                Elements reps = doc.getAllElements();
                for (Element tag : reps) {
                    if (tag.attr("class").equals("card-text")) {


                        String weight = tag.getElementsByTag("b").first().text();
                        String price = tag.getElementsByTag("b").last().text().replace("€", "").replace("/kg", "");
                        String gender = tag.getElementsByTag("span").first().text();
                        String breed = tag.getElementsByTag("span").get(1).text();
                        String dob = tag.getElementsByTag("span").last().text();
                        String date = "Last 2 Weeks";

                        Report report = new Report(weight, price, gender, breed, dob, date);
                        if(!price.matches("^[0-9].?[0-9]*")){
                            price = "0";

                        }
                        else{
                            reports.add(report);
                        }


                        double heiferprices = Double.parseDouble(price);
                        heiferAveragePrice = (heiferAveragePrice + heiferprices);

                    }
                }
                int amount = reports.size();
                heiferAveragePrice = heiferAveragePrice/amount;



            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            reportAdapter = new ReportAdapter(reports);
            reportRcv.setAdapter(reportAdapter);
        }
    }

    public class bullockReport extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            reports.clear();
            try {

                Document doc = Jsoup.connect("https://www.livestock-live.com/MartMemberAccess/Market/Sales?SearchRing=BLK&SearchDateWeek=2&SearchWeightFrom=&SearchWeightTo=&SearchBreed=&SearchOrderBy=Price&DoSearch=DoSearch").get();

                Elements reps = doc.getAllElements();
                for (Element tag : reps) {
                    if (tag.attr("class").equals("card-text")) {

                        String weight = tag.getElementsByTag("b").first().text();
                        String price = tag.getElementsByTag("b").last().text().replace("€", "").replace("/kg", "");
                        String gender = tag.getElementsByTag("span").first().text();
                        String breed = tag.getElementsByTag("span").get(1).text();
                        String dob = tag.getElementsByTag("span").last().text();
                        String date = "Last 2 Weeks";

                        Report report = new Report(weight, price, gender, breed, dob, date);
                        if(!price.matches("^[0-9].?[0-9]*")){
                            price = "0";

                        }
                        else{
                            reports.add(report);
                        }



                        double bullockprices = Double.parseDouble(price);
                        bullockAveragePrice = (bullockAveragePrice + bullockprices);


                    }
                }


                int amount = reports.size();
                bullockAveragePrice = bullockAveragePrice/amount;


            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            reportAdapter = new ReportAdapter(reports);
            reportRcv.setAdapter(reportAdapter);
        }
    }

    public class calfReport extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            reports.clear();
            try {
                Document doc = Jsoup.connect("https://www.livestock-live.com/MartMemberAccess/Market/Sales?SearchRing=CA&SearchDateWeek=2&SearchWeightFrom=&SearchWeightTo=&SearchBreed=&SearchOrderBy=Price&DoSearch=DoSearch").get();

                Elements reps = doc.getAllElements();
                for (Element tag : reps) {
                    if (tag.attr("class").equals("card-text")) {

                        String weight = tag.getElementsByTag("b").first().text();
                        String price = tag.getElementsByTag("b").last().text().replace("€", "").replace("/kg", "");
                        String gender = tag.getElementsByTag("span").first().text();
                        String breed = tag.getElementsByTag("span").get(1).text();
                        String dob = tag.getElementsByTag("span").last().text();
                        String date = "Last 2 Weeks";


                        Report report = new Report(weight, price, gender, breed, dob,date);
                        if(!price.matches("^[0-9].?[0-9]*")){
                            price = "0";

                        }
                        else{
                            reports.add(report);
                        }

                        double calfprices = Double.parseDouble(price);
                        calfAveragePrice = (calfAveragePrice + calfprices);

                    }
                }
                 int amount = reports.size();
                calfAveragePrice = calfAveragePrice/amount;
                System.out.println("Average Price: €" + new DecimalFormat("#.##").format(calfAveragePrice) + "/Kg");

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            reportAdapter = new ReportAdapter(reports);
            reportRcv.setAdapter(reportAdapter);
        }
    }

    public class cowReport extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            reports.clear();

            try {
                Document doc = Jsoup.connect("https://www.livestock-live.com/MartMemberAccess/Market/Sales?SearchRing=DCW&SearchDateWeek=2&SearchWeightFrom=&SearchWeightTo=&SearchBreed=&SearchOrderBy=Price&DoSearch=DoSearch").get();

                Elements reps = doc.getAllElements();
                for (Element tag : reps) {
                    if (tag.attr("class").equals("card-text")) {

                        String weight = tag.getElementsByTag("b").first().text();
                        String price = tag.getElementsByTag("b").last().text().replace("€", "").replace("/kg", "");
                        String gender = tag.getElementsByTag("span").first().text();
                        String breed = tag.getElementsByTag("span").get(1).text();
                        String dob = tag.getElementsByTag("span").last().text();
                        String date = "Last 2 Weeks";

                        Report report = new Report(weight, price, gender, breed, dob,date);
                        reports.add(report);

                        if(!price.matches("^[0-9].?[0-9]*")){
                            price ="0";
                        }
                        double cowprices = Double.parseDouble(price);
                        cowAveragePrice = (cowAveragePrice + cowprices);

                    }
                }
                int amount = reports.size();
                cowAveragePrice = cowAveragePrice/amount;
                System.out.println("Average Price: €" + new DecimalFormat("#.##").format(cowAveragePrice) + "/Kg");

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            reportAdapter = new ReportAdapter(reports);
            reportRcv.setAdapter(reportAdapter);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.item1:
                Intent i = new Intent(ViewReportActivity.this, SummaryActivity.class);
                startActivity(i);
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


}