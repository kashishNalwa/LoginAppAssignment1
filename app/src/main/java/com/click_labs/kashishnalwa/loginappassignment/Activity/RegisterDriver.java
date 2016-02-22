package com.click_labs.kashishnalwa.loginappassignment.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.click_labs.kashishnalwa.loginappassignment.Model.ErrorCodes;
import com.click_labs.kashishnalwa.loginappassignment.Model.RegisterDriverResponse;
import com.click_labs.kashishnalwa.loginappassignment.R;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonAlertClass;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonData;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonProgressDialog;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonUtils;
import com.click_labs.kashishnalwa.loginappassignment.retrofit.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RegisterDriver extends BaseActivity {

    TextView actionBarTv;
    ImageView actionBarIv;
    EditText enteredNameEt;
    EditText enteredContactEt;
    EditText enteredEmailEt;
    EditText enteredAddressEt;
    String enteredName;
    String enteredContact;
    String enteredEmail;
    String enteredAddress;
    Button addBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_driver);
        init();
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enteredName = enteredNameEt.getText().toString();
                enteredContact = enteredContactEt.getText().toString();
                enteredEmail = enteredEmailEt.getText().toString();
                enteredAddress = enteredAddressEt.getText().toString();
                if (validate(enteredEmail, enteredContact)) {
                    apiCallToLoginSupplier();
                }


            }
        });

        actionBarIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonPressed();
            }
        });
    }

    private boolean validate(String email, String phone) {
        boolean isValidMail = CommonUtils.isValidEmail(email);
        boolean isValidPhoneNo = CommonUtils.isValidPhoneNo(phone);
       if (isValidMail == false) {
            CommonAlertClass.showAlert(RegisterDriver.this, "Enter Valid Mail");
            return false;
        } else if (isValidPhoneNo == false) {
            CommonAlertClass.showAlert(RegisterDriver.this, "Phone No should be atleast 10 integers in length");
            return false;
        } else {
            return true;
        }
    }

    private void apiCallToLoginSupplier() {

        String accessToken = "bearer " + CommonData.getLogInData().getData().get(0).getAccessToken();
        CommonProgressDialog.showProgressDialog(RegisterDriver.this, "Refreshing ...", "Please wait ...");
        Log.v("hello", accessToken);

        RestClient.getApiService().addDriverClicked(accessToken, enteredName, enteredContact, enteredEmail, enteredAddress, new Callback<RegisterDriverResponse>() {
            @Override
            public void success(RegisterDriverResponse registerDriverResponse, Response response) {
                CommonProgressDialog.hideProgressDialog();
                CommonData.setRegisterDriverResponse(registerDriverResponse);
                CommonAlertClass.showAlert(RegisterDriver.this, "New Driver Registered!", new CommonAlertClass.OnAlertOkClickListener() {
                    @Override
                    public void onOkButtonClicked() {
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                });
                Log.v("hello", CommonData.getRegisterDriverResponse().getMessage());


            }

            @Override
            public void failure(RetrofitError error) {
                CommonProgressDialog.hideProgressDialog();
                Log.v("error", error.toString());
                ErrorCodes.checkCode(RegisterDriver.this, error, true);
            }
        });

    }

    private void backButtonPressed() {
        Intent intent = new Intent(RegisterDriver.this, DisplayDrivers.class);
        startActivity(intent);
    }

    private void init() {
        actionBarTv = (TextView) findViewById(R.id.app_bar_text_view);
        actionBarTv.setText("Registration Form");

        actionBarIv = (ImageView) findViewById(R.id.app_bar_image_view);
        actionBarIv.setImageResource(R.drawable.btn_nav_back_normal1);
        enteredNameEt = (EditText) findViewById(R.id.register_enter_name_et);
        enteredContactEt = (EditText) findViewById(R.id.register_enter_contact_et);
        enteredEmailEt = (EditText) findViewById(R.id.register_enter_email_et);
        enteredAddressEt = (EditText) findViewById(R.id.register_enter_address_et);
        addBtn = (Button) findViewById(R.id.register_add_btn);
    }
}
