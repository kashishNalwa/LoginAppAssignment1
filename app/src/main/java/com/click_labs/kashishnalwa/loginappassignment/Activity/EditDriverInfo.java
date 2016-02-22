package com.click_labs.kashishnalwa.loginappassignment.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.click_labs.kashishnalwa.loginappassignment.Model.DriverData;
import com.click_labs.kashishnalwa.loginappassignment.Model.ErrorCodes;
import com.click_labs.kashishnalwa.loginappassignment.Model.RegisterDriverResponse;
import com.click_labs.kashishnalwa.loginappassignment.R;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonData;
import com.click_labs.kashishnalwa.loginappassignment.retrofit.RestClient;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EditDriverInfo extends BaseActivity {
    TextView actionBarTv;
    EditText EditNameEt;
    EditText EditContactEt;
    EditText EditEmailEt;
    EditText EditAddressEt;
    Button editAddBtn;
    Button editUpdateBtn;
    Button editDeleteBtn;
    DriverData driverData;
    ImageView actionBarIv;
    String accessToken="bearer "+ CommonData.getLogInData().getData().get(0).getAccessToken();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_driver_info);
        driverData = (DriverData) getIntent().getSerializableExtra("hello");
        init();
        setDataIntoEt();
        editAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditNameEt.setEnabled(true);
                EditContactEt.setEnabled(true);
                EditEmailEt.setEnabled(true);
                EditAddressEt.setEnabled(true);
                editUpdateBtn.setVisibility(View.VISIBLE);

            }
        });

        editUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        editDeleteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.v("driverId", driverData.getId());
                DeleteData deleteData=new DeleteData(String.valueOf(driverData.getId()));

                RestClient.getApiService().deleteClicked(accessToken, deleteData.toString(), new Callback<RegisterDriverResponse>() {
                    @Override
                    public void success(RegisterDriverResponse registerDriverResponse, Response response) {
                        CommonData.setRegisterDriverResponse(registerDriverResponse);
                        Toast.makeText(EditDriverInfo.this,CommonData.getRegisterDriverResponse().getMessage(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.v("error",error.toString());
                        ErrorCodes.checkCode(EditDriverInfo.this,error,true);
                    }
                });

            }
        });


    }

    private void setDataIntoEt() {
        actionBarTv.setText("Edit Driver Profile");
        actionBarIv.setImageResource(R.drawable.btn_nav_back_normal1);
        EditNameEt.setText(driverData.getName());
        EditContactEt.setText(driverData.getPhoneNo());
        EditEmailEt.setText(driverData.getEmail());
        EditAddressEt.setText(driverData.getAddress());
    }


    private void init() {
        actionBarTv=(TextView) findViewById(R.id.app_bar_text_view);
        actionBarIv=(ImageView) findViewById(R.id.app_bar_image_view);
        EditNameEt = (EditText) findViewById(R.id.edit_enter_name_et);
        EditContactEt = (EditText) findViewById(R.id.edit_enter_contact_et);
        EditEmailEt = (EditText) findViewById(R.id.edit_enter_email_et);
        EditAddressEt = (EditText) findViewById(R.id.edit_enter_address_et);
        editAddBtn=(Button) findViewById(R.id.edit_add_btn);
        editUpdateBtn=(Button) findViewById(R.id.edit_update_btn);
        editDeleteBtn=(Button) findViewById(R.id.edit_delete_btn);
    }


    public class DeleteData
    {
        String driverId="";

        public DeleteData(String driverId) {
            this.driverId = driverId;
        }
    }
}
