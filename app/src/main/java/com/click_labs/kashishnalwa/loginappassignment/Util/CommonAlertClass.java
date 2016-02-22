package com.click_labs.kashishnalwa.loginappassignment.Util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by kashish nalwa on 16-02-2016.
 */
public class CommonAlertClass {
    private static AlertDialog alert;
    private static OnAlertOkClickListener onAlertOkClickListener;
    private static OnAlertOkWithCancelClickListener onAlertOkWithCancelClickListener;

    public static void showAlert(Context context, String msg) {
        alert = new AlertDialog.Builder(context).setMessage(msg).create();
        alert.show();
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alert.dismiss();
            }
        });
    }

    public static void showAlert(Context context, String msg, OnAlertOkClickListener onAlertOkClickListener1) {
        onAlertOkClickListener = onAlertOkClickListener1;
        alert = new AlertDialog.Builder(context).setMessage(msg).create();

        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onAlertOkClickListener.onOkButtonClicked();
                alert.dismiss();
            }
        });
        alert.show();

    }

    public static void showAlertWithCancel(Context context, String msg, final OnAlertOkWithCancelClickListener onAlertOkWithCancelClickListener1) {
        CommonAlertClass.onAlertOkWithCancelClickListener = onAlertOkWithCancelClickListener1;
        alert = new AlertDialog.Builder(context).setMessage(msg).create();

        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onAlertOkWithCancelClickListener.onOkButtonClicked();

                alert.dismiss();
            }
        });
        alert.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onAlertOkWithCancelClickListener.onCancelButtonClicked();
                alert.dismiss();
            }
        });
        alert.show();
    }

    public static void hideAlert() {
        if (alert != null && alert.isShowing()) {
            alert.dismiss();
        }
    }


    public interface OnAlertOkClickListener {
        public void onOkButtonClicked();
    }

    public interface OnAlertOkWithCancelClickListener {
        public void onOkButtonClicked();

        public void onCancelButtonClicked();
    }


}
