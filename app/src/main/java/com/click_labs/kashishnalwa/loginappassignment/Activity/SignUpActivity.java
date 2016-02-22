package com.click_labs.kashishnalwa.loginappassignment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class SignUpActivity extends BaseActivity {
    EditText supplierNameEt;
    EditText supplierNoEt;
    EditText supplierEmailEt;
    EditText supplierPasswordEt;
    Button signUpBtn;
    String enteredSupplierName;
    String enteredSupplierEmail;
    String enteredSupplierNo;
    String enteredSupplierPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedSignUp();
            }
        });
    }

    private void clickedSignUp() {
        enteredSupplierName = supplierEmailEt.getText().toString();
        enteredSupplierEmail = supplierEmailEt.getText().toString();
        enteredSupplierNo = supplierNoEt.getText().toString();
        enteredSupplierPassword = supplierPasswordEt.getText().toString();
        if (validate(enteredSupplierEmail, enteredSupplierNo, enteredSupplierPassword)) {
            apiCalltogetLogInDAta();
        }


    }

    private void apiCalltogetLogInDAta() {
        CommonProgressDialog.showProgressDialog(SignUpActivity.this, "Sign up", "Loading...");
        RestClient.getApiService().signUpClicked(enteredSupplierName, enteredSupplierNo, enteredSupplierEmail, enteredSupplierPassword, new Callback<LogInData>() {
            @Override
            public void success(LogInData logInData, Response response) {
                CommonProgressDialog.hideProgressDialog();
                CommonData.setLogInData(logInData);

                CommonAlertClass.showAlert(SignUpActivity.this, "Supplier Created", new CommonAlertClass.OnAlertOkClickListener() {
                    @Override
                    public void onOkButtonClicked() {
                        Intent intent = new Intent(getApplicationContext(), DisplayDrivers.class);
                        startActivity(intent);
                    }
                });
                //Toast.makeText(getApplicationContext(), "Supplier Created", Toast.LENGTH_LONG).show();

            }

            @Override
            public void failure(RetrofitError error) {
                Log.v("failure", error.toString());
                CommonProgressDialog.hideProgressDialog();
                ErrorCodes.checkCode(SignUpActivity.this, error, true);
            }
        });
    }

    private boolean validate(String email, String phone, String password) {
        boolean isValidMail = CommonUtils.isValidEmail(email);
        boolean isValidPhoneNo = CommonUtils.isValidPhoneNo(phone);
        boolean isValidPass = CommonUtils.isValidPassword(password);


        if (isValidMail == false) {
            CommonAlertClass.showAlert(SignUpActivity.this, "Enter Valid Mail");
            return false;
        } else if (isValidPass == false) {
            CommonAlertClass.showAlert(SignUpActivity.this, "Password should be atleast 5 integers in length");
            return false;
        } else if (isValidPhoneNo == false) {
            CommonAlertClass.showAlert(SignUpActivity.this, "Phone No should be atleast 10 integers in length");
            return false;
        } else {
            return true;
        }
    }

    private void init() {
        supplierNameEt = (EditText) findViewById(R.id.enter_name_et);
        supplierNoEt = (EditText) findViewById(R.id.enter_contact_et);
        supplierEmailEt = (EditText) findViewById(R.id.enter_email_et);
        supplierPasswordEt = (EditText) findViewById(R.id.enter_password_et);
        signUpBtn = (Button) findViewById(R.id.signup_btn);
    }
}
