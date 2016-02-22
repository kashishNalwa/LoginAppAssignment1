package com.click_labs.kashishnalwa.loginappassignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.click_labs.kashishnalwa.loginappassignment.R;

import org.w3c.dom.Text;

public class FirstPage extends AppCompatActivity {
    TextView actionBarTv;
    Button signUpBtn;
    Button logInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        init();
        BtnOnClickListener();
    }

    private void init() {
        actionBarTv=(TextView) findViewById(R.id.app_bar_text_view);
        actionBarTv.setText("Welcome");
        signUpBtn = (Button) findViewById(R.id.signup_btn);
        logInBtn = (Button) findViewById(R.id.login_btn);
    }

    private void BtnOnClickListener() {
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstPage.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        logInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FirstPage.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
