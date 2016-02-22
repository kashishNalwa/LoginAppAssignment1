package com.click_labs.kashishnalwa.loginappassignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.click_labs.kashishnalwa.loginappassignment.R;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonData;

public class SplashScreen extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (CommonData.getLogInData() == null) {
                    intent = new Intent(SplashScreen.this, FirstPage.class);


                } else {
                    intent = new Intent(SplashScreen.this, DisplayDrivers.class);

                }

                startActivity(intent);
                finish();
            }
        }, timer);
    }
}
