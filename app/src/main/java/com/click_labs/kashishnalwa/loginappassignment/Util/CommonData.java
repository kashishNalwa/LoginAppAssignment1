package com.click_labs.kashishnalwa.loginappassignment.Util;

import android.content.Context;

import com.click_labs.kashishnalwa.loginappassignment.Model.DriverDetail;
import com.click_labs.kashishnalwa.loginappassignment.Model.LogInData;
import com.click_labs.kashishnalwa.loginappassignment.Model.RegisterDriverResponse;
import com.click_labs.kashishnalwa.loginappassignment.sharedpreferences.SharedPreferencesName;

import io.paperdb.Paper;

/**
 * Created by kashish nalwa on 11-02-2016.
 */
public class CommonData {
    private static LogInData logInData = null;
    private static DriverDetail driverDetail;
    private static RegisterDriverResponse registerDriverResponse;

    public static RegisterDriverResponse getRegisterDriverResponse() {
        return registerDriverResponse;
    }

    public static void setRegisterDriverResponse(RegisterDriverResponse registerDriverResponse) {
        CommonData.registerDriverResponse = registerDriverResponse;
    }

    public static DriverDetail getDriverDetail() {
        if (driverDetail == null) {
            driverDetail = Paper.book().read(SharedPreferencesName.APP_DRIVERS);
            //          driverDetail = Prefs.with(context).getObject(SharedPreferencesName.APP_DRIVERS, DriverDetail.class);
        }
        return driverDetail;
    }

    public static void setDriverDetail(DriverDetail driverDetail) {
        CommonData.driverDetail = driverDetail;
        Paper.book().write(SharedPreferencesName.APP_DRIVERS, driverDetail);
//        Prefs.with(context).save(SharedPreferencesName.APP_DRIVERS, driverDetail);
    }

    public static LogInData getLogInData() {
        if (logInData == null) {
            logInData = Paper.book().read(SharedPreferencesName.APP_USER);
            //logInData = Prefs.with(context).getObject(SharedPreferencesName.APP_USER, LogInData.class);
        }
        return logInData;
    }

    public static void setLogInData(LogInData logInData) {
        CommonData.logInData = logInData;
        Paper.book().write(SharedPreferencesName.APP_USER, logInData);
        //Prefs.with(context).save(SharedPreferencesName.APP_USER, logInData);
    }

    public static void clearData() {
        logInData = null;
        driverDetail = null;
        Paper.book().destroy();
        // Prefs.with(context).removeAll();
    }


}
