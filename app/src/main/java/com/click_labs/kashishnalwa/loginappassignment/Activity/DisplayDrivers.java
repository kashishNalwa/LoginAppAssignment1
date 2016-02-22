package com.click_labs.kashishnalwa.loginappassignment.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.click_labs.kashishnalwa.loginappassignment.Adapter.VivzAdapter;
import com.click_labs.kashishnalwa.loginappassignment.Model.DriverData;
import com.click_labs.kashishnalwa.loginappassignment.Model.DriverDetail;
import com.click_labs.kashishnalwa.loginappassignment.Model.ErrorCodes;
import com.click_labs.kashishnalwa.loginappassignment.R;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonAlertClass;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonData;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonProgressDialog;
import com.click_labs.kashishnalwa.loginappassignment.retrofit.RestClient;

import java.util.Collections;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DisplayDrivers extends BaseActivity {
    TextView actionBarTv;
    RecyclerView recyclerView;
    ImageView logoutIv;
    VivzAdapter adapter;
    Button addDriversBtn;
    List<DriverData> driverDataList = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_drivers);

        init();
        if (CommonData.getDriverDetail() == null) {

            apiCallToDisplayRegisteredDrivers(false);

        } else {

            adapter.setDataAndNotifiy(CommonData.getDriverDetail().getData());
            apiCallToDisplayRegisteredDrivers(true);

        }

        addDriversBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayDrivers.this, RegisterDriver.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        logoutIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutClicked();
            }
        });


    }

    private void logoutClicked() {
        CommonAlertClass.showAlertWithCancel(DisplayDrivers.this, "Are you sure you want to Logout?", new CommonAlertClass.OnAlertOkWithCancelClickListener() {
            @Override
            public void onOkButtonClicked() {
                CommonData.clearData();
                Intent intent = new Intent(DisplayDrivers.this, FirstPage.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelButtonClicked() {

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {

            apiCallToDisplayRegisteredDrivers(true);

        }
    }

    private void apiCallToDisplayRegisteredDrivers(Boolean flag) {
        if (flag == false) {
            CommonProgressDialog.showProgressDialog(DisplayDrivers.this, "Please wait ...", "Fetching data from server ...");
        }

        String accessToken = "bearer " + CommonData.getLogInData().getData().get(0).getAccessToken();
        Log.v("hello", accessToken);
        RestClient.getApiService().getAllDriver(accessToken, new Callback<DriverDetail>() {
            @Override
            public void success(DriverDetail driverDetail, Response response) {
                CommonProgressDialog.hideProgressDialog();
                CommonData.setDriverDetail(driverDetail);
                driverDataList = CommonData.getDriverDetail().getData();
                adapter.setDataAndNotifiy(driverDataList);
            }

            @Override
            public void failure(RetrofitError error) {
                CommonAlertClass.hideAlert();
                Log.v("failure", error.toString());
                ErrorCodes.checkCode(DisplayDrivers.this, error, true);

            }
        });
    }

    private void init() {
        logoutIv = (ImageView) findViewById(R.id.app_bar_ibtn);
        logoutIv.setImageResource(R.drawable.logout_iv);

        actionBarTv = (TextView) findViewById(R.id.app_bar_text_view);
        actionBarTv.setText("Registered Drivers");
        addDriversBtn = (Button) findViewById(R.id.add_driver_btn);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        adapter = new VivzAdapter(DisplayDrivers.this, driverDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplayDrivers.this));
        recyclerView.setAdapter(adapter);

    }
}
