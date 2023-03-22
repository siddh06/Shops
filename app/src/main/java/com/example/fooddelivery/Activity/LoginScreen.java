package com.example.fooddelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.fooddelivery.Adapter.SliderAdapter;
import com.example.fooddelivery.R;
import com.example.fooddelivery.modal.SliderImageModel;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;


import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {
    SliderView loginSlider;
    ArrayList<SliderImageModel> sliderImageModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        getSupportActionBar().hide();

        loginSlider = findViewById(R.id.loginSlider);

        sliderImageModels = new ArrayList<>();
        //sliderImageModels.add(new SliderImageModel("https://www.shopickr.com/wp-content/uploads/2021/01/myntra-republic-day-sale-2021.png", "hiii Devendra"));
        //sliderImageModels.add(new SliderImageModel("https://www.jiomart.com/images/cms/aw_rbslider/slides/1655751757_Smart-casuals.jpg", "hiii Devendra"));
        sliderImageModels.add(new SliderImageModel("https://www.citymed.com.au/wp-content/uploads/2020/02/blog3-1.webp", "hiii Devendra"));
        sliderImageModels.add(new SliderImageModel("https://www.sussexdoctors.com.au/wp-content/uploads/2020/11/banner_center_img_mobile.png", "hiii Kunal"));
        sliderImageModels.add(new SliderImageModel("https://media.istockphoto.com/id/1353442568/vector/medical-laboratory.jpg?s=612x612&w=0&k=20&c=4lNLv_cYG38fMbcgmUbnqhchPsj4YDvE9_6KYt02k9I=", "hiii siidhhesh"));

        SliderAdapter adapter = new SliderAdapter(this, sliderImageModels);

        loginSlider.setSliderAdapter(adapter);

        loginSlider.setIndicatorAnimation(IndicatorAnimationType.SCALE_DOWN); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        loginSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
        loginSlider.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        loginSlider.setIndicatorSelectedColor(Color.WHITE);
        loginSlider.setIndicatorUnselectedColor(Color.GRAY);
        loginSlider.setScrollTimeInSec(4); //set scroll delay in seconds :
        loginSlider.startAutoCycle();
    }
}