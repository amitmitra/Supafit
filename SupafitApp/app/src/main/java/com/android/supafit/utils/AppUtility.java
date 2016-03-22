package com.android.supafit.utils;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amitmitra on 15/03/16.
 */
public class AppUtility {

    public static final boolean isValidPhoneNumber(String phoneNumber){
        String expression = "^(\\+91-|\\+91|0)?\\d{10}$";
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static final boolean isValidEmailId(String emailId){
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailId);
        return matcher.matches();
    }

    public static final String formatDateTime(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static final String formatDate(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static final String formatTime(Date date){
        DateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    public static void showLongSnackBar(CoordinatorLayout mCoordinatorLayout, String msg){
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, msg, Snackbar.LENGTH_LONG);

        snackbar.show();
    }
}
