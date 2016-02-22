package com.click_labs.kashishnalwa.loginappassignment.Util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by kashish nalwa on 16-02-2016.
 */
public class CommonProgressDialog {
    public static ProgressDialog progressDialog;


    public static void showProgressDialog(Context context, String title, String msg) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
