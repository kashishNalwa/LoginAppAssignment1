package com.click_labs.kashishnalwa.loginappassignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.click_labs.kashishnalwa.loginappassignment.Model.ErrorCodes;
import com.click_labs.kashishnalwa.loginappassignment.Model.LogInData;
import com.click_labs.kashishnalwa.loginappassignment.R;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonAlertClass;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonData;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonProgressDialog;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonUtils;
import com.click_labs.kashishnalwa.loginappassignment.retrofit.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoginActivity extends BaseActivity {
    TextView actionBarTv;
    EditText enteredEmailEt;
    EditText enteredPasswordEt;
    String supplierEmail;
    String supplierPassword;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        BtnOnClickListener();

    }

    private void BtnOnClickListener() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginBtnClicked();
            }
        });
    }

    private void loginBtnClicked() {


        supplierEmail = enteredEmailEt.getText().toString();
        supplierPassword = enteredPasswordEt.getText().toString();

        if (validate(supplierEmail, supplierPassword)) {
            apiCallToLoginSupplier();
        }

    }

    private boolean validate(String email, String password) {
        boolean isValidMail = CommonUtils.isValidEmail(email);
        boolean isValidPass = CommonUtils.isValidPassword(password);
        if (isValidMail == false) {
            CommonAlertClass.showAlert(LoginActivity.this, "Enter Valid Mail");
            return false;
        } else if (isValidPass == false) {
            CommonAlertClass.showAlert(LoginActivity.this, "Password should be atleast 5 integers in length");
            return false;
        } else {
            return true;
        }
    }

    private void apiCallToLoginSupplier() {
        CommonProgressDialog.showProgressDialog(LoginActivity.this, "Please wait ...", "Connecting to server ...");
        RestClient.getApiService().loginClicked(supplierEmail, supplierPassword, new Callback<LogInData>() {
            @Override
            public void success(LogInData logInData, Response response) {

                CommonProgressDialog.hideProgressDialog();
                CommonData.setLogInData(logInData);
                CommonAlertClass.showAlert(LoginActivity.this, "Supplier Logged In!", new CommonAlertClass.OnAlertOkClickListener() {
                    @Override
                    public void onOkButtonClicked() {
                        Intent intent = new Intent(LoginActivity.this, DisplayDrivers.class);
                        startActivity(intent);
                    }
                });


            }

            @Override
            public void failure(RetrofitError error) {
                CommonProgressDialog.hideProgressDialog();
                Log.v("failure", error.toString());
                ErrorCodes.checkCode(LoginActivity.this, error, true);
            }
        });
    }


    private void init() {
        actionBarTv = (TextView) findViewById(R.id.app_bar_text_view);
        actionBarTv.setText("Supplier Login");
        enteredEmailEt = (EditText) findViewById(R.id.enter_email_et);
        enteredPasswordEt = (EditText) findViewById(R.id.enter_password_et);
        loginBtn = (Button) findViewById(R.id.login_btn);

    }
}
