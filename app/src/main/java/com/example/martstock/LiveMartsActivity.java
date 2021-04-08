package com.example.martstock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LiveMartsActivity extends AppCompatActivity {
    ListView lv;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_marts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv = findViewById(R.id.lv);

        final String[] martList={ "Abbeyfeale Mart","Ardee Mart","Athenry Mart","Balla Mart","Ballina Mart","Ballinasloe Mart","Ballinrobe Mart",
                "Ballbay Mart","Ballybofey Mart","Ballyjamesduff Mart","Ballymahon Mart","Ballymote Mart","Ballyshannon Mart",
                "Baltinglass Mart","Birr Mart","Blesington Mart","Cahir Mart","Carlow Mart"+ "Carnaross Mart","Carnew Mart",
                "Carrick on Suir Mart","Cashel Mart","Carrigallen Mart","Castleisland Mart"+ "Castlerea Mart","Cavan Mart","Clifden Mart",
                "Clogher Mart","Devlin Mart","Dingle Mart","Downpatrick Mart"+ "Dowra Mart","Drumshanbo Mart","Dungrarven Mart","Elphin Mart",
                "Ennis Mart","Enniscorthy Mart", "Gort Mart","Granard Mart","Headford Mart","Iveragh Mart","Kanturk Mart",
                "Kenmare Mart","Kilcullen Mart","Kilkenny Mart","Kilmallock Mart","Kilrush Mart","Kingscourt Mart",
                "Listowel Mart","Loughrea Mart","Maam Cross Mart","Manorhamilton Mart","Mid Kerry Mart"+ "Milford Mart","Mohill Mart",
                "Mountbellew Mart","Nenagh Mart","Newport Mart","Omagh Mart","Roscommon Mart",
                "Roscrea Mart","Tuam Mart", "Tullamore Mart","Tullow Mart"};

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, martList);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 String selectedFromList = (String) (lv.getItemAtPosition(position));
                Intent intent = new Intent(LiveMartsActivity.this, WebActivity.class);
                intent.putExtra("selected ", selectedFromList);
                startActivity(intent);
            }
        });

    }
}