package com.fahad.ufonepackages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.BuildConfig;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {
    String message;
    private FirebaseAnalytics mFirebaseAnalytics;
    String text;
    EditText edt;
    AlertDialog.Builder dialogBuilder;
    LayoutInflater inflater;
    View dialogView;
    AlertDialog b;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    View navHeaderView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        imageView = findViewById(R.id.imageView);

        drawerLayout = findViewById(R.id.drawer_layout2);
        navigationView = findViewById(R.id.nav_view2);
        toolbar = findViewById(R.id.tool);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_toc_black_24dp);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.balance:
                        balance1();
                        return true;
                    case R.id.sms:
                        sms1();
                        return true;
                    case R.id.internet:
                        internet1();
                        return true;
                    case R.id.minuts:
                        minuts1();
                        return true;
                    case R.id.recharge:
                        recharge1();
                        return true;
                    case R.id.advance:
                        advance1();
                        return true;
                    case R.id.balanceshare:
                        balanceshare1();
                        return true;
                    case R.id.nav_share:
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "All Pakistan Network Packages");
                            String shareMessage= "\nCheck out this Packages App have all Latest Updates\n\n";
                            shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "Choose one"));
                        } catch(Exception e) {
                            //e.toString();
                        }
                        return true;
                    case R.id.supportus:
                        try{
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+getPackageName())));
                        }
                        catch (ActivityNotFoundException e){
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName())));
                        }
                        return true;
                    case R.id.policy:

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1rVNmMh_CPex20XRlzM5YEF_d9ayn-Kk7/view?usp=sharing")));
                        return true;
                    case R.id.exit:
                        onBackPressed();
                        return true;

                    default:
                        return false;
                }
            }
        });

        navHeaderView = navigationView.inflateHeaderView(R.layout.headersecond);
        ImageView headerImage = navHeaderView.findViewById(R.id.headderpic);
        LinearLayout headerlayout = navHeaderView.findViewById(R.id.layoutheadder);

        message = "ufone";

        getSupportActionBar().setTitle("All Ufone Packages Detail");
        navigationView.setBackgroundColor(Color.parseColor("#80FC6E00"));
        headerImage.setImageResource(R.drawable.header_ufone);
        headerlayout.setBackgroundColor(Color.parseColor("#fc6e00"));
     }

    public void all(View view) { newwcativty("ALL IN ONE"); }

    public void call(View view)
    {
        newwcativty("CALL");
    }

    public void internet(View view)
    {
        newwcativty("INTERNET");
    }

    public void band(View view)
    {
        newwcativty("BROADBAND");
    }

    public void sms(View view) { newwcativty("MESSAGE"); }

    void newwcativty(String PPosition)
    {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("Key2", PPosition);

        if( message.equals("ufone") && PPosition.equals("BROADBAND"))
        {
            Toast.makeText(this,"Sorry !! "+System.getProperty("line.separator")+" Not Available from Ufone Company",Toast.LENGTH_SHORT).show();
        }else
        {
            startActivity(intent);
        }

    }



    void balance1()
    {

                fuck("*124#");
       }

    void sms1()
    {
                fucksms("606","");
                Toast.makeText(this,"Sent Empty Message to 606",Toast.LENGTH_SHORT).show();
       }

    void internet1()
    {         fuck("*706#");
     }

    void minuts1()
    {
                fuck("*707#");
     }

    void recharge1()
    {
        dialogBuilder = new AlertDialog.Builder(this);

        dialogBuilder.setCancelable(false);
        inflater = this.getLayoutInflater();
        dialogView = inflater.inflate(R.layout.alartdailog, null);
        dialogBuilder.setView(dialogView);
        edt = dialogView.findViewById(R.id.editText);

        Button sent = dialogView.findViewById(R.id.button5);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text = edt.getText().toString();
                if(text.length() == 14)
                {
                            fuck("*123*"+ text +"#");

                    b.dismiss();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Enter 14 Digit Card Number",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button cancel = dialogView.findViewById(R.id.button6);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.dismiss();
            }
        });

        b = dialogBuilder.create();
        b.show();    }

    void advance1()
    {
                fuck("*456#");
     }

    void balanceshare1()
    {
            dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setCancelable(false);
            inflater = this.getLayoutInflater();
            dialogView = inflater.inflate(R.layout.alartdailoggift, null);
            dialogBuilder.setView(dialogView);
            final EditText num = dialogView.findViewById(R.id.editText4);
            final EditText amount = dialogView.findViewById(R.id.editText5);

            Button sent = dialogView.findViewById(R.id.button8);
            sent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String text1 = num.getText().toString();
                    String text2 = amount.getText().toString();
                            fuck("*828*"+text1+"*"+text2+"#");

                }
            });

            Button cancel = dialogView.findViewById(R.id.button9);
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b.dismiss();
                }
            });

            b = dialogBuilder.create();
            b.show();

        }



    void fuck(String code)
    {
        Intent out = new Intent();
        out.setAction(Intent.ACTION_DIAL);
        out.setData(Uri.parse("tel:" + Uri.encode(code)));
        startActivity(out);

    }

    void fucksms(String number,String txt)
    {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.Q)
        {

        }
        else
        {

            Intent smsIntent = new Intent(Intent.ACTION_VIEW);
            smsIntent.setType("vnd.android-dir/mms-sms");
            smsIntent.putExtra("address", number);
            smsIntent.putExtra("sms_body",txt);
            startActivity(smsIntent);
        }
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}

