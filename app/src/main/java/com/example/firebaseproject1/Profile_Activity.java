package com.example.firebaseproject1;


import android.os.Bundle;
import android.widget.ImageView;

import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import androidx.appcompat.app.AppCompatActivity;

public class Profile_Activity extends AppCompatActivity {
    SliderLayout sliderLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);
        this.setTitle("Profile");
        sliderLayout = findViewById(R.id.imageSliderprofileif);
        sliderLayout.setIndicatorAnimation(IndicatorAnimations.FILL);
        //set indicator animation by using SliderLayout.Animations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setScrollTimeInSec(1); //set scroll delay in seconds :

        setSliderViews();
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
