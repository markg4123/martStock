package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

public class WebActivity extends AppCompatActivity {
     WebView webView;
     String selectedFromList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = findViewById(R.id.webview);

        selectedFromList = getIntent().getExtras().getString("selected ");

        Log.i(selectedFromList, "Selected:" + selectedFromList);
        //LSL Auctions mart
        if (selectedFromList.equals("Abbeyfeale Mart") || selectedFromList.equals("Ballinasloe Mart") ||
                selectedFromList.equals("Ballybofey Mart") || selectedFromList.equals("Ballymote Mart") ||
                selectedFromList.equals("Cahir Mart") || selectedFromList.equals("Carnaross Mart") ||
                selectedFromList.equals("Carnew Mart") || selectedFromList.equals("Carrigallen Mart") ||
                selectedFromList.equals("Castleisland Mart") || selectedFromList.equals("Devlin Mart") ||
                selectedFromList.equals("Dingle Mart") || selectedFromList.equals("Downpatrick Mart") ||
                selectedFromList.equals("Dungrarven Mart") || selectedFromList.equals("Ennis Mart") ||
                selectedFromList.equals("Gort Mart") || selectedFromList.equals("Granard Mart") ||
                selectedFromList.equals("Headford Mart") || selectedFromList.equals("Kanturk Mart") ||
                selectedFromList.equals("Kenmare Mart") || selectedFromList.equals("Kilmallock Mart") ||
                selectedFromList.equals("Kilfenora Mart") || selectedFromList.equals("Kilrush Mart") ||
                selectedFromList.equals("Listowel Mart") || selectedFromList.equals("Manorhamilton Mart") ||
                selectedFromList.equals("Mid Kerry Mart") || selectedFromList.equals("Milford Mart") ||
                selectedFromList.equals("Mohill Mart") || selectedFromList.equals("Newport Mart") ||
                selectedFromList.equals("Omagh Mart") || selectedFromList.equals("Roscommon Mart") ||
                selectedFromList.equals("Tullamore Mart")|| selectedFromList.equals("Scariff Mart")
                || selectedFromList.equals("Waterford Mart")) {

            webView.loadUrl("https://www.lslauctions.com/tv/");
            webView.getSettings().setJavaScriptEnabled(true);

        }
        //MartEye marts
        if (selectedFromList.equals("Ardee Mart")) {
            webView.loadUrl("https://ardee.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Athenry Mart")) {
            webView.loadUrl("https://athenry.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Ardee Mart")) {
            webView.loadUrl("https://ardee.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Ballina Mart")) {
            webView.loadUrl("https://ballina.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Ballinakill Mart")) {
            webView.loadUrl("https://ballinakill.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Ballybay Mart")) {
            webView.loadUrl("https://ballybay.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Ballyjamesduff Mart")) {
            webView.loadUrl("https://ballyjamesduff.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Ballymahon Mart")) {
            webView.loadUrl("https://ballymahon.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Ballyshannon Mart")) {
            webView.loadUrl("https://ballyshannon.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Blessington Mart")) {
            webView.loadUrl("https://blessington.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Carlow Mart")) {
            webView.loadUrl("https://carlow.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Cashel Mart")) {
            webView.loadUrl("https://cashel.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Cavan Mart")) {
            webView.loadUrl("https://cavan.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Clifden Mart")) {
            webView.loadUrl("https://clifden.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Dowra Mart")) {
            webView.loadUrl("https://dowra.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Drumshanbo Mart")) {
            webView.loadUrl("https://drumshanbomart.ie/index.html#!/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Enniscorthy Mart")) {
            webView.loadUrl("https://enniscorthy.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Kilcullen Mart")) {
            webView.loadUrl("https://kilcullen.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Kingscourt Mart")) {
            webView.loadUrl("https://kingscourt.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Loughrea Mart")) {
            webView.loadUrl("https://loughrea.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Maam Cross Mart")) {
            webView.loadUrl("https://maamcross.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Mountbellew Mart")) {
            webView.loadUrl("https://mountbellew.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }
        if (selectedFromList.equals("Tullow Mart")) {
            webView.loadUrl("https://tullowmart.marteye.ie/");
            webView.getSettings().setJavaScriptEnabled(true);
        }

        // MartBids marts
        if (selectedFromList.equals("Balla Mart")||selectedFromList.equals("Ballinrobe Mart")
                ||selectedFromList.equals("Baltinglass Mart") ||selectedFromList.equals("Birr Mart")
                || selectedFromList.equals("Clogher Mart")||selectedFromList.equals("Elphin Mart")
                ||selectedFromList.equals("Iveragh Mart")||selectedFromList.equals("Kilkenny Mart")
                ||selectedFromList.equals("Nenagh Mart")||selectedFromList.equals("Roscrea Mart")
                ||selectedFromList.equals("Tuam Mart")){
            webView.loadUrl("https://bidding.martbids.ie/app/v5/login.php/");
            webView.getSettings().setJavaScriptEnabled(true);
        }



    }
}