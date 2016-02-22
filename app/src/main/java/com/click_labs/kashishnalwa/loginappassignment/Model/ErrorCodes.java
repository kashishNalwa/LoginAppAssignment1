package com.click_labs.kashishnalwa.loginappassignment.Model;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.click_labs.kashishnalwa.loginappassignment.R;
import com.click_labs.kashishnalwa.loginappassignment.Util.CommonData;

import org.json.JSONObject;

import retrofit.RetrofitError;
import retrofit.mime.TypedByteArray;

public class ErrorCodes {


    private static String errorMessage = "";

    /**
     * @param context
     * @param error
     */
    public static void checkCode(final Activity context, RetrofitError error, Boolean doCheckSession) {
        try {
            errorMessage = "";
            Log.v("error bundle", error.toString() + "");

           /* if (doCheckSession) {
                try {
                    if (error.getResponse().getStatus() == 401) {
                        //CommonDialog.With(context).Show("Session Expired", new CommonDialog.OnOkButtonClicked() {
                            @Override
                            public void onClicked() {
                                AppExit(context);
                                // logoutServerCall(context);
                            }
                        });

                        return;
                    }
                } catch (Exception e) {
                    Log.v("error Exception", e.toString() + "");
                }
            }*/


            switch (error.getKind()) {
                case NETWORK:
                    errorMessage = "Failed to connect. Please check your internet connection.";

                    break;
                case CONVERSION:
                    errorMessage = "An error was procured while parsing.";
                    break;
                case HTTP: {
                    errorMessage = "Application server could not respond. Please try later.";

                    try {
                        String str = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                        JSONObject json = new JSONObject(str);
                        errorMessage = json.getString("message");

                    } catch (Exception e) {
                        errorMessage = "Something went wrong. Please try later.";
                    }
                }
                break;

                case UNEXPECTED:
                    errorMessage = "An unexpected error occurred. Please try later.";
                    break;
            }

            /*if (!errorMessage.isEmpty())
                CommonDialog.With(context).Show(errorMessage);

*/
            Toast.makeText(context,errorMessage,Toast.LENGTH_LONG).show();
        }catch (Exception e) {
           // CommonDialog.With(context).Show("Something went wrong. Please try later");

        }
        }

/*    private static void AppExit(Activity context) {
        CommonData.ClearData(context);
        Intent i = new Intent(context, LoginSignupActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        context.finish();
        context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
*/

}