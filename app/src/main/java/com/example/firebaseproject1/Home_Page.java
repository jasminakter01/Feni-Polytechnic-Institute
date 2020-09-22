package com.example.firebaseproject1;

import android.content.Intent;


import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

public class Home_Page extends AppCompatActivity {
    SliderLayout sliderLayout;
    //CardView GoToHomePAge;
   /* DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__page);
        sliderLayout = findViewById(R.id.imageSlider);

       /* GoToHomePAge=findViewById(R.id.homid);
        drawerLayout=findViewById(R.id.navihome1id);*/
       /* NavigationView navigationView=findViewById(R.id.navigation_id);
        navigationView.setNavigationItemSelectedListener(this);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.naviopen,R.string.naviclose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();

        Thread thread=new Thread() {
            @Override
            public void run() {
                //   showprog();
                try {
                    sleep(5000);
                    Intent intent=new Intent(getApplicationContext(),HomePage2.class);
                    startActivity(intent);
                    finish();
                    super.run();

                }catch (Exception e){
                }
            }
        };
        thread.start();



/*
        GoToHomePAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HomePage2.class);
                startActivity(intent);
            }
        });*/
    }

    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(this);

            switch (i) {
                case 0:
                    sliderView.setImageDrawable(R.drawable.fpi1);
                    sliderView.setDescription("Feni Polytechnic Institute");
                    break;
                case 1:
                    sliderView.setImageDrawable(R.drawable.fpi2);
                    sliderView.setDescription("Feni Polytechnic Institute");
                    break;
                case 2:
                    sliderView.setImageDrawable(R.drawable.fpi3);
                    sliderView.setDescription("Feni Polytechnic Institute");
                    break;

                case 3:
                    sliderView.setImageDrawable(R.drawable.fpi5);
                    sliderView.setDescription("Feni Polytechnic Institute");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
           // sliderView.setDescription("setDescription " + (i + 1));
            sliderView.setDescription("Feni Polytechnic Institute");

            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                   // Toast.makeText(getApplicationContext(), "This is slider " + (finalI + 1), Toast.LENGTH_SHORT).show();
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }

}

}
