package com.click_labs.kashishnalwa.loginappassignment.Util;

/**
 * Created by kashish nalwa on 16-02-2016.
 */
public class CommonUtils {

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public final static boolean isValidPassword(CharSequence target) {
        if (target == null) {
            return false;
        } else if (target.length() >= 5) {
            return true;
        } else
            return false;
    }
    public final static boolean isValidPhoneNo(CharSequence target) {
        if (target == null) {
            return false;
        } else if (target.length() == 10) {
            return true;
        } else
            return false;
    }
}
