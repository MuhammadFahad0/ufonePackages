package com.fahad.ufonepackages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    Cursor c = null;
    List<product> list;
    RecyclerView RC;
    LinearLayoutManager manager;
    adrapter adp;
    String typeselection;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt = findViewById(R.id.textView22);

        RC = findViewById(R.id.vvvvvv);
        manager = new LinearLayoutManager(this);
        RC.setLayoutManager(manager);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mAdView = findViewById(R.id.banner_container3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        list = new ArrayList<>();

        DatabaseHelper myDbHelper = new DatabaseHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }

        Intent intent = getIntent();
        typeselection = intent.getStringExtra("Key2");

                switch (typeselection)
                {
                    case "ALL IN ONE":
                        c = myDbHelper.query("ufoneallinone", null, null, null, null, null, null);
                        txt.setText("Ufone All in One");
                        break;

                    case "CALL":
                        c = myDbHelper.query("ufonecall", null, null, null, null, null, null);
                        txt.setText("Ufone Call");
                        break;

                    case "MESSAGE":
                        c = myDbHelper.query("ufonemessage", null, null, null, null, null, null);
                        txt.setText("Ufone Message");
                        break;

                    case "INTERNET":
                        c = myDbHelper.query("ufoneinternet", null, null, null, null, null, null);
                        txt.setText("Ufone Internet");
                        break;

                    case "BROADBAND":
                        c = myDbHelper.query("ufonebroadband", null, null, null, null, null, null);
                        txt.setText("EVO Wireless Broadband");
                        txt.setTextColor(Color.parseColor("#006029"));
                        break;

                    case "datasim":
                        c = myDbHelper.query("ufonedatasim", null, null, null, null, null, null);
                        txt.setText("ufone Data Sim");
                        break;
                }
        if (c.moveToFirst()) {
            do {
                list.add(new product(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getString(6),c.getString(7)));
            } while (c.moveToNext());
        }
        adp = new adrapter(Main2Activity.this,list);
        RC.setAdapter(adp);
    }
    @Override
    public void onBackPressed() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
        }


        super.onBackPressed();
    }
}