package com.android.supafit.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by amitmitra on 15/03/16.
 */
public class AppPreferences {

    private final String APP_PREFS_NAME = "appPreferences";
    private SharedPreferences mSharedPreferences;

    private final String CLIENT_ID = "supafit-mobile-app";
    private final String CLIENT_SECRET = "SsUbJJio22nH3rgFf32eRFEF43dedc2wfc2ef_RF34wsdxSXQSCX34RDSdcsd";

    private final String USER_ID_KEY = "user_id";
    private final String USER_EMAIL_KEY = "user_email";
    private final String USER_NAME_KEY = "user_name";
    private final String USER_PASSWORD_KEY = "password";
    private final String ACCESS_TOKEN_KEY = "access_token";
    private final String REFRESH_TOKEN_KEY = "refresh_token";
    private final String GCM_TOKEN_KEY = "gcmtoken";
    private final String USER_STATUS_FLAG = "userstatus";

    public AppPreferences(Context activityContext) {
        mSharedPreferences = activityContext.getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public String getClientId(){
        return CLIENT_ID;
    }

    public String getClientSecret(){
        return CLIENT_SECRET;
    }

    public void setUserEmail(String userEmail){
        mSharedPreferences.edit().putString(USER_ID_KEY, userEmail).commit();
    }

    public String getUserEmail(){
        return mSharedPreferences.getString(USER_EMAIL_KEY, "");
    }

    public void setUserId(long userId){
        mSharedPreferences.edit().putLong(USER_ID_KEY, userId).commit();
    }

    public long getUserId(){
        return mSharedPreferences.getLong(USER_ID_KEY, 0);
    }

    public void setUserName(String userName){
        mSharedPreferences.edit().putString(USER_NAME_KEY, userName).commit();
    }

    public String getUserName(){
        return mSharedPreferences.getString(USER_NAME_KEY, "");
    }

    public void setUserPassword(String password){
        mSharedPreferences.edit().putString(USER_PASSWORD_KEY, password).commit();
    }

    public String getUserPassword(){
        return mSharedPreferences.getString(USER_PASSWORD_KEY, "");
    }

    public void setAccessToken(String accessToken){
        mSharedPreferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).commit();
    }

    public String getAccessToken(){
        return mSharedPreferences.getString(ACCESS_TOKEN_KEY, "");
    }

    public void setRefreshToken(String refreshToken){
        mSharedPreferences.edit().putString(REFRESH_TOKEN_KEY, refreshToken).commit();
    }

    public String getRefreshToken(){
        return mSharedPreferences.getString(REFRESH_TOKEN_KEY, "");
    }

    public void setGcmToken(String gcmToken){
        mSharedPreferences.edit().putString(GCM_TOKEN_KEY, gcmToken).commit();
    }

    public String getGcmToken(){
        return mSharedPreferences.getString(GCM_TOKEN_KEY, "");
    }

    public void setUserStatus(int status){
        mSharedPreferences.edit().putInt(USER_STATUS_FLAG, status).commit();
    }

    public int getUserStatus(){
        return mSharedPreferences.getInt(USER_STATUS_FLAG, 0);
    }
}
